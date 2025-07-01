package com.example.demo.domain.ports;

import com.example.demo.controller.dto.PokemonContractResponse;

public interface PokeApiPort {
    PokemonContractResponse buscarPokemon(String nomeOuId);
}
