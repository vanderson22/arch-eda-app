package br.com.home.arch.domain.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class PedidoId implements Serializable {

    private UUID value;

    private PedidoId(UUID value) {
        this.value = value;
    }

    public static PedidoId of(UUID value) {
        Objects.requireNonNull(value, "PedidoId value cannot be null");
        return new PedidoId(value);
    }

    public static PedidoId generate() {
        return new PedidoId(UUID.randomUUID());
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoId pedidoId = (PedidoId) o;
        return Objects.equals(value, pedidoId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
