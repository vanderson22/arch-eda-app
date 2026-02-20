package br.com.home.arch.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

//Exemplo de uma classe não anêmica.
public class Pedido {

    private PedidoId id;
    private String customerId;
    private BigDecimal totalAmount;
    private LocalDateTime creationDate;
    private PedidoStatus status;

    private Pedido(PedidoId id, String customerId, BigDecimal totalAmount, LocalDateTime creationDate, PedidoStatus status) {
        this.id = id;
        this.customerId = customerId;
        this.totalAmount = totalAmount;
        this.creationDate = creationDate;
        this.status = status;
    }

    public static Pedido create(String customerId, BigDecimal totalAmount) {
        Objects.requireNonNull(customerId, "Customer ID cannot be null");
        Objects.requireNonNull(totalAmount, "Total amount cannot be null");
        if (totalAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Total amount must be positive");
        }

        return new Pedido(PedidoId.generate(), customerId, totalAmount, LocalDateTime.now(), PedidoStatus.PENDING);
    }

    public PedidoId getId() {
        return id;
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

    public PedidoStatus getStatus() {
        return status;
    }

    public void approve() {
        if (this.status != PedidoStatus.PENDING) {
            throw new IllegalStateException("Pedido cannot be approved if not in PENDING status");
        }
        this.status = PedidoStatus.APPROVED;
    }

    public void reject() {
        if (this.status != PedidoStatus.PENDING) {
            throw new IllegalStateException("Pedido cannot be rejected if not in PENDING status");
        }
        this.status = PedidoStatus.REJECTED;
    }

    // Factory method for loading from persistence
    public static Pedido load(UUID id, String customerId, BigDecimal totalAmount, LocalDateTime creationDate, PedidoStatus status) {
        return new Pedido(PedidoId.of(id), customerId, totalAmount, creationDate, status);
    }

    public enum PedidoStatus {
        PENDING,
        APPROVED,
        REJECTED
    }
}
