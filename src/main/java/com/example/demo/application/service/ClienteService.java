package com.example.demo.application.service;

import com.example.demo.domain.model.Cliente;
import com.example.demo.domain.ports.ClienteRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepositoryPort repository;

    public ClienteService(ClienteRepositoryPort repository) {
        this.repository = repository;
    }

    public Cliente cadastrar(String nome) {
        Cliente cliente = new Cliente(null, nome);
        return repository.salvar(cliente);
    }

    public List<Cliente> listar() {
        return repository.listar();
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public void excluir(Long id) {
        repository.excluir(id);
    }

    public Cliente atualizar(Long id, String nome) {
        Cliente existente = repository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Cliente atualizado = new Cliente(id, nome);
        return repository.salvar(atualizado);
    }

}
