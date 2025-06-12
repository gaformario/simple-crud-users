package com.gaformario.simple_crud.mapper;

import com.gaformario.simple_crud.controller.dto.UserResponseDTO;
import com.gaformario.simple_crud.controller.dto.UserRequestDTO;
import com.gaformario.simple_crud.infrastructure.entitys.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    UserResponseDTO toDTO(User user);
    User toEntity(UserRequestDTO dto);
    List<UserResponseDTO> toDTOList(List<User> users);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    void updateUserFromDTO(UserRequestDTO source, @MappingTarget User target);
}