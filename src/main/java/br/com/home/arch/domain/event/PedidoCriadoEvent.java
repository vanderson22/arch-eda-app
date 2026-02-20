package br.com.home.arch.domain.event;

import br.com.home.arch.domain.entity.PedidoId;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class PedidoCriadoEvent {

    private final PedidoId pedidoId;
    private final String customerId;
    private final BigDecimal totalAmount;
    private final LocalDateTime creationDate;

    public PedidoCriadoEvent(PedidoId pedidoId, String customerId, BigDecimal totalAmount, LocalDateTime creationDate) {
        this.pedidoId = pedidoId;
        this.customerId = customerId;
        this.totalAmount = totalAmount;
        this.creationDate = creationDate;
    }

    public PedidoId getPedidoId() {
        return pedidoId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
}
