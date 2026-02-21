package br.com.home.arch.application.usecase;

import br.com.home.arch.domain.entity.PedidoId;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CriarPedidoResponseTest {

    @Test
    void constructor_shouldCreateResponse_whenPedidoIdProvided() {
        PedidoId pedidoId = PedidoId.generate();
        CriarPedidoResponse response = new CriarPedidoResponse(pedidoId);

        assertNotNull(response);
        assertEquals(pedidoId, response.getPedidoId());
    }

    @Test
    void getPedidoId_shouldReturnCorrectPedidoId() {
        PedidoId pedidoId = PedidoId.generate();
        CriarPedidoResponse response = new CriarPedidoResponse(pedidoId);
        assertEquals(pedidoId, response.getPedidoId());
    }
}
