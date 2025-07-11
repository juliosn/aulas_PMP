package br.com.fatec.pokemon.messaging.adapter;

import br.com.fatec.pokemon.entity.User;
import br.com.fatec.pokemon.messaging.dto.PedidoFreteAmqp;

public class PedidoMessagingImplAdapter {
    private PedidoMessagingImplAdapter() {
    }

    public static PedidoFreteAmqp cast(User user) {
        PedidoFreteAmqp frete = new PedidoFreteAmqp();
        frete.setCep(user.endereco().cep());
        frete.setBairro(user.endereco().bairro());
        frete.setClientId(user.id());
        frete.setLogradouro(user.endereco().logradouro());
        frete.setComplemento(user.endereco().complemento());
        frete.setLocalidade(user.endereco().localidade());
        frete.setUf(user.endereco().uf());

        return frete;

//        return new PedidoFreteAmqp(
//                user.id(),
//                user.endereco().cep(),
//                user.endereco().logradouro(),
//                user.endereco().complemento(),
//                user.endereco().bairro(),
//                user.endereco().localidade(),
//                user.endereco().uf());
    }

}