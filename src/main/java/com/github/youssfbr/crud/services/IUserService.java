package com.github.youssfbr.crud.services;

import com.github.youssfbr.crud.dtos.UserCreateRequestDTO;
import com.github.youssfbr.crud.dtos.UserResponseDTO;
import com.github.youssfbr.crud.dtos.UserUpdateRequestDTO;

import java.util.List;

public interface IUserService {

    List<UserResponseDTO> getAllUsers();
    UserResponseDTO findUserById(Long id);
    List<UserResponseDTO> findByName(String name);
    UserResponseDTO userCreate(UserCreateRequestDTO userCreateRequestDTO);
    UserResponseDTO userUpdate(UserUpdateRequestDTO userUpdateRequestDTO);
    void delete(Long id);
}
