package br.com.fatec.pokemon.integration;

import br.com.fatec.pokemon.entity.Endereco;
import br.com.fatec.pokemon.integration.adapter.PokemonIntegrationAdapter;
import br.com.fatec.pokemon.integration.client.ViaCepIntegrationWithFeign;
import br.com.fatec.pokemon.integration.dto.ViaCepResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
public class CepIntegrationImpl implements CepIntegration {

    private ViaCepIntegrationWithFeign integration;

    public CepIntegrationImpl(ViaCepIntegrationWithFeign integration) {
        this.integration = integration;
    }

    @Retryable(
            maxAttempts = 4,
            backoff = @Backoff(delay = 1000))
    @Cacheable(value = "endereco-cache", key = "#cep")
    public Endereco getCep(final String cep) {
        ViaCepResponse response = integration.getCep(cep);
        return PokemonIntegrationAdapter.cast(response);
    }
}