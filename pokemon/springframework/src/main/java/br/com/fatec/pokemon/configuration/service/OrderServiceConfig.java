package br.com.fatec.pokemon.configuration.service;

import br.com.fatec.pokemon.messaging.PedidoMessaging;
import br.com.fatec.pokemon.repository.UserRepository;
import br.com.fatec.pokemon.service.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderServiceConfig {

    @Bean
    public OrderService orderService(
            UserRepository repository, PedidoMessaging messaging) {
        return new OrderService(repository, messaging);
    }

}
