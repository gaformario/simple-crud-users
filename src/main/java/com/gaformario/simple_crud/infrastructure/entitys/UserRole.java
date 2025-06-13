package com.gaformario.simple_crud.infrastructure.entitys;

public enum UserRole {
    ADMIN("ADMIN"),
    USER("USER"),
    MODERATOR("MODERATOR");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}