package br.com.home.arch.infrastructure.adapter.in;

import br.com.home.arch.infrastructure.adapter.in.CriarPedidoCommandService;
import br.com.home.arch.application.usecase.CriarPedidoCommand;
import br.com.home.arch.application.usecase.CriarPedidoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/pedidos")
public class CriarPedidoController {

    private final CriarPedidoCommandService criarPedidoCommandService;

    public CriarPedidoController(CriarPedidoCommandService criarPedidoCommandService) {
        this.criarPedidoCommandService = criarPedidoCommandService;
    }

    @PostMapping
    public ResponseEntity<CriarPedidoResponse> criarPedido(@RequestBody CriarPedidoRequest request) {
        CriarPedidoResponse response = criarPedidoCommandService.criarPedido(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
