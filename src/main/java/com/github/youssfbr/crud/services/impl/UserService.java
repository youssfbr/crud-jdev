package com.github.youssfbr.crud.services.impl;

import com.github.youssfbr.crud.dtos.UserCreateRequestDTO;
import com.github.youssfbr.crud.dtos.UserResponseDTO;
import com.github.youssfbr.crud.dtos.UserUpdateRequestDTO;
import com.github.youssfbr.crud.entities.User;
import com.github.youssfbr.crud.repositories.IUserRepository;
import com.github.youssfbr.crud.services.IUserService;
import com.github.youssfbr.crud.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private static final String NOT_FOUND_MESSAGE = "Resource not found with id ";

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDTO::new)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO findUserById(Long id) {
        return userRepository.findById(id)
                .map(UserResponseDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_MESSAGE + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDTO> findByName(String name) {
        return userRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(UserResponseDTO::new)
                .toList();
    }

    @Override
    @Transactional
    public UserResponseDTO userCreate(UserCreateRequestDTO userCreateRequestDTO) {
        final User userToCreate = new User(userCreateRequestDTO);
        final User userCreated = userRepository.save(userToCreate);
        return new UserResponseDTO(userCreated);
    }

    @Override
    @Transactional
    public UserResponseDTO userUpdate(UserUpdateRequestDTO userUpdateRequestDTO) {

        final User userToUpdate = checkExistsUser(userUpdateRequestDTO.getId());
        BeanUtils.copyProperties(userUpdateRequestDTO, userToUpdate);

        final User userUpdated = userRepository.save(userToUpdate);

        return new UserResponseDTO(userUpdated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        checkExistsUser(id);
        userRepository.deleteById(id);
    }

    private User checkExistsUser(Long id) {
        return userRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_MESSAGE + id));
    }
}
