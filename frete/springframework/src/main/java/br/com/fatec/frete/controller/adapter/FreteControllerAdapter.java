package br.com.fatec.frete.controller.adapter;

import br.com.fatec.frete.controller.dto.request.PedidoRequest;
import br.com.fatec.frete.controller.dto.response.PedidoResponse;
import br.com.fatec.frete.entity.Endereco;
import br.com.fatec.frete.entity.Pedido;
import br.com.fatec.frete.entity.User;

import java.util.UUID;

public class FreteControllerAdapter {
    private FreteControllerAdapter() {
    }

    public static Pedido toDomain(PedidoRequest request) {
        User user = new User(
                UUID.randomUUID().toString(),
                request.nome(),
                request.email());

        Endereco endereco = new Endereco(
                request.cep(),
                request.logradouro(),
                request.complemento(),
                request.bairro(),
                request.localidade(),
                request.uf());

        return new Pedido(null, user, endereco, 0.0, null);
    }

    public static PedidoResponse toResponse(Pedido pedido) {
        return new PedidoResponse(
                pedido.id(),
                pedido.usuario().nome(),
                pedido.usuario().email(),
                pedido.endereco().cep(),
                pedido.valorFrete(),
                pedido.status());
    }
}
