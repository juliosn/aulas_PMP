package br.com.fatec.pokemon.messaging;

import br.com.fatec.pokemon.entity.User;

public interface PedidoMessaging {
    void sendToQueue(User user);
}
