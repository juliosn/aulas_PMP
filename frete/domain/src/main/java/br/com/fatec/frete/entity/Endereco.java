package br.com.fatec.frete.entity;

import java.io.Serializable;

public record Endereco(
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String localidade,
        String uf
) implements Serializable {
}
