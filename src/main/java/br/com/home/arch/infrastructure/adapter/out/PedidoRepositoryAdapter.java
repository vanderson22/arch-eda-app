package br.com.home.arch.infrastructure.adapter.out;

import br.com.home.arch.application.port.out.PedidoRepository;
import br.com.home.arch.domain.entity.Pedido;
import br.com.home.arch.infrastructure.persistence.PedidoJpaEntity;
import br.com.home.arch.infrastructure.persistence.SpringPedidoRepository;
import org.springframework.stereotype.Component;

@Component
public class PedidoRepositoryAdapter implements PedidoRepository {

    private final SpringPedidoRepository springPedidoRepository;

    public PedidoRepositoryAdapter(SpringPedidoRepository springPedidoRepository) {
        this.springPedidoRepository = springPedidoRepository;
    }

    @Override
    public Pedido save(Pedido pedido) {
        PedidoJpaEntity pedidoJpaEntity = toJpaEntity(pedido);
        springPedidoRepository.save(pedidoJpaEntity);
        return toDomainEntity(pedidoJpaEntity);
    }

    private PedidoJpaEntity toJpaEntity(Pedido pedido) {
        return new PedidoJpaEntity(
                pedido.getId().getValue(),
                pedido.getCustomerId(),
                pedido.getTotalAmount(),
                pedido.getCreationDate(),
                pedido.getStatus()
        );
    }

    private Pedido toDomainEntity(PedidoJpaEntity pedidoJpaEntity) {
        return Pedido.load(
                pedidoJpaEntity.getId(),
                pedidoJpaEntity.getCustomerId(),
                pedidoJpaEntity.getTotalAmount(),
                pedidoJpaEntity.getCreationDate(),
                pedidoJpaEntity.getStatus()
        );
    }
}
