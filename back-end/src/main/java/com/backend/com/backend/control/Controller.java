package com.backend.com.backend.control;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class Controller {

    @GetMapping("get")
    public String get() {
        // Obtém o contexto de autenticação atual
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Verifica se o usuário atual tem o papel ADMIN
        if (authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ADMIN"))) {
            return "Acesso permitido para administradores";
        } else {
            return "Acesso negado. Você não é um administrador.";
        }
    }
}
