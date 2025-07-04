package br.com.fatec.frete.entity;

import java.io.Serializable;

public record User(
        String id,
        String nome,
        String email
) implements Serializable {
}
