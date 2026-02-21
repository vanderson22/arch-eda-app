package br.com.home.arch.infrastructure.adapter.in;

import br.com.home.arch.application.usecase.CriarPedidoResponse;
import br.com.home.arch.domain.entity.PedidoId;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CriarPedidoController.class)
class CriarPedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CriarPedidoCommandService criarPedidoCommandService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void criarPedido_shouldReturnCreatedStatusAndPedidoId() throws Exception {
        // Given
        CriarPedidoRequest request = new CriarPedidoRequest();
        request.setCustomerId("customer123");
        request.setTotalAmount(new BigDecimal("100.00"));

        PedidoId pedidoId = PedidoId.of(UUID.randomUUID());
        CriarPedidoResponse response = new CriarPedidoResponse(pedidoId);

        when(criarPedidoCommandService.criarPedido(any(CriarPedidoRequest.class))).thenReturn(response);

        // When & Then
        mockMvc.perform(post("/pedidos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.pedidoId.value").value(pedidoId.getValue().toString()));
    }
}
