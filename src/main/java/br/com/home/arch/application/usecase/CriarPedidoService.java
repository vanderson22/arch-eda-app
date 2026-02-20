package br.com.home.arch.application.usecase;

import br.com.home.arch.application.port.in.CriarPedidoUseCase;
import br.com.home.arch.application.port.out.PedidoEventPublisher;
import br.com.home.arch.application.port.out.PedidoRepository;
import br.com.home.arch.domain.entity.Pedido;
import br.com.home.arch.domain.event.PedidoCriadoEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CriarPedidoService implements CriarPedidoUseCase {

    private final PedidoRepository pedidoRepository;
    private final PedidoEventPublisher pedidoEventPublisher;

    public CriarPedidoService(PedidoRepository pedidoRepository, PedidoEventPublisher pedidoEventPublisher) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoEventPublisher = pedidoEventPublisher;
    }

    @Override
    public CriarPedidoResponse execute(CriarPedidoCommand command) {
        // 1. Validate business rules (already done in CriarPedidoCommand and Pedido.create)
        Pedido pedido = Pedido.create(command.getCustomerId(), command.getTotalAmount());

        // 2. Persist in the database
        pedidoRepository.save(pedido);

        // 3. Publish PedidoCriado event
        PedidoCriadoEvent pedidoCriadoEvent = new PedidoCriadoEvent(
                pedido.getId(),
                pedido.getCustomerId(),
                pedido.getTotalAmount(),
                pedido.getCreationDate()
        );
        pedidoEventPublisher.publish(pedidoCriadoEvent);

        // 4. Return DTO response
        return new CriarPedidoResponse(pedido.getId());
    }
}
