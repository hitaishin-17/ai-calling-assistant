package com.hitaishi.aicall.twilio;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;

/**
 * Client to initiate voice calls using Twilio.
 */
@Component
public class TwilioClient {

    @Value("${twilio.account_sid}")
    private String accountSid;

    @Value("${twilio.auth_token}")
    private String authToken;

    @Value("${twilio.from_number}")
    private String fromNumber;

    @Value("${twilio.twiml_base_url}")
    private String twimlBaseUrl; // e.g., http://yourdomain.com/api/twiml?message=

    /**
     * Initiates a phone call to the given number with the AI-generated message.
     *
     * @param to      Recipient phone number
     * @param message Text message to convert to speech
     * @return Call SID from Twilio
     */
    public String makeCall(String to, String message) {
        Twilio.init(accountSid, authToken);

        String encodedMsg = message.replace(" ", "%20"); // basic URL encoding
        // ✅ This hits /api/twiml/start which is POST and supports full voice flow
        URI twimlUri = URI.create(twimlBaseUrl + "/start");

        Call call = Call.creator(
                new PhoneNumber(to),
                new PhoneNumber(fromNumber),
                twimlUri
        ).create();

        return call.getSid();
    }
}