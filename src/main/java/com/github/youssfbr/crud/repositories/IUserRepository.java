package com.github.youssfbr.crud.repositories;

import com.github.youssfbr.crud.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
}
