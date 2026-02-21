package br.com.home.arch.application.usecase;

import br.com.home.arch.domain.entity.PedidoId;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CriarPedidoCommandTest {

    @Test
    void constructor_shouldCreateCommand_whenValidDataProvided() {
        String customerId = "customer1";
        BigDecimal totalAmount = new BigDecimal("100.00");
        CriarPedidoCommand command = new CriarPedidoCommand(customerId, totalAmount);

        assertNotNull(command);
        assertEquals(customerId, command.getCustomerId());
        assertEquals(totalAmount, command.getTotalAmount());
    }

    @Test
    void constructor_shouldThrowNullPointerException_whenCustomerIdIsNull() {
        BigDecimal totalAmount = new BigDecimal("100.00");
        assertThrows(NullPointerException.class, () -> new CriarPedidoCommand(null, totalAmount));
    }

    @Test
    void constructor_shouldThrowNullPointerException_whenTotalAmountIsNull() {
        String customerId = "customer1";
        assertThrows(NullPointerException.class, () -> new CriarPedidoCommand(customerId, null));
    }

    @Test
    void constructor_shouldThrowIllegalArgumentException_whenTotalAmountIsZeroOrNegative() {
        String customerId = "customer1";
        assertThrows(IllegalArgumentException.class, () -> new CriarPedidoCommand(customerId, BigDecimal.ZERO));
        assertThrows(IllegalArgumentException.class, () -> new CriarPedidoCommand(customerId, new BigDecimal("-10.00")));
    }
}
