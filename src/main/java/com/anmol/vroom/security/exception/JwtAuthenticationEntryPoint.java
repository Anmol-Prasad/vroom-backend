package com.anmol.vroom.security.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    // Tool to convert POJO to JSON
    // Deserializer : Converting JSON to POJO
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // Setting response headers
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        // Creating response object body
        SecurityErrorResponse error = new SecurityErrorResponse(
                401,
                "Unauthorized",
                authException.getMessage()
        );

        String jsonResponse = objectMapper.writeValueAsString(error);

        // Setting response body
        // Serialization : Converting POJO to JSON
        response.getWriter().write(jsonResponse);
    }


}
