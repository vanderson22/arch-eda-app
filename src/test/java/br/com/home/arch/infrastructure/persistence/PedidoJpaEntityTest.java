package br.com.home.arch.infrastructure.persistence;

import br.com.home.arch.domain.entity.Pedido.PedidoStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PedidoJpaEntityTest {

    @Test
    void constructorAndGettersSetters_shouldWorkCorrectly() {
        UUID id = UUID.randomUUID();
        String customerId = "customer1";
        BigDecimal totalAmount = new BigDecimal("100.00");
        LocalDateTime creationDate = LocalDateTime.now();
        PedidoStatus status = PedidoStatus.PENDING;

        PedidoJpaEntity entity = new PedidoJpaEntity(id, customerId, totalAmount, creationDate, status);

        assertNotNull(entity);
        assertEquals(id, entity.getId());
        assertEquals(customerId, entity.getCustomerId());
        assertEquals(totalAmount, entity.getTotalAmount());
        assertEquals(creationDate, entity.getCreationDate());
        assertEquals(status, entity.getStatus());

        UUID newId = UUID.randomUUID();
        String newCustomerId = "customer2";
        BigDecimal newTotalAmount = new BigDecimal("200.00");
        LocalDateTime newCreationDate = LocalDateTime.now().plusDays(1);
        PedidoStatus newStatus = PedidoStatus.APPROVED;

        entity.setId(newId);
        entity.setCustomerId(newCustomerId);
        entity.setTotalAmount(newTotalAmount);
        entity.setCreationDate(newCreationDate);
        entity.setStatus(newStatus);

        assertEquals(newId, entity.getId());
        assertEquals(newCustomerId, entity.getCustomerId());
        assertEquals(newTotalAmount, entity.getTotalAmount());
        assertEquals(newCreationDate, entity.getCreationDate());
        assertEquals(newStatus, entity.getStatus());
    }

    @Test
    void noArgsConstructor_shouldCreateInstance() {
        PedidoJpaEntity entity = new PedidoJpaEntity();
        assertNotNull(entity);
    }
}
