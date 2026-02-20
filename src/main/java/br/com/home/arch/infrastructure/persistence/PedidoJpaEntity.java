package br.com.home.arch.infrastructure.persistence;

import br.com.home.arch.domain.entity.Pedido.PedidoStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "pedidos")
public class PedidoJpaEntity {

    @Id
    private UUID id;
    private String customerId;
    private BigDecimal totalAmount;
    private LocalDateTime creationDate;

    @Enumerated(EnumType.STRING)
    private PedidoStatus status;

    public PedidoJpaEntity() {
    }

    public PedidoJpaEntity(UUID id, String customerId, BigDecimal totalAmount, LocalDateTime creationDate, PedidoStatus status) {
        this.id = id;
        this.customerId = customerId;
        this.totalAmount = totalAmount;
        this.creationDate = creationDate;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public PedidoStatus getStatus() {
        return status;
    }

    public void setStatus(PedidoStatus status) {
        this.status = status;
    }
}
