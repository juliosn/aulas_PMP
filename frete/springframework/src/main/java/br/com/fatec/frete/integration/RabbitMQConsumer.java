package br.com.fatec.frete.integration;

import br.com.fatec.frete.entity.Pedido;
import br.com.fatec.frete.service.FreteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(RabbitMQConsumer.class);
    private final FreteService service;

    public RabbitMQConsumer(FreteService service) {
        this.service = service;
    }

    @RabbitListener(queues = "frete-queue")
    public void receive(Pedido pedido) {
        LOG.info("Mensagem recebida da fila: {}", pedido);
        service.calcularFrete(pedido.usuario(), pedido.endereco());
    }
}
