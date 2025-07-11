package br.com.fatec.pokemon.service;

import br.com.fatec.pokemon.entity.User;
import br.com.fatec.pokemon.messaging.PedidoMessaging;
import br.com.fatec.pokemon.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderService {
    private static final Logger LOG = LoggerFactory.getLogger(ClientService.class);

    private final UserRepository repository;
    private final PedidoMessaging messaging;

    public OrderService(UserRepository repository, PedidoMessaging messaging) {
        this.repository = repository;
        this.messaging = messaging;
    }

    public void createOrder(String idClient) {
        User user = repository.findById(idClient);
        messaging.sendToQueue(user);
        LOG.info("MENSAGEM ENVIADA AO RABBIT");
    }

}
