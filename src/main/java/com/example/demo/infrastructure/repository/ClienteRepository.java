package com.example.demo.infrastructure.repository;

import com.example.demo.domain.model.Cliente;
import com.example.demo.domain.ports.ClienteRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ClienteRepository implements ClienteRepositoryPort {

    private final Map<Long, Cliente> banco = new HashMap<>();
    private final AtomicLong contador = new AtomicLong(1);

    @Override
    public Cliente salvar(Cliente cliente) {
        Long id = cliente.getId() != null ? cliente.getId() : contador.getAndIncrement();
        Cliente novo = new Cliente(id, cliente.getNome());
        banco.put(id, novo);
        return novo;
    }


    @Override
    public List<Cliente> listar() {
        return new ArrayList<>(banco.values());
    }

    @Override
    public Optional<Cliente> buscarPorId(Long id) {
        return Optional.ofNullable(banco.get(id));
    }

    @Override
    public void excluir(Long id) {
        banco.remove(id);
    }
}
