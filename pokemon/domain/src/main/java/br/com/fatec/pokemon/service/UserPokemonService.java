package br.com.fatec.pokemon.service;

import br.com.fatec.pokemon.entity.Endereco;
import br.com.fatec.pokemon.entity.Pedido;
import br.com.fatec.pokemon.entity.User;
import br.com.fatec.pokemon.exception.BadRequestException;
import br.com.fatec.pokemon.exception.InternalServerException;
import br.com.fatec.pokemon.exception.NotFoundException;
import br.com.fatec.pokemon.integration.CepIntegration;
import br.com.fatec.pokemon.integration.RabbitMQProducer;
import br.com.fatec.pokemon.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserPokemonService {

    private static final Logger LOG = LoggerFactory.getLogger(UserPokemonService.class);

    private final UserRepository repository;
    private final CepIntegration integration;
    private final RabbitMQProducer producer;

    public UserPokemonService(
            UserRepository repository,
            CepIntegration integration,
            RabbitMQProducer producer) {
        this.repository = repository;
        this.integration = integration;
        this.producer = producer;
    }

    public User register(User user) {
        try {
            User updateUser = repository.findByName(user.nome());
            User saved = save(updateUser.id(), user);
            enviarPedidoParaFila(saved);
            return saved;
        } catch (NotFoundException ex) {
            User saved = save(user);
            enviarPedidoParaFila(saved);
            return saved;
        }
    }

    private void enviarPedidoParaFila(User user) {
        Pedido pedido = new Pedido(
                null,
                user,
                user.endereco(),
                0.0,
                null
        );
        validarPedido(pedido);
        producer.sendPedido(pedido);
    }

    private void validarPedido(Pedido pedido) {
        if (pedido == null) {
            throw new BadRequestException("Pedido não pode ser nulo");
        }

        User usuario = pedido.usuario();
        if (usuario == null) {
            throw new BadRequestException("Usuário não pode ser nulo");
        }
        if (usuario.nome() == null || usuario.nome().isBlank()) {
            throw new BadRequestException("Nome do usuário é obrigatório");
        }
        if (usuario.email() == null || usuario.email().isBlank()) {
            throw new BadRequestException("Email do usuário é obrigatório");
        }
        if (usuario.endereco() == null || usuario.endereco().cep() == null || usuario.endereco().cep().isBlank()) {
            throw new BadRequestException("CEP não pode ser vazio ou nulo");
        }
        Endereco endereco = integration.getCep(usuario.endereco().cep());

        if (endereco == null) {
            throw new BadRequestException("Endereço não pode ser nulo");
        }
        if (endereco.cep() == null || endereco.cep().isBlank()) {
            throw new BadRequestException("CEP é obrigatório");
        }
        if (endereco.logradouro() == null || endereco.logradouro().isBlank()) {
            throw new BadRequestException("Logradouro é obrigatório");
        }
        if (endereco.bairro() == null || endereco.bairro().isBlank()) {
            throw new BadRequestException("Bairro é obrigatório");
        }
        if (endereco.localidade() == null || endereco.localidade().isBlank()) {
            throw new BadRequestException("Localidade é obrigatória");
        }
        if (endereco.uf() == null || endereco.uf().isBlank()) {
            throw new BadRequestException("UF é obrigatória");
        }
    }

    private User save(User user) {
        return save(user.id(), user);
    }

    private User save(final String id, User user) {
        try {
            Endereco endereco = integration.getCep(user.endereco().cep());
            return repository.save(new User(
                    id,
                    user.nome(),
                    user.email(),
                    endereco));
        } catch (Exception ex) {
            LOG.error("Erro na regra de negócio ao salvar: {} na data/hora: {}",
                    ex.getMessage(), LocalDateTime.now());
            throw new InternalServerException(ex);
        }
    }
}
