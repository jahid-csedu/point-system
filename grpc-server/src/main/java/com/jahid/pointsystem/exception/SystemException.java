package com.jahid.pointsystem.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemException extends RuntimeException{
    private String code;
    private String message;

    public SystemException(Error error) {
        this(error.getCode(), error.getMessage());
    }
}
