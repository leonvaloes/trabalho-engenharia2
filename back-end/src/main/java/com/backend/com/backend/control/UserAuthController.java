package com.backend.com.backend.control;

import com.backend.com.backend.model.entidades.user.LoginRequestDTO;
import com.backend.com.backend.model.entidades.user.User;
import com.backend.com.backend.model.entidades.user.UserLoginResponse;
import com.backend.com.backend.model.repositorios.UserRepository;
import com.backend.com.backend.security.JwtIssuer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class UserAuthController {

    private final JwtIssuer jwtIssuer;
    private final UserRepository userRepository;

    @PostMapping("/auth/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody LoginRequestDTO login) {
        User thisUser = userRepository.findByLoginAndPassword(login.getLogin(), login.getPassword());

        if (thisUser != null) {
            // Check if the user is an admin
            List<String> roles = thisUser.isAdmin() ? List.of("ADMIN", "USER") : List.of("USER");
            String token = jwtIssuer.issue(1L, thisUser.getLogin(), roles);

            // Return the token in the response
            return ResponseEntity.accepted().body(UserLoginResponse.builder()
                    .token(token)
                    .build());
        } else {
            // Return a 401 Unauthorized response if the login or password is incorrect
            return ResponseEntity.status(401).build();
        }
    }
}
