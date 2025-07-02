package com.example.demo.service;

import com.example.demo.entity.Cliente;
import com.example.demo.entity.Pokemon;
import com.example.demo.exception.BadRequestException;
import com.example.demo.integration.PokemonIntegration;
import com.example.demo.repository.UserRepository;

public class ClientePokemonService {
    private final UserRepository repository;
    private final PokemonIntegration integration;

    public ClientePokemonService(
            UserRepository repository,
            PokemonIntegration integration) {
        this.repository = repository;
        this.integration = integration;
    }

    public Cliente registerUserWithPokemon(Cliente cliente) {
        try {
            Cliente updateCliente = repository.findByName(cliente.nome());
            return save(updateCliente.id(), cliente);
        } catch (BadRequestException ex) {
            return save(cliente);
        }
    }

    private Cliente save(Cliente cliente) {
        return save(cliente.id(), cliente);
    }

    private Cliente save(final String id, Cliente cliente) {
        Pokemon pokemon = integration.getPokemon(cliente.pokemon().nome());
        return repository.save(new Cliente(
                id,
                cliente.nome(),
                cliente.endereco(),
                cliente.email(),
                cliente.cidade(),
                pokemon));
    }

}