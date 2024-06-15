package com.backend.com.backend.model.entidades.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserLoginResponse {
    private String token;
}
