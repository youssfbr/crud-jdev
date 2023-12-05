package com.github.youssfbr.crud.services;

import com.github.youssfbr.crud.dtos.UserCreateRequestDTO;
import com.github.youssfbr.crud.dtos.UserResponseDTO;

import java.util.List;

public interface IUserService {

    List<UserResponseDTO> getAllUsers();
    UserResponseDTO userCreate(UserCreateRequestDTO userCreateRequestDTO);
}
