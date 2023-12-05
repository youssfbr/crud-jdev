package com.github.youssfbr.crud.repositories;

import com.github.youssfbr.crud.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE TRIM(u.name) ILIKE %:name%")
    List<User> findByNameContainingIgnoreCase(String name);
}
