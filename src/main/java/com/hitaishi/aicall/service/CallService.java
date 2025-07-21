package com.hitaishi.aicall.service;

import com.hitaishi.aicall.ai.GroqApiClient;
import com.hitaishi.aicall.model.CallRequest;
import com.hitaishi.aicall.model.CallStatus;
import com.hitaishi.aicall.twilio.TwilioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Handles the flow: input prompt → AI script → outbound call → status log
 */
@Service
public class CallService {

    @Autowired
    private GroqApiClient groqApiClient;

    @Autowired
    private TwilioClient twilioClient;

    public String handleCall(CallRequest request) {
        try {
            // Step 1: Generate call script using Groq
            String aiScript = groqApiClient.generateCallScript(request.getPurpose());

            if (aiScript == null || aiScript.isBlank()) {
                return "Failed to generate call script.";
            }

            // Step 2: Make the call using Twilio
            String callSid = twilioClient.makeCall(request.getRecipient(), aiScript);
            System.out.println(aiScript);

            // Step 3: Optional - store or return status
            CallStatus status = new CallStatus(
                    callSid,
                    request.getRecipient(),
                    "initiated",
                    "Call triggered successfully",
                    LocalDateTime.now()
            );

            // TODO: save status to DB (optional)

            return "Call initiated successfully (SID: " + callSid + ")";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error occurred while processing the call: " + ex.getMessage();
        }
    }
}