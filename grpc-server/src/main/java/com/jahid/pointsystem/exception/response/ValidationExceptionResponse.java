package com.jahid.pointsystem.exception.response;

import java.util.Map;

public class ValidationExceptionResponse {
    private String errorCode;
    private Map<String, String> errorDetails;

    public ValidationExceptionResponse(String errorCode, Map<String, String> errorDetails) {
        this.errorCode = errorCode;
        this.errorDetails = errorDetails;
    }
}
