package br.com.home.arch.application.usecase;

import br.com.home.arch.application.port.out.PedidoEventPublisher;
import br.com.home.arch.application.port.out.PedidoRepository;
import br.com.home.arch.domain.entity.Pedido;
import br.com.home.arch.domain.event.PedidoCriadoEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CriarPedidoServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(CriarPedidoServiceTest.class);

    private PedidoRepository pedidoRepository;
    private PedidoEventPublisher pedidoEventPublisher;
    private CriarPedidoService criarPedidoService;

    @BeforeEach
    void setUp() {
        logger.info("CriarPedidoServiceTest: Setting up mocks for PedidoRepository and PedidoEventPublisher.");
        pedidoRepository = mock(PedidoRepository.class);
        pedidoEventPublisher = mock(PedidoEventPublisher.class);
        criarPedidoService = new CriarPedidoService(pedidoRepository, pedidoEventPublisher);
        logger.info("CriarPedidoServiceTest: Mocks set up successfully.");
    }

    @Test
    void execute_shouldCreateAndSavePedidoAndPublishEvent() {
        logger.info("CriarPedidoServiceTest: Starting execute_shouldCreateAndSavePedidoAndPublishEvent test.");
        String customerId = "customer123";
        BigDecimal totalAmount = new BigDecimal("100.00");
        logger.info("CriarPedidoServiceTest: Creating CriarPedidoCommand with customerId={}, totalAmount={}", customerId, totalAmount);
        CriarPedidoCommand command = new CriarPedidoCommand(customerId, totalAmount);

        logger.info("CriarPedidoServiceTest: Executing criarPedidoService.execute(command).");
        CriarPedidoResponse response = criarPedidoService.execute(command);
        logger.info("CriarPedidoServiceTest: Received response: {}", response.getPedidoId().getValue());

        assertNotNull(response);
        logger.info("CriarPedidoServiceTest: Response is not null.");

        ArgumentCaptor<Pedido> pedidoCaptor = ArgumentCaptor.forClass(Pedido.class);
        verify(pedidoRepository, times(1)).save(pedidoCaptor.capture());
        Pedido capturedPedido = Objects.isNull(pedidoCaptor) ? null : pedidoCaptor.getValue();
        logger.info("CriarPedidoServiceTest: Captured Pedido for saving: ID={}, CustomerID={}, TotalAmount={}, Status={}",
                capturedPedido.getId().getValue(), capturedPedido.getCustomerId(), capturedPedido.getTotalAmount(), capturedPedido.getStatus());

        assertEquals(capturedPedido.getId(), response.getPedidoId());
        logger.info("CriarPedidoServiceTest: Asserted captured Pedido ID matches response Pedido ID.");

        assertEquals(customerId, capturedPedido.getCustomerId());
        assertEquals(totalAmount, capturedPedido.getTotalAmount());
        assertNotNull(capturedPedido.getId());
        assertNotNull(capturedPedido.getCreationDate());
        assertEquals(Pedido.PedidoStatus.PENDING, capturedPedido.getStatus());
        logger.info("CriarPedidoServiceTest: Asserted captured Pedido details.");

        ArgumentCaptor<PedidoCriadoEvent> eventCaptor = ArgumentCaptor.forClass(PedidoCriadoEvent.class);
        verify(pedidoEventPublisher, times(1)).publish(eventCaptor.capture());
        PedidoCriadoEvent capturedEvent = eventCaptor.getValue();
        logger.info("CriarPedidoServiceTest: Captured PedidoCriadoEvent: ID={}, CustomerID={}, TotalAmount={}, CreationDate={}",
                capturedEvent.getPedidoId().getValue(), capturedEvent.getCustomerId(), capturedEvent.getTotalAmount(), capturedEvent.getCreationDate());

        assertEquals(capturedPedido.getId(), capturedEvent.getPedidoId());
        assertEquals(customerId, capturedEvent.getCustomerId());
        assertEquals(totalAmount, capturedEvent.getTotalAmount());
        assertEquals(capturedPedido.getCreationDate(), capturedEvent.getCreationDate());
        logger.info("CriarPedidoServiceTest: Asserted captured Event details match captured Pedido.");
        logger.info("CriarPedidoServiceTest: Finished execute_shouldCreateAndSavePedidoAndPublishEvent test successfully.");
    }
}
