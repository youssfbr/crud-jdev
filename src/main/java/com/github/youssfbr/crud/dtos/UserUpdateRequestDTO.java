package com.github.youssfbr.crud.dtos;

import lombok.Getter;

@Getter
public class UserUpdateRequestDTO {

    private Long id;

    private String name;

    private Integer age;
}
