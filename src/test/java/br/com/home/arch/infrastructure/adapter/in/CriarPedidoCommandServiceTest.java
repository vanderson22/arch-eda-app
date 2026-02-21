package br.com.home.arch.infrastructure.adapter.in;

import br.com.home.arch.application.port.in.CriarPedidoUseCase;
import br.com.home.arch.application.usecase.CriarPedidoCommand;
import br.com.home.arch.application.usecase.CriarPedidoResponse;
import br.com.home.arch.domain.entity.PedidoId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CriarPedidoCommandServiceTest {

    private CriarPedidoUseCase criarPedidoUseCase;
    private CriarPedidoCommandService criarPedidoCommandService;

    @BeforeEach
    void setUp() {
        criarPedidoUseCase = mock(CriarPedidoUseCase.class);
        criarPedidoCommandService = new CriarPedidoCommandService(criarPedidoUseCase);
    }

    @Test
    void criarPedido_shouldCallUseCaseAndReturnResponse() {
        // Given
        String customerId = "customer123";
        BigDecimal totalAmount = new BigDecimal("100.00");
        CriarPedidoRequest request = new CriarPedidoRequest();
        request.setCustomerId(customerId);
        request.setTotalAmount(totalAmount);

        CriarPedidoResponse expectedResponse = new CriarPedidoResponse(PedidoId.of(UUID.randomUUID()));
        when(criarPedidoUseCase.execute(any(CriarPedidoCommand.class))).thenReturn(expectedResponse);

        // When
        CriarPedidoResponse actualResponse = criarPedidoCommandService.criarPedido(request);

        // Then
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);

        verify(criarPedidoUseCase, times(1)).execute(any(CriarPedidoCommand.class));
    }
}
