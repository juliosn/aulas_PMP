package com.example.demo.domain.ports;

import com.example.demo.domain.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteRepositoryPort {
    Cliente salvar(Cliente cliente);
    List<Cliente> listar();
    Optional<Cliente> buscarPorId(Long id);
    void excluir(Long id);
}
