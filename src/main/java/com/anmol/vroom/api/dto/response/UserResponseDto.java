package com.anmol.vroom.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// Response DTOs need AllArgs since responses are immutable
// Requests need setter since requests ane mutable and Spring needs it internally to convert JSON -> Object
@Getter
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Double walletBalance;
}
