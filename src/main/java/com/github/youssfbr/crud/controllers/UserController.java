package com.github.youssfbr.crud.controllers;

import com.github.youssfbr.crud.dtos.UserCreateRequestDTO;
import com.github.youssfbr.crud.dtos.UserResponseDTO;
import com.github.youssfbr.crud.dtos.UserUpdateRequestDTO;
import com.github.youssfbr.crud.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final IUserService userService;
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<UserResponseDTO>> findByName(@PathVariable String name) {
        return ResponseEntity.ok(userService.findByName(name.trim()));
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> userCreate(@RequestBody UserCreateRequestDTO userCreateRequestDTO) {

        final UserResponseDTO userCreated = userService.userCreate(userCreateRequestDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreated.getId())
                .toUri();

        return ResponseEntity.created(location).body(userCreated);
    }

    @PutMapping
    public ResponseEntity<UserResponseDTO> userUpdate(@RequestBody UserUpdateRequestDTO userUpdateRequestDTO) {
        return ResponseEntity.ok(userService.userUpdate(userUpdateRequestDTO));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody UserUpdateRequestDTO userUpdateRequestDTO) {
        userService.delete(userUpdateRequestDTO.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

