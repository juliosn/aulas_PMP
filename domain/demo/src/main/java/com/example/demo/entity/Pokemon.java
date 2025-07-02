package com.example.demo.entity;

import java.util.List;

public record Pokemon(
        String nome,
        Integer peso,
        List<PokemonType> tipos
) {
}
