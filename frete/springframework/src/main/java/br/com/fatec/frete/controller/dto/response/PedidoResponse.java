package br.com.fatec.frete.controller.dto.response;

import br.com.fatec.frete.entity.StatusPedido;

public record PedidoResponse(
        String id,
        String nome,
        String email,
        String cep,
        double valorFrete,
        StatusPedido status
) {
}
