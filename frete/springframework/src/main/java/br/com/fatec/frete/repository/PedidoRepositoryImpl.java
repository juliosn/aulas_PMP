package br.com.fatec.frete.repository;

import br.com.fatec.frete.entity.Pedido;
import br.com.fatec.frete.exception.InternalServerException;
import br.com.fatec.frete.exception.NotFoundException;
import br.com.fatec.frete.repository.PedidoRepository;
import br.com.fatec.frete.repository.adapter.PedidoRepositoryAdapter;
import br.com.fatec.frete.repository.client.PedidoRepositoryMongo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class PedidoRepositoryImpl implements PedidoRepository {

    private static final Logger LOG = LoggerFactory.getLogger(PedidoRepositoryImpl.class);
    private final PedidoRepositoryMongo mongo;

    public PedidoRepositoryImpl(PedidoRepositoryMongo mongo) {
        this.mongo = mongo;
    }

    @Override
    public Pedido save(Pedido pedido) {
        try {
            return PedidoRepositoryAdapter.toDomain(
                    mongo.save(PedidoRepositoryAdapter.toOrm(pedido)));
        } catch (Exception ex) {
            LOG.error("Erro ao salvar pedido: {}", ex.getMessage());
            throw new InternalServerException(ex);
        }
    }

    @Override
    public Pedido findById(String id) {
        return mongo.findById(id)
                .map(PedidoRepositoryAdapter::toDomain)
                .orElseThrow(() -> new NotFoundException("Pedido não encontrado"));
    }

    @Override
    public void delete(String id) {
        try {
            mongo.deleteById(id);
        } catch (Exception ex) {
            LOG.error("Erro ao deletar pedido: {}", ex.getMessage());
            throw new InternalServerException(ex);
        }
    }
}
