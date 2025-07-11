package br.com.fatec.pokemon.messaging;

import br.com.fatec.pokemon.entity.User;
import br.com.fatec.pokemon.messaging.adapter.PedidoMessagingImplAdapter;
import br.com.fatec.pokemon.messaging.dto.PedidoFreteAmqp;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PedidoMessagingImpl implements PedidoMessaging {
    private final String exchange;
    private final String routingKey;
    private final RabbitTemplate rabbitTemplate;
    private final Jackson2JsonMessageConverter converter;

    public PedidoMessagingImpl(
            @Value("${spring.rabbitmq.request.exchange.producer}") String exchange,
            @Value("${spring.rabbitmq.request.routing-key.producer}") String routingKey,
            RabbitTemplate rabbitTemplate,
            Jackson2JsonMessageConverter converter) {
        this.exchange = exchange;
        this.converter = converter;
        this.routingKey = routingKey;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendToQueue(User user) {
        PedidoFreteAmqp amqp = PedidoMessagingImplAdapter.cast(user);
        rabbitTemplate.convertAndSend(exchange, routingKey, amqp);
    }
}
