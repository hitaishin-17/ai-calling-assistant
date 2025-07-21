package com.hitaishi.aicall.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for incoming call request.
 * Uses Lombok to generate boilerplate code.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CallRequest {
    private String recipient;
    private String purpose;
}