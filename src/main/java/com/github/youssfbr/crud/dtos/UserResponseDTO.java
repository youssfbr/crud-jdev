package com.github.youssfbr.crud.dtos;

import com.github.youssfbr.crud.entities.User;
import lombok.Getter;

@Getter
public class UserResponseDTO {

    private Long id;

    private String name;

    private Integer age;

    public UserResponseDTO(User entity) {
        id = entity.getId();
        name = entity.getName();
        age = entity.getAge();
    }
}
