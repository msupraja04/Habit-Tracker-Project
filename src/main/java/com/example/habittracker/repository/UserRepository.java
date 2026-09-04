package com.example.habittracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.habittracker.model.User;

public interface UserRepository
       extends JpaRepository<User, Integer>{

    User findByEmail(String email);
    User findById(int id);

}