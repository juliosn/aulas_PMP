package br.com.fatec.frete.controller.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PedidoRequest(
        @NotBlank String nome,
        @Email String email,
        @NotBlank String cep,
        @NotBlank String logradouro,
        String complemento,
        @NotBlank String bairro,
        @NotBlank String localidade,
        @NotBlank String uf
) {
}
