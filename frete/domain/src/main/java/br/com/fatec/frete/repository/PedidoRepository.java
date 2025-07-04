package br.com.fatec.frete.repository;

import br.com.fatec.frete.entity.Pedido;

public interface PedidoRepository {
    Pedido save(Pedido pedido);
    Pedido findById(String id);
    void delete(String id);
}
