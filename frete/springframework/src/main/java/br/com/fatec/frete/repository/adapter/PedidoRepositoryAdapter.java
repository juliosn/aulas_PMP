package br.com.fatec.frete.repository.adapter;

import br.com.fatec.frete.entity.*;
import br.com.fatec.frete.repository.orm.PedidoOrm;

public class PedidoRepositoryAdapter {
    private PedidoRepositoryAdapter() {}

    public static PedidoOrm toOrm(Pedido pedido) {
        return new PedidoOrm(
                pedido.id(),
                pedido.usuario().id(),
                pedido.usuario().nome(),
                pedido.usuario().email(),
                pedido.endereco().cep(),
                pedido.endereco().logradouro(),
                pedido.endereco().complemento(),
                pedido.endereco().bairro(),
                pedido.endereco().localidade(),
                pedido.endereco().uf(),
                pedido.valorFrete(),
                pedido.status()
        );
    }

    public static Pedido toDomain(PedidoOrm orm) {
        return new Pedido(
                orm.id(),
                new User(orm.userId(), orm.nome(), orm.email()),
                new Endereco(
                        orm.cep(), orm.logradouro(), orm.complemento(),
                        orm.bairro(), orm.localidade(), orm.uf()
                ),
                orm.valorFrete(),
                orm.status()
        );
    }
}
