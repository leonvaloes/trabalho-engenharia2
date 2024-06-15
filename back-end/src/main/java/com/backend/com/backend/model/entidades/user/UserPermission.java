package com.backend.com.backend.model.entidades.user;

public enum UserPermission {
    READ("read"),
    UPDATE("update"),
    DELETE("delete"),
    CREATE("create");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
