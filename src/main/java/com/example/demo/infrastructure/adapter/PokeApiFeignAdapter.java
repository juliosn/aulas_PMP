package com.example.demo.infrastructure.adapter;

import com.example.demo.infrastructure.response.PokeApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "pokeapi", url = "${pokeapi.url}")
public interface PokeApiFeignAdapter {

    @GetMapping("/pokemon/{nomeOuId}")
    PokeApiResponse buscarPokemon(@PathVariable String nomeOuId);
}
