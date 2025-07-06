package br.com.fatec.pokemon.entity;

import java.io.Serializable;

public record Pedido(
        String id,
        User usuario,
        Endereco endereco,
        double valorFrete,
        String status
) implements Serializable {
}
