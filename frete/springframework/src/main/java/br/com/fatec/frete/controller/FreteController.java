package br.com.fatec.frete.controller;

import br.com.fatec.frete.controller.adapter.FreteControllerAdapter;
import br.com.fatec.frete.controller.dto.request.PedidoRequest;
import br.com.fatec.frete.controller.dto.response.PedidoResponse;
import br.com.fatec.frete.entity.Pedido;
import br.com.fatec.frete.service.FreteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/frete/v1")
public class FreteController {

    private final FreteService service;

    public FreteController(FreteService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/pedido")
    public PedidoResponse calcularFrete(@RequestBody @Valid PedidoRequest request) {
        Pedido pedido = FreteControllerAdapter.toDomain(request);
        Pedido salvo = service.calcularFrete(pedido.usuario(), pedido.endereco());
        return FreteControllerAdapter.toResponse(salvo);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/pedido/{id}")
    public PedidoResponse buscar(@PathVariable String id) {
        return FreteControllerAdapter.toResponse(service.buscarPorId(id));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/pedido/{id}")
    public void deletar(@PathVariable String id) {
        service.deletarPedido(id);
    }
}
