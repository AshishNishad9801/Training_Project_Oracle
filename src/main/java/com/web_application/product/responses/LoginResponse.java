package com.web_application.product.responses;

import com.web_application.product.entity.RoleEnum;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;

    private long expiresIn;

    private RoleEnum Role;
}