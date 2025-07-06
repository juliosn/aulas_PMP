package br.com.fatec.pokemon.entity;

import java.io.Serializable;

public record User(
        String id,
        String nome,
        String email,
        Endereco endereco
) implements Serializable {
}
