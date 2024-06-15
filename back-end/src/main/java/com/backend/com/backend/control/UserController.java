package com.backend.com.backend.control;

import com.backend.com.backend.model.entidades.user.User;
import com.backend.com.backend.model.entidades.user.UserPermission;
import com.backend.com.backend.model.entidades.user.UserRole;
import com.backend.com.backend.model.services.AuthService;
import com.backend.com.backend.model.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    // Endpoint para criar um novo usuário
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    // Endpoint para buscar um usuário pelo ID
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para atualizar as permissões de um usuário
    @PutMapping("/{userId}/permissions")
    public ResponseEntity<User> updatePermissions(@PathVariable Long userId,
                                                  @RequestBody Set<UserPermission> permissions) {
        User currentUser = authService.getCurrentUser();
        User updatedUser = userService.updateUserPermissions(userId, permissions, currentUser);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para atualizar o papel (role) de um usuário
    @PutMapping("/{userId}/role")
    public ResponseEntity<User> updateRole(@PathVariable Long userId,
                                           @RequestParam UserRole role) {
        User currentUser = authService.getCurrentUser();
        User updatedUser = userService.updateUserRole(userId, role, currentUser);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para deletar um usuário pelo ID
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        User currentUser = authService.getCurrentUser();
        userService.deleteUser(userId, currentUser);
        return ResponseEntity.noContent().build();
    }
}
