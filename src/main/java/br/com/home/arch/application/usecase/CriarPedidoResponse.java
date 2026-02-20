package br.com.home.arch.application.usecase;

import br.com.home.arch.domain.entity.PedidoId;

public class CriarPedidoResponse {
    private PedidoId pedidoId;

    public CriarPedidoResponse(PedidoId pedidoId) {
        this.pedidoId = pedidoId;
    }

    public PedidoId getPedidoId() {
        return pedidoId;
    }
}
