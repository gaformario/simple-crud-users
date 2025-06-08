package com.gaformario.simple_crud.mapper;

import com.gaformario.simple_crud.controller.dto.UserDTO;
import com.gaformario.simple_crud.infrastructure.entitys.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);
    User toEntity(UserDTO dto);

    void updateUserFromDTO(UserDTO source, @MappingTarget User target);
}
