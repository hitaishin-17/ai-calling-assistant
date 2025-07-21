package com.hitaishi.aicall.controller;

import com.hitaishi.aicall.ai.GroqApiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.apache.catalina.manager.JspHelper.escapeXml;

/**
 * Endpoint to generate TwiML XML for Twilio voice playback.
 * Called by Twilio during outbound calls.
 */
@RestController
@RequestMapping("/api/twiml")
public class TwiMLEndpointController {

    private final GroqApiClient groqApiClient;

    @Value("${twilio.callback_base_url}")
    private String callbackBaseUrl;

    public TwiMLEndpointController(GroqApiClient groqApiClient) {
        this.groqApiClient = groqApiClient;
    }
    /**
     * Step 1: Greet and ask how they are
     */
    @PostMapping(value = "/start", produces = MediaType.APPLICATION_XML_VALUE)
    public void greetUser(HttpServletResponse response) throws IOException {
        System.out.println("Twilio POST /start hit");

        String twiml = """
        <?xml version="1.0" encoding="UTF-8"?>
        <Response>
          <Gather input="speech" action="%s/api/twiml/conversation" method="POST">
             <Say voice="alice">Hi! How can I help you today?</Say>
          </Gather>
          <Say>I didn't catch that. Goodbye!</Say>
        </Response>
        """.formatted(callbackBaseUrl);

        response.setContentType("application/xml");
        response.getWriter().write(twiml.trim());
        response.flushBuffer();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public void sayMessage(@RequestParam("message") String message, HttpServletResponse response) throws IOException {
        String twiml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Response>\n" +
                "  <Say voice=\"alice\">" + message + "</Say>\n" +
                "</Response>";

        response.setContentType("application/xml");
        response.getWriter().write(twiml.trim());
    }

    @PostMapping(value = "/conversation", produces = MediaType.APPLICATION_XML_VALUE)
    public void handleDynamicConversation(
            @RequestParam(value = "SpeechResult", required = false) String speech,
            HttpServletResponse response) throws IOException {

        if (speech == null || speech.isBlank()) {
            respondWith(response, "I'm sorry, I didn't catch that. Could you please repeat?");
            return;
        }
        System.out.println(speech);
        // Keywords to end the call
        String lower = speech.toLowerCase();
        if (lower.contains("bye") || lower.contains("cut the call") || lower.contains("disconnect") || lower.contains("hang up")) {
            respondWith(response, "Okay, ending the call now. Have a great day!");
            return;
        }

        // Else pass to Groq
        String reply = groqApiClient.generateCallScript(speech);
        respondWith(response, reply);
    }

    private void respondWith(HttpServletResponse response, String message) throws IOException {
        String twiml = """
    <?xml version="1.0" encoding="UTF-8"?>
    <Response>
      <Gather input="speech" action="/api/twiml/conversation" method="POST">
        <Say voice="alice">%s</Say>
      </Gather>
      <Say>Goodbye!</Say>
    </Response>
    """.formatted(message.replace("\"", "").trim());

        response.setContentType("application/xml");
        response.getWriter().write(twiml.trim());
        response.flushBuffer();
    }
    /**
     * Step 2: Respond to how they're doing, ask for appointment
     */
    @PostMapping(value = "/step2", produces = MediaType.APPLICATION_XML_VALUE)
    public void handleHowAreYou(@RequestParam(value = "SpeechResult", required = false) String speech,
                                HttpServletResponse response) throws IOException {
        String reply;

        if (speech != null && speech.toLowerCase().contains("good")) {
            reply = """
            <Response>
              <Gather input="speech" action="%s/api/twiml/step3" method="POST">
                <Say voice="alice">Glad to hear that. Would you like to book an appointment?</Say>
              </Gather>
              <Say>I didn't hear that. Goodbye!</Say>
            </Response>
            """.formatted(callbackBaseUrl);
        } else {
            reply = """
            <Response>
              <Say voice="alice">Hope you're doing well. Take care. Goodbye!</Say>
            </Response>
            """;
        }

        response.setContentType("application/xml");
        response.getWriter().write(reply.trim());
    }

    private String escapeXml(String input) {
        return input
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");
    }
    /**
     * Step 3: Capture yes/no to booking
     */
    @PostMapping(value = "/step3", produces = MediaType.APPLICATION_XML_VALUE)
    public void handleBookingDecision(@RequestParam(value = "SpeechResult", required = false) String speech,
                                      HttpServletResponse response) throws IOException {
        String reply;

        if (speech != null && speech.toLowerCase().contains("yes")) {
            reply = """
                <Response>
                  <Say voice="alice">Great! Your appointment is booked. Have a nice day!</Say>
                </Response>
                """;
        } else {
            reply = """
                <Response>
                  <Say voice="alice">No problem. Let us know if you change your mind. Goodbye!</Say>
                </Response>
                """;
        }

        response.setContentType("application/xml");
        response.getWriter().write(reply.trim());
    }
}