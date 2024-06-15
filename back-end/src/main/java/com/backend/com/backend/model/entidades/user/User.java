package com.backend.com.backend.model.entidades.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@Getter
@Setter // Adiciona os métodos setters
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // Aqui, usamos Long em vez de String

    private String login;
    private String password;
    private boolean active;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @ElementCollection(targetClass = UserPermission.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_permissions", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<UserPermission> permissions = new HashSet<>();

    public User(String login, String password, UserRole role) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.active = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
        authorities.addAll(permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList()));
        return authorities;
    }


    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    // Métodos para adicionar e remover permissões
    public void addPermission(UserPermission permission) {
        permissions.add(permission);
    }

    public void removePermission(UserPermission permission) {
        permissions.remove(permission);
    }

    // Adicionando setters para role e permissions
    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setPermissions(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public boolean isAdmin() {
        if(role == UserRole.ADMIN)
            return true;
        return false;
    }
    public boolean hasPermission(UserPermission permission) {
        return permissions.contains(permission);
    }
}
