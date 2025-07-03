package com.example.demo.repository;

import com.example.demo.entity.User;

public interface UserRepository {
    User save(User user);

    User findById(String id);

    User findByName(String name);

    void delete(String id);
}
