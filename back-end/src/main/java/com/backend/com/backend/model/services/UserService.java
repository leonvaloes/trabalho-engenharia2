package com.backend.com.backend.model.services;

import com.backend.com.backend.model.entidades.user.User;
import com.backend.com.backend.model.entidades.user.UserPermission;
import com.backend.com.backend.model.entidades.user.UserRole;
import com.backend.com.backend.model.repositorios.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User updateUserPermissions(Long userId, Set<UserPermission> permissions, User currentUser) {
        User userToUpdate = userRepository.findById(userId).orElse(null);

        if (userToUpdate == null) {
            return null;
        }

        // Verificar se o usuário atual tem permissão para atualizar permissões
        if (currentUser.isAdmin() || currentUser.getPermissions().contains(UserPermission.UPDATE)) {
            userToUpdate.getPermissions().clear();
            userToUpdate.getPermissions().addAll(permissions);
            userRepository.save(userToUpdate);
            return userToUpdate;
        }

        return null; // Retornar null ou lançar exceção se não tiver permissão
    }

    public User updateUserRole(Long userId, UserRole role, User currentUser) {
        User userToUpdate = userRepository.findById(userId).orElse(null);

        if (userToUpdate == null) {
            return null;
        }

        // Verificar se o usuário atual tem permissão para atualizar o papel
        if (currentUser.isAdmin() || currentUser.getPermissions().contains(UserPermission.UPDATE)) {
            userToUpdate.setRole(role);
            userRepository.save(userToUpdate);
            return userToUpdate;
        }

        return null; // Retornar null ou lançar exceção se não tiver permissão
    }

    public void deleteUser(Long userId, User currentUser) {
        if (currentUser.isAdmin() || currentUser.getPermissions().contains(UserPermission.DELETE)) {
            userRepository.deleteById(userId);
        }
        // Caso não tenha permissão, pode lançar uma exceção ou retornar algum erro
    }
}
