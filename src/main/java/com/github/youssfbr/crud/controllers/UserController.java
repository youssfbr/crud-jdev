package com.github.youssfbr.crud.controllers;

import com.github.youssfbr.crud.dtos.UserCreateRequestDTO;
import com.github.youssfbr.crud.dtos.UserResponseDTO;
import com.github.youssfbr.crud.dtos.UserUpdateRequestDTO;
import com.github.youssfbr.crud.services.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@Tag(name = "Usuários")
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final IUserService userService;
    @GetMapping
    @Operation(summary = "Listar", description = "Lista todos os usuários")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Listar por ID", description = "Lista um usuário pelo ID (Número)")
    public ResponseEntity<UserResponseDTO> findUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Listar por nome", description = "Lista um usuário pelo nome")
    public ResponseEntity<List<UserResponseDTO>> findByName(@PathVariable String name) {
        return ResponseEntity.ok(userService.findByName(name.trim()));
    }

    @PostMapping
    @Operation(summary = "Salvar", description = "Salva um usuário")
    public ResponseEntity<UserResponseDTO> userCreate(@RequestBody UserCreateRequestDTO userCreateRequestDTO) {

        final UserResponseDTO userCreated = userService.userCreate(userCreateRequestDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreated.getId())
                .toUri();

        return ResponseEntity.created(location).body(userCreated);
    }

    @PutMapping
    @Operation(summary = "Atualizar", description = "Atualiza um usuário")
    public ResponseEntity<UserResponseDTO> userUpdate(@RequestBody UserUpdateRequestDTO userUpdateRequestDTO) {
        return ResponseEntity.ok(userService.userUpdate(userUpdateRequestDTO));
    }

    @DeleteMapping
    @Operation(summary = "Excluir", description = "Exclui um usuário")
    public ResponseEntity<Void> delete(@RequestBody UserUpdateRequestDTO userUpdateRequestDTO) {
        userService.delete(userUpdateRequestDTO.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

