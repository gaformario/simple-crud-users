package com.gaformario.simple_crud.controller;

import com.gaformario.simple_crud.business.UserService;
import com.gaformario.simple_crud.controller.dto.UserRequestDTO;
import com.gaformario.simple_crud.controller.dto.UserResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "Users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @Operation(summary = "Get all users")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/{email}")
    @Operation(summary = "Get user by email")
    public ResponseEntity<UserResponseDTO> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @GetMapping("/profile")
    @Operation(summary = "Get own profile (Authenticated users)")
    public ResponseEntity<UserResponseDTO> getProfile(Authentication authentication) {
        String userId = authentication.getName();
        UserResponseDTO userProfile = userService.getUserById(Integer.valueOf(userId));
        return ResponseEntity.ok(userProfile);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update user")
    public ResponseEntity<Void> updateUser(@PathVariable Integer id, @RequestBody UserRequestDTO userDTO) {
        userService.updateUserById(id, userDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Delete user by email")
    public ResponseEntity<Void> deleteUserByEmail(@RequestParam String email) {
        userService.deleteUserByEmail(email);
        return ResponseEntity.ok().build();
    }
}
