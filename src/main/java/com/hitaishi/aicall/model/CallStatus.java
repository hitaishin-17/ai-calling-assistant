package com.hitaishi.aicall.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Represents the status of a call made through the AI Calling Assistant.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CallStatus {
    private String callSid;
    private String recipient;
    private String status;           // e.g., "initiated", "completed", "failed"
    private String message;          // optional log or error
    private LocalDateTime timestamp; // when the call was triggered
}