package com.gaformario.simple_crud.business;

import com.gaformario.simple_crud.controller.dto.UserDTO;
import com.gaformario.simple_crud.infrastructure.entitys.User;
import com.gaformario.simple_crud.infrastructure.repository.UserRepository;
import com.gaformario.simple_crud.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    private final UserMapper userMapper;

    public UserService(UserRepository repository, UserMapper userMapper) {
        this.repository = repository;
        this.userMapper = userMapper;
    }

    public void saveUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        repository.saveAndFlush(user);
    }

    public UserDTO getUserByEmail(String email) {
        User user = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email not found"));
        return userMapper.toDTO(user);
    }

    public void deleteUserByEmail(String email) {
        repository.deleteByEmail(email);
    }

    public void updateUserById(Integer id, UserDTO user) {
        User userEntity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User id not found"));

        userMapper.updateUserFromDTO(user, userEntity);

        repository.saveAndFlush(userEntity);
    }

}
