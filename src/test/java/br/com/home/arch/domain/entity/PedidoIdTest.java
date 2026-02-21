package br.com.home.arch.domain.entity;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PedidoIdTest {

    @Test
    void of_shouldCreatePedidoId_whenValidUUIDProvided() {
        UUID uuid = UUID.randomUUID();
        PedidoId pedidoId = PedidoId.of(uuid);
        assertNotNull(pedidoId);
        assertEquals(uuid, pedidoId.getValue());
    }

    @Test
    void of_shouldThrowNullPointerException_whenNullUUIDProvided() {
        assertThrows(NullPointerException.class, () -> PedidoId.of(null));
    }

    @Test
    void generate_shouldCreatePedidoIdWithRandomUUID() {
        PedidoId pedidoId = PedidoId.generate();
        assertNotNull(pedidoId);
        assertNotNull(pedidoId.getValue());
    }

    @Test
    void equals_shouldReturnTrue_whenPedidoIdsHaveSameValue() {
        UUID uuid = UUID.randomUUID();
        PedidoId pedidoId1 = PedidoId.of(uuid);
        PedidoId pedidoId2 = PedidoId.of(uuid);
        assertTrue(pedidoId1.equals(pedidoId2));
    }

    @Test
    void equals_shouldReturnFalse_whenPedidoIdsHaveDifferentValue() {
        PedidoId pedidoId1 = PedidoId.generate();
        PedidoId pedidoId2 = PedidoId.generate();
        assertFalse(pedidoId1.equals(pedidoId2));
    }

    @Test
    void hashCode_shouldBeSame_whenPedidoIdsHaveSameValue() {
        UUID uuid = UUID.randomUUID();
        PedidoId pedidoId1 = PedidoId.of(uuid);
        PedidoId pedidoId2 = PedidoId.of(uuid);
        assertEquals(pedidoId1.hashCode(), pedidoId2.hashCode());
    }

    @Test
    void toString_shouldReturnUUIDString() {
        UUID uuid = UUID.randomUUID();
        PedidoId pedidoId = PedidoId.of(uuid);
        assertEquals(uuid.toString(), pedidoId.toString());
    }
}
