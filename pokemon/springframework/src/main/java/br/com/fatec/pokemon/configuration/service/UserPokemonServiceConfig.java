package br.com.fatec.pokemon.configuration.service;

import br.com.fatec.pokemon.integration.CepIntegration;
import br.com.fatec.pokemon.repository.UserRepository;
import br.com.fatec.pokemon.service.ClientService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserPokemonServiceConfig {

    @Bean
    public ClientService userPokemonService(
            UserRepository repository, CepIntegration integration) {
        return new ClientService(repository, integration);
    }

}
