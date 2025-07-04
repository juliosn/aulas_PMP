package br.com.fatec.frete.config;

import br.com.fatec.frete.service.FreteService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.mockito.Mockito.mock;

@TestConfiguration
public class FreteServiceTestConfig {
    @Bean
    public FreteService freteService() {
        return mock(FreteService.class);
    }
}
