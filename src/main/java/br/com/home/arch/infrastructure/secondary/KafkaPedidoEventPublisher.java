package br.com.home.arch.infrastructure.secondary;

import br.com.home.arch.application.port.out.PedidoEventPublisher;
import br.com.home.arch.domain.event.PedidoCriadoEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaPedidoEventPublisher implements PedidoEventPublisher {

    private static final String TOPIC = "pedido.criado";

    private final KafkaTemplate<String, PedidoCriadoEvent> kafkaTemplate;

    public KafkaPedidoEventPublisher(KafkaTemplate<String, PedidoCriadoEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(PedidoCriadoEvent event) {
        kafkaTemplate.send(TOPIC, event.getPedidoId().toString(), event);
    }
}
