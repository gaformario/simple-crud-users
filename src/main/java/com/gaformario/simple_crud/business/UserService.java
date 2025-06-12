package com.gaformario.simple_crud.business;

import com.gaformario.simple_crud.controller.dto.UserRequestDTO;
import com.gaformario.simple_crud.controller.dto.UserResponseDTO;
import com.gaformario.simple_crud.infrastructure.entitys.User;
import com.gaformario.simple_crud.infrastructure.repository.UserRepository;
import com.gaformario.simple_crud.mapper.UserMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, UserMapper userMapper) {
        this.repository = repository;
        this.userMapper = userMapper;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public void saveUser(UserRequestDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.saveAndFlush(user);
    }

    public List<UserResponseDTO> getUsers() {
        return userMapper.toDTOList(repository.findAll());
    }

    public UserResponseDTO getUserByEmail(String email) {
        User user = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email not found"));
        return userMapper.toDTO(user);
    }

    public void deleteUserByEmail(String email) {
        repository.deleteByEmail(email);
    }

    public void updateUserById(Integer id, UserRequestDTO user) {
        User userEntity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User id not found"));

        userMapper.updateUserFromDTO(user, userEntity);

        if (user.password() != null && !user.password().isEmpty()) {
            userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        }

        repository.saveAndFlush(userEntity);
    }
}