package com.backend.com.backend.control;

import com.backend.com.backend.model.entidades.user.AuthenticationDTO;
import com.backend.com.backend.model.entidades.user.LoginResponseDTO;
import com.backend.com.backend.model.entidades.user.RegisterDTO;
import com.backend.com.backend.model.entidades.user.User;
import com.backend.com.backend.model.repositorios.UserRepository;
import com.backend.com.backend.security.TokenService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("auth")
public class AuthenticationControl {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationControl.class);

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        logger.info("Received login request for user: {}", data.login());

        // Buscar o usuário pelo nome de usuário no banco de dados
        User user = (User) repository.findByLogin(data.login());

        // Verificar se o usuário existe e se está ativo
        if (user != null && user.isActive()) {
            // Verificar a senha
            if (new BCryptPasswordEncoder().matches(data.password(), user.getPassword())) {
                // Autenticar o usuário e gerar o token
                var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
                var auth = this.authenticationManager.authenticate(usernamePassword);
                var token = tokenService.generateToken((User) auth.getPrincipal());

                logger.info("User '{}' authenticated successfully. Token: {}", data.login(), token);
                return ResponseEntity.ok(new LoginResponseDTO(token));
            } else {
                logger.warn("User '{}' authentication failed: invalid password.", data.login());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
            }
        } else {
            logger.warn("User '{}' authentication failed: user not found or inactive.", data.login());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
        }
    }
    @GetMapping("/roles")
    public ResponseEntity getUserRoles(Principal principal) {
        logger.info("Retrieving roles for user: {}", principal.getName());

        // Verificar se o usuário está autenticado
        if (principal != null) {
            // Buscar o usuário pelo nome de usuário no banco de dados
            User user = (User) repository.findByLogin(principal.getName());

            // Verificar se o usuário existe
            if (user != null) {
                // Retornar os papéis do usuário
                return ResponseEntity.ok(user.getRole());
            } else {
                logger.warn("User '{}' not found.", principal.getName());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
            }
        } else {
            logger.warn("No authenticated user found.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No authenticated user found.");
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        logger.info("Received registration request for user: {}", data.login());

        if (this.repository.findByLogin(data.login()) != null) {
            logger.warn("User '{}' already exists. Registration failed.", data.login());
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());

        this.repository.save(newUser);
        logger.info("User '{}' registered successfully.", data.login());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/all-users")
    public ResponseEntity getAllUsers() {
        logger.info("Retrieving all users from the database.");
        List<User> users = repository.findAll();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/deactivate-user/{userId}")
    public ResponseEntity deactivateUser(@PathVariable String userId, Principal principal) {
        logger.info("Deactivating user with ID: {}", userId);

        if (principal != null && principal.getName().equals(userId)) {
            logger.warn("User '{}' is attempting to deactivate themselves. Deactivation not allowed.", userId);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Cannot deactivate yourself.");
        }

        Optional<User> optionalUser = repository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setActive(false);
            repository.save(user);
            logger.info("User with ID '{}' deactivated successfully.", userId);
            return ResponseEntity.ok().build();
        } else {
            logger.warn("User with ID '{}' not found. Deactivation failed.", userId);
            return ResponseEntity.notFound().build();
        }
    }

}
