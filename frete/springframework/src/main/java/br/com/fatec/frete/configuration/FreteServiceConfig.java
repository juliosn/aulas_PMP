package br.com.fatec.frete.configuration;

import br.com.fatec.frete.repository.PedidoRepository;
import br.com.fatec.frete.service.FreteService;
import br.com.fatec.frete.service.FreteService.FreteEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FreteServiceConfig {

    @Bean
    public FreteEventPublisher fakePublisher() {
        return pedido -> {
        };
    }

    @Bean
    public FreteService freteService(PedidoRepository repository, FreteEventPublisher publisher) {
        return new FreteService(repository, publisher);
    }
}
