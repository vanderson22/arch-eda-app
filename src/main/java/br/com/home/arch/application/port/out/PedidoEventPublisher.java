package br.com.home.arch.application.port.out;

import br.com.home.arch.domain.event.PedidoCriadoEvent;

public interface PedidoEventPublisher {
    void publish(PedidoCriadoEvent event);
}
