package br.com.fatec.frete.service;

import br.com.fatec.frete.entity.*;
import br.com.fatec.frete.exception.BadRequestException;
import br.com.fatec.frete.exception.InternalServerException;
import br.com.fatec.frete.repository.PedidoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;

import java.time.LocalDateTime;
import java.util.Set;

public class FreteService {

    private static final Logger LOG = LoggerFactory.getLogger(FreteService.class);

    private final PedidoRepository repository;
    private final FreteEventPublisher publisher;

    private static final Set<String> ESTADOS_GRATIS = Set.of("SP", "PR");
    private static final Set<String> ESTADOS_VINTE = Set.of("RJ", "SC", "RS");
    private static final Set<String> ESTADOS_CINQUENTA = Set.of("MG", "MT", "MS", "ES");

    public FreteService(PedidoRepository repository, FreteEventPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    @Cacheable(value = "freteCache", key = "#endereco.uf")
    public Pedido calcularFrete(User usuario, Endereco endereco) {
        validarUsuario(usuario);
        validarEndereco(endereco);

        String uf = endereco.uf().toUpperCase();
        double valorFrete;

        if (ESTADOS_GRATIS.contains(uf)) {
            valorFrete = 0.0;
        } else if (ESTADOS_VINTE.contains(uf)) {
            valorFrete = 20.0;
        } else if (ESTADOS_CINQUENTA.contains(uf)) {
            valorFrete = 50.0;
        } else {
            throw new BadRequestException("Não realizamos entregas para o estado: " + uf);
        }

        Pedido pedido = new Pedido(null, usuario, endereco, valorFrete, StatusPedido.PROCESSANDO);

        Pedido salvo = repository.save(pedido);
        publisher.send(salvo);
        return salvo;
    }

    public Pedido buscarPorId(String id) {
        return repository.findById(id);
    }

    public void deletarPedido(String id) {
        repository.delete(id);
    }

    private void validarUsuario(User usuario) {
        if (usuario == null) {
            throw new BadRequestException("Usuário não pode ser nulo");
        }
        if (usuario.nome() == null || usuario.nome().isBlank()) {
            throw new BadRequestException("Nome do usuário é obrigatório");
        }
        if (usuario.email() == null || usuario.email().isBlank()) {
            throw new BadRequestException("Email do usuário é obrigatório");
        }
        // Pode adicionar validação de formato do email aqui se quiser
    }

    private void validarEndereco(Endereco endereco) {
        if (endereco == null) {
            throw new BadRequestException("Endereço não pode ser nulo");
        }
        if (endereco.uf() == null || endereco.uf().isBlank()) {
            throw new BadRequestException("UF do endereço é obrigatória");
        }
        // Pode validar o CEP e outras partes aqui se quiser
    }

    public interface FreteEventPublisher {
        void send(Pedido pedido);
    }
}
