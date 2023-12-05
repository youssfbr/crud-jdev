package com.github.youssfbr.crud.entities;

import com.github.youssfbr.crud.dtos.UserCreateRequestDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "tb_user")
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60, nullable = false)
    private String name;

    private Integer age;

    public User(UserCreateRequestDTO userCreateRequestDTO) {
        this.name = userCreateRequestDTO.getName();
        this.age = userCreateRequestDTO.getAge();
    }
}
