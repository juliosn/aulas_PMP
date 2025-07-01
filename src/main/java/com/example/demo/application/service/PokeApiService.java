package com.example.demo.application.service;

import com.example.demo.controller.dto.PokemonContractResponse;
import com.example.demo.domain.ports.PokeApiPort;
import org.springframework.stereotype.Service;

@Service
public class PokeApiService {

    private final PokeApiPort apiPort;

    public PokeApiService(PokeApiPort apiPort) {
        this.apiPort = apiPort;
    }

    public PokemonContractResponse buscar(String nomeOuId) {
        return apiPort.buscarPokemon(nomeOuId);
    }
}
