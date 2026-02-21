package br.com.home.arch.infrastructure.secondary;

import br.com.home.arch.application.port.out.PedidoEventPublisher;
import br.com.home.arch.domain.entity.PedidoId;
import br.com.home.arch.domain.event.PedidoCriadoEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.kafka.core.KafkaTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class KafkaPedidoEventPublisherTest {

    private KafkaTemplate<String, PedidoCriadoEvent> kafkaTemplate;
    private KafkaPedidoEventPublisher kafkaPedidoEventPublisher;

    @BeforeEach
    void setUp() {
        kafkaTemplate = mock(KafkaTemplate.class);
        kafkaPedidoEventPublisher = new KafkaPedidoEventPublisher(kafkaTemplate);
    }

    @Test
    void publish_shouldSendEventToKafka() {
        // Given
        PedidoId pedidoId = PedidoId.generate();
        String customerId = "customer1";
        BigDecimal totalAmount = new BigDecimal("100.00");
        LocalDateTime creationDate = LocalDateTime.now();
        PedidoCriadoEvent event = new PedidoCriadoEvent(pedidoId, customerId, totalAmount, creationDate);

        // When
        kafkaPedidoEventPublisher.publish(event);

        // Then
        ArgumentCaptor<String> topicCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> keyCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<PedidoCriadoEvent> eventCaptor = ArgumentCaptor.forClass(PedidoCriadoEvent.class);

        verify(kafkaTemplate, times(1)).send(topicCaptor.capture(), keyCaptor.capture(), eventCaptor.capture());

        assertEquals("pedido.criado", topicCaptor.getValue());
        assertEquals(pedidoId.toString(), keyCaptor.getValue());
        assertEquals(event, eventCaptor.getValue());
    }
}
