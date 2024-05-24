package com.backend.com.backend.model.entidades.user;

public record RegisterDTO(String login, String password, UserRole role) {
}
