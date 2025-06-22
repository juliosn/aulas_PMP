package com.example.demo.application.service;

import com.example.demo.controller.dto.PokemonResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class PokeApiService {

    private final RestTemplate restTemplate;

    public PokeApiService() {
        this.restTemplate = new RestTemplate();
    }

    @Cacheable("pokemon")
    public PokemonResponse buscarPokemon(String nomeOuId) {
        String url = "https://pokeapi.co/api/v2/pokemon/" + nomeOuId.toLowerCase();
        try {
            return restTemplate.getForObject(url, PokemonResponse.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new RuntimeException("Pokémon não encontrado: " + nomeOuId);
        }
    }
}
