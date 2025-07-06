package br.com.fatec.pokemon.controller.dto.request;

public record UserRequest(
        String name,
        String email,
        String cep
) {
}
