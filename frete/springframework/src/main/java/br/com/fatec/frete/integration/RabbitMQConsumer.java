package br.com.fatec.frete.integration;

import br.com.fatec.frete.entity.Pedido;
import br.com.fatec.frete.entity.User;
import br.com.fatec.frete.entity.Endereco;
import br.com.fatec.frete.service.FreteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(RabbitMQConsumer.class);

    private final FreteService freteService;

    public RabbitMQConsumer(FreteService freteService) {
        this.freteService = freteService;
    }

    @RabbitListener(queues = "frete-queue", containerFactory = "rabbitListenerContainerFactory")
    public void receberPedido(Pedido pedidoRecebido) {
        try {
            LOG.info(">>> Pedido recebido da fila: {}", pedidoRecebido);

            User usuario = pedidoRecebido.usuario();
            Endereco endereco = pedidoRecebido.endereco();

            Pedido novoPedido = freteService.calcularFrete(usuario, endereco);

            LOG.info(">>> Pedido processado e salvo: {}", novoPedido);

        } catch (Exception ex) {
            LOG.error(">>> Erro ao processar pedido: {}", ex.getMessage(), ex);
        }
    }
}
