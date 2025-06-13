package com.gaformario.simple_crud.controller.dto;

import com.gaformario.simple_crud.infrastructure.entitys.UserRole;

public record UserRequestDTO(String name, String email, String password, UserRole role) {}

