package com.anmol.vroom.security.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SecurityErrorResponse {
    private int status;
    private String error;
    private String message;
}
