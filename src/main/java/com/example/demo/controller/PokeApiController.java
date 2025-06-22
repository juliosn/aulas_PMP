package com.example.demo.controller;

import com.example.demo.controller.dto.PokemonResponse;
import com.example.demo.application.service.PokeApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pokemon")
public class PokeApiController {

    private final PokeApiService pokeApiService;

    public PokeApiController(PokeApiService pokeApiService) {
        this.pokeApiService = pokeApiService;
    }

    @GetMapping("/{nomeOuId}")
    public ResponseEntity<?> getPokemon(@PathVariable String nomeOuId) {
        try {
            PokemonResponse response = pokeApiService.buscarPokemon(nomeOuId);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
