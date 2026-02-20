package br.com.home.arch.application.usecase;

import java.math.BigDecimal;
import java.util.Objects;

public class CriarPedidoCommand {

    private String customerId;
    private BigDecimal totalAmount;

    public CriarPedidoCommand(String customerId, BigDecimal totalAmount) {
        this.customerId = Objects.requireNonNull(customerId, "Customer ID cannot be null");
        this.totalAmount = Objects.requireNonNull(totalAmount, "Total amount cannot be null");
        if (totalAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Total amount must be positive");
        }
    }

    public String getCustomerId() {
        return customerId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
}
