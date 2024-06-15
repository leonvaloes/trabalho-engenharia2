package com.backend.com.backend.model.repositorios;

import com.backend.com.backend.model.entidades.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLoginAndPassword(String login, String password);

    User findByLogin(String username);
}
