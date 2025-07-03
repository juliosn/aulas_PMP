package com.example.demo.service;

import com.example.demo.entity.Pokemon;
import com.example.demo.entity.User;
import com.example.demo.exception.NotFoundException;
import com.example.demo.integration.PokemonIntegration;
import com.example.demo.repository.UserRepository;

public class UserPokemonService {
    private final UserRepository repository;
    private final PokemonIntegration integration;

    public UserPokemonService(
            UserRepository repository,
            PokemonIntegration integration) {
        this.repository = repository;
        this.integration = integration;
    }

    public User register(User user) {
        try {
            User updateUser = repository.findByName(user.nome());
            return save(updateUser.id(), user);
        } catch (NotFoundException ex) {
            return save(user);
        }
    }

    private User save(User user) {
        return save(user.id(), user);
    }

    private User save(final String id, User user) {
        Pokemon pokemon = integration.getPokemon(user.pokemonPreferido().nome());
        return repository.save(new User(
                id,
                user.nome(),
                user.endereco(),
                user.email(),
                user.cidade(),
                pokemon));
    }


}
