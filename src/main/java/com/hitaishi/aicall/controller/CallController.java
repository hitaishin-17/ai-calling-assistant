package com.hitaishi.aicall.controller;

import com.hitaishi.aicall.model.CallRequest;
import com.hitaishi.aicall.service.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller to handle incoming API requests to trigger AI-powered phone calls.
 */
@RestController
@RequestMapping("/api/call")
public class CallController {

    @Autowired
    private CallService callService;

    /**
     * POST endpoint to initiate an AI-generated call.
     *
     * @param request JSON body with recipient phone number and purpose
     * @return A response message indicating the result of the call attempt
     */
    @PostMapping
    public ResponseEntity<String> initiateCall(@RequestBody CallRequest request) {

        System.out.println("Received call request to: " + request.getRecipient() + " for: " + request.getPurpose());
        if (request.getRecipient() == null || request.getPurpose() == null) {
            return ResponseEntity.badRequest().body("Missing recipient or purpose.");
        }

        String result = callService.handleCall(request);
        return ResponseEntity.ok(result);
    }

    /**
     * Simple health check endpoint
     */
    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("AI Calling Service is up.");
    }
}
