package com.emcaras.eventos.security.dto;

import lombok.Data;

@Data
public class JwtResponseDto {
    private String accessToken;
    private String tokenType;

    public JwtResponseDto(String token){
        this.accessToken = token;
        this.tokenType = "Bearer ";
    }
}
