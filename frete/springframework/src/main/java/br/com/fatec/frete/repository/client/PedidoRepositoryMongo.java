package br.com.fatec.frete.repository.client;

import br.com.fatec.frete.repository.orm.PedidoOrm;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepositoryMongo extends MongoRepository<PedidoOrm, String> {
}
