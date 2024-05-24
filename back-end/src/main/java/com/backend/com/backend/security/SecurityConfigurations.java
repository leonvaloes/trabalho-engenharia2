package com.backend.com.backend.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfigurations.class);

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        logger.info("Configuring security filter chain...");

        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/TipoProdutos/get-all-Tipoproduto").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/auth/deactivate-user/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/auth/all-users").permitAll()
                        .requestMatchers(HttpMethod.POST, "/Produtos").permitAll()
                        .requestMatchers(HttpMethod.POST, "/parametrizacao").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/parametrizacao/1").permitAll()
                        .requestMatchers(HttpMethod.GET, "/parametrizacao").permitAll()
                        .requestMatchers(HttpMethod.GET, "/parametrizacao/logo-grande").permitAll()
                        .requestMatchers(HttpMethod.GET, "/parametrizacao/logo-pequeno").permitAll()
                        .requestMatchers(HttpMethod.GET, "/Produtos/get-um-produto").permitAll()
                        .requestMatchers(HttpMethod.GET, "/Produtos/get-produto-nome").permitAll()
                        .requestMatchers(HttpMethod.GET, "/TipoProdutos").permitAll()
                        .requestMatchers(HttpMethod.POST, "/TipoProdutos").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        logger.info("Creating authentication manager...");
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        logger.info("Creating password encoder...");
        return new BCryptPasswordEncoder();
    }
}
