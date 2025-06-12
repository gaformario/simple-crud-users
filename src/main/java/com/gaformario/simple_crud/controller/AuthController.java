package com.gaformario.simple_crud.controller;

import com.gaformario.simple_crud.business.AuthService;
import com.gaformario.simple_crud.business.UserService;
import com.gaformario.simple_crud.controller.dto.AuthRequestDTO;
import com.gaformario.simple_crud.controller.dto.AuthResponseDTO;
import com.gaformario.simple_crud.controller.dto.UserRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    @Operation(summary = "Login user credentials")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO authRequestDTO) {
        String token = authService.authenticate(authRequestDTO.getEmail(), authRequestDTO.getPassword());
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }

    @PostMapping("/register")
    @Operation(summary = "Register user")
    public ResponseEntity<Void> createUser(@RequestBody UserRequestDTO userDTO) {
        userService.saveUser(userDTO);
        return ResponseEntity.ok().build();
    }

}
