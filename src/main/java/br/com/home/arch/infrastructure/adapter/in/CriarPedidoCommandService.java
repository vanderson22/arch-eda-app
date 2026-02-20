package br.com.home.arch.infrastructure.adapter.in;

import br.com.home.arch.application.port.in.CriarPedidoUseCase;
import br.com.home.arch.application.usecase.CriarPedidoCommand;
import br.com.home.arch.application.usecase.CriarPedidoResponse;
import org.springframework.stereotype.Component;

@Component
public class CriarPedidoCommandService {

    private final CriarPedidoUseCase criarPedidoUseCase;

    public CriarPedidoCommandService(CriarPedidoUseCase criarPedidoUseCase) {
        this.criarPedidoUseCase = criarPedidoUseCase;
    }

    public CriarPedidoResponse criarPedido(CriarPedidoRequest request) {
        CriarPedidoCommand command = new CriarPedidoCommand(request.getCustomerId(), request.getTotalAmount());
        return criarPedidoUseCase.execute(command);
    }
}
