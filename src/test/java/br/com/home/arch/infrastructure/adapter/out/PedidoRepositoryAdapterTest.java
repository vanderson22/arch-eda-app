package br.com.home.arch.infrastructure.adapter.out;

import br.com.home.arch.application.port.out.PedidoRepository;
import br.com.home.arch.domain.entity.Pedido;
import br.com.home.arch.domain.entity.Pedido.PedidoStatus;
import br.com.home.arch.domain.entity.PedidoId;
import br.com.home.arch.infrastructure.persistence.PedidoJpaEntity;
import br.com.home.arch.infrastructure.persistence.SpringPedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class PedidoRepositoryAdapterTest {

    private SpringPedidoRepository springPedidoRepository;
    private PedidoRepositoryAdapter pedidoRepositoryAdapter;

    @BeforeEach
    void setUp() {
        springPedidoRepository = mock(SpringPedidoRepository.class);
        pedidoRepositoryAdapter = new PedidoRepositoryAdapter(springPedidoRepository);
    }

    @Test
    void save_shouldConvertAndSavePedido() {
        // Given
        Pedido domainPedido = Pedido.create("customer123", new BigDecimal("100.50"));
        PedidoJpaEntity jpaEntity = new PedidoJpaEntity(
                domainPedido.getId().getValue(),
                domainPedido.getCustomerId(),
                domainPedido.getTotalAmount(),
                domainPedido.getCreationDate(),
                domainPedido.getStatus()
        );

        when(springPedidoRepository.save(any(PedidoJpaEntity.class))).thenReturn(jpaEntity);

        // When
        Pedido savedDomainPedido = pedidoRepositoryAdapter.save(domainPedido);

        // Then
        assertNotNull(savedDomainPedido);
        assertEquals(domainPedido.getId(), savedDomainPedido.getId());
        assertEquals(domainPedido.getCustomerId(), savedDomainPedido.getCustomerId());
        assertEquals(domainPedido.getTotalAmount(), savedDomainPedido.getTotalAmount());
        assertEquals(domainPedido.getCreationDate(), savedDomainPedido.getCreationDate());
        assertEquals(domainPedido.getStatus(), savedDomainPedido.getStatus());

        verify(springPedidoRepository, times(1)).save(any(PedidoJpaEntity.class));
    }
}
