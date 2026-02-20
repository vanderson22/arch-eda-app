package br.com.home.arch.application.port.out;

import br.com.home.arch.domain.entity.Pedido;

public interface PedidoRepository {
    Pedido save(Pedido pedido);
}
