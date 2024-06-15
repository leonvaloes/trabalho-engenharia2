package com.backend.com.backend.model.entidades.user;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String login;
    private String password;
}
