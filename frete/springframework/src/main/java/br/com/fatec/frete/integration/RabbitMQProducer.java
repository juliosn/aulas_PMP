package br.com.fatec.frete.integration;

import br.com.fatec.frete.entity.Pedido;
import br.com.fatec.frete.service.FreteService.FreteEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQProducer implements FreteEventPublisher {

    private static final Logger LOG = LoggerFactory.getLogger(RabbitMQProducer.class);

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void send(Pedido pedido) {
        try {
            rabbitTemplate.convertAndSend("frete-exchange", "frete.novo", pedido);
            LOG.info("Pedido enviado para fila com sucesso. ID: {}", pedido.id());
        } catch (Exception e) {
            LOG.error("Erro ao enviar pedido para fila: {}", e.getMessage());
        }
    }
}
