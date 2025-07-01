package com.example.demo.controller.dto;

import java.util.List;

public record PokemonContractResponse(
        String nome,
        int altura,
        int peso,
        List<String> tipos
) {}
