package br.com.home.arch.domain.entity;

import br.com.home.arch.domain.entity.Pedido.PedidoStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {

    @Test
    void create_shouldCreatePedidoInPendingStatus() {
        String customerId = "customer123";
        BigDecimal totalAmount = new BigDecimal("150.75");
        Pedido pedido = Pedido.create(customerId, totalAmount);

        assertNotNull(pedido);
        assertNotNull(pedido.getId());
        assertEquals(customerId, pedido.getCustomerId());
        assertEquals(totalAmount, pedido.getTotalAmount());
        assertNotNull(pedido.getCreationDate());
        assertEquals(PedidoStatus.PENDING, pedido.getStatus());
    }

    @Test
    void create_shouldThrowNullPointerException_whenCustomerIdIsNull() {
        BigDecimal totalAmount = new BigDecimal("100.00");
        assertThrows(NullPointerException.class, () -> Pedido.create(null, totalAmount));
    }

    @Test
    void create_shouldThrowNullPointerException_whenTotalAmountIsNull() {
        String customerId = "customer123";
        assertThrows(NullPointerException.class, () -> Pedido.create(customerId, null));
    }

    @Test
    void create_shouldThrowIllegalArgumentException_whenTotalAmountIsZeroOrNegative() {
        String customerId = "customer123";
        assertThrows(IllegalArgumentException.class, () -> Pedido.create(customerId, BigDecimal.ZERO));
        assertThrows(IllegalArgumentException.class, () -> Pedido.create(customerId, new BigDecimal("-10.00")));
    }

    @Test
    void approve_shouldChangeStatusToApproved_whenPedidoIsPending() {
        Pedido pedido = Pedido.create("customer123", new BigDecimal("50.00"));
        pedido.approve();
        assertEquals(PedidoStatus.APPROVED, pedido.getStatus());
    }

    @Test
    void approve_shouldThrowIllegalStateException_whenPedidoIsNotPending() {
        Pedido pedido = Pedido.create("customer123", new BigDecimal("50.00"));
        pedido.approve(); // Now it's APPROVED
        assertThrows(IllegalStateException.class, pedido::approve);
    }

    @Test
    void reject_shouldChangeStatusToRejected_whenPedidoIsPending() {
        Pedido pedido = Pedido.create("customer123", new BigDecimal("50.00"));
        pedido.reject();
        assertEquals(PedidoStatus.REJECTED, pedido.getStatus());
    }

    @Test
    void reject_shouldThrowIllegalStateException_whenPedidoIsNotPending() {
        Pedido pedido = Pedido.create("customer123", new BigDecimal("50.00"));
        pedido.reject(); // Now it's REJECTED
        assertThrows(IllegalStateException.class, pedido::reject);
    }

    @Test
    void load_shouldCreatePedidoWithGivenValues() {
        UUID uuid = UUID.randomUUID();
        String customerId = "loadedCustomer";
        BigDecimal totalAmount = new BigDecimal("200.00");
        LocalDateTime creationDate = LocalDateTime.now().minusDays(1);
        PedidoStatus status = PedidoStatus.APPROVED;

        Pedido pedido = Pedido.load(uuid, customerId, totalAmount, creationDate, status);

        assertNotNull(pedido);
        assertEquals(PedidoId.of(uuid), pedido.getId());
        assertEquals(customerId, pedido.getCustomerId());
        assertEquals(totalAmount, pedido.getTotalAmount());
        assertEquals(creationDate, pedido.getCreationDate());
        assertEquals(status, pedido.getStatus());
    }
}
