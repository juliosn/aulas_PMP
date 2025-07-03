package com.example.demo.entity;

public record User(
        String id,
        String nome,
        String endereco,
        String email,
        String cidade,
        Pokemon pokemonPreferido
) {
}