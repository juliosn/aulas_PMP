package br.com.fatec.pokemon.controller;

import br.com.fatec.pokemon.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/context/v1")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/client/order/{clientId}")
    public void criarPedido(@PathVariable(name = "clientId") String clientId) {
        service.createOrder(clientId);
    }

}
