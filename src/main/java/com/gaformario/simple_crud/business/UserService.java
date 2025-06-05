package com.gaformario.simple_crud.business;

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

    public void saveUser(User user) {
        repository.saveAndFlush(user);
    }

    public User getUserByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() -> new RuntimeException("Email not found"));
    }

    public void deleteUserByEmail(String email) {
        repository.deleteByEmail(email);
    }

    public void updateUserById(Integer id, User user) {
        User userEntity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User id not found"));

        userMapper.updateUserFromEntity(user, userEntity);

        repository.saveAndFlush(userEntity);
    }

}
