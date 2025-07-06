package br.com.fatec.pokemon.configuration.service;

import br.com.fatec.pokemon.integration.CepIntegration;
import br.com.fatec.pokemon.integration.RabbitMQProducer;
import br.com.fatec.pokemon.repository.UserRepository;
import br.com.fatec.pokemon.service.UserPokemonService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserPokemonServiceConfig {

    @Bean
    public UserPokemonService userPokemonService(
            UserRepository repository,
            CepIntegration integration,
            RabbitMQProducer producer) {
        return new UserPokemonService(repository, integration, producer);
    }
}
