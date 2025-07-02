package com.example.demo.entity;

public record Cliente(
        String id,
        String nome,
        String endereco,
        String email,
        String cidade,
        Pokemon pokemon
) {
}
