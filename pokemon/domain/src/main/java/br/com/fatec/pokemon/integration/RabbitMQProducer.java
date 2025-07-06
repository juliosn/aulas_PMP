package br.com.fatec.pokemon.integration;

import br.com.fatec.pokemon.entity.Endereco;
import br.com.fatec.pokemon.entity.Pedido;
import br.com.fatec.pokemon.entity.User;
import br.com.fatec.pokemon.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQProducer {

    private static final Logger LOG = LoggerFactory.getLogger(RabbitMQProducer.class);

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendPedido(Pedido pedido) {
        try {
            rabbitTemplate.convertAndSend(
                    "frete-exchange",
                    "frete.novo",
                    pedido);
            LOG.info("Pedido enviado para fila RabbitMQ com sucesso. ID do usuário: {}", pedido.usuario().id());
        } catch (Exception e) {
            LOG.error("Erro ao enviar pedido para fila RabbitMQ: {}", e.getMessage());
        }
    }

}
