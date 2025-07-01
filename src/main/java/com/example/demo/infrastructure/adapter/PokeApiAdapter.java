package com.example.demo.infrastructure.adapter;

import com.example.demo.controller.dto.PokemonContractResponse;
import com.example.demo.domain.ports.PokeApiPort;
import com.example.demo.infrastructure.response.PokeApiResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PokeApiAdapter implements PokeApiPort {

    private final PokeApiFeignAdapter feignAdapter;

    public PokeApiAdapter(PokeApiFeignAdapter feignAdapter) {
        this.feignAdapter = feignAdapter;
    }

    @Override
    public PokemonContractResponse buscarPokemon(String nomeOuId) {
        PokeApiResponse api = feignAdapter.buscarPokemon(nomeOuId);

        List<String> tipos = api.types.stream()
                .map(t -> t.type.name)
                .collect(Collectors.toList());

        return new PokemonContractResponse(
                api.name,
                api.height,
                api.weight,
                tipos
        );
    }
}
