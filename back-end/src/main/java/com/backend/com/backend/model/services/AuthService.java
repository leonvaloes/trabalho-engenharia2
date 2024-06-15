package com.backend.com.backend.model.services;

import com.backend.com.backend.model.entidades.user.User;
import com.backend.com.backend.model.entidades.user.UserPermission;
import com.backend.com.backend.model.repositorios.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            return userRepository.findByLogin(username);
        } else {
            return null;
        }
    }

    public boolean hasPermission(UserPermission permission) {
        User currentUser = getCurrentUser();
        return currentUser != null && (currentUser.isAdmin() || currentUser.getPermissions().contains(permission));
    }
}
