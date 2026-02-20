package br.com.home.arch.infrastructure.adapter.in;

import java.math.BigDecimal;

public class CriarPedidoRequest {
    private String customerId;
    private BigDecimal totalAmount;

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
}
