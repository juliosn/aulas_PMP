package com.example.demo.controller;

import com.example.demo.application.service.PokeApiService;
import com.example.demo.controller.dto.PokemonContractResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pokemon")
public class PokeApiController {

    private final PokeApiService service;

    public PokeApiController(PokeApiService service) {
        this.service = service;
    }

    @GetMapping("/{nomeOuId}")
    public ResponseEntity<?> get(@PathVariable String nomeOuId) {
        try {
            return ResponseEntity.ok(service.buscar(nomeOuId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Pokémon não encontrado: " + nomeOuId);
        }
    }
}
