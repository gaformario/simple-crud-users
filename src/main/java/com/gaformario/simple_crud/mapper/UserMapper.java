package com.gaformario.simple_crud.mapper;

import com.gaformario.simple_crud.infrastructure.entitys.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    void updateUserFromEntity(User source, @MappingTarget User target);
}
