package br.com.fatec.frete.entity;

import java.io.Serializable;

public record Pedido(
        String id,
        User usuario,
        Endereco endereco,
        double valorFrete,
        StatusPedido status
) implements Serializable {
}
