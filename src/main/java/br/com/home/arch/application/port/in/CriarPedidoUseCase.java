package br.com.home.arch.application.port.in;

import br.com.home.arch.application.usecase.CriarPedidoCommand;
import br.com.home.arch.application.usecase.CriarPedidoResponse;

public interface CriarPedidoUseCase {
    CriarPedidoResponse execute(CriarPedidoCommand command);
}
