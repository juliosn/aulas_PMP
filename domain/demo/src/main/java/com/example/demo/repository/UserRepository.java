package com.example.demo.repository;

import com.example.demo.entity.Cliente;

public interface UserRepository {
    Cliente save(Cliente cliente);

    Cliente findByName(String name);

    Cliente findById(String id);

    void delete(String id);
}
