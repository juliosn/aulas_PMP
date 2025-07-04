package br.com.fatec.frete.repository.orm;

import br.com.fatec.frete.entity.StatusPedido;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("pedido")
public record PedidoOrm(
        @Id
        String id,
        String userId,
        String nome,
        String email,
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String localidade,
        String uf,
        double valorFrete,
        StatusPedido status
) {
}
