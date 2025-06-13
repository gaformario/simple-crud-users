package com.gaformario.simple_crud.controller.dto;

import com.gaformario.simple_crud.infrastructure.entitys.UserRole;

public record UserResponseDTO(Integer id, String name, String email, UserRole role) {}
