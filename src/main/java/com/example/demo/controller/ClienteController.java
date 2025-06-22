package com.example.demo.controller;

import com.example.demo.application.service.ClienteService;
import com.example.demo.controller.dto.ClienteRequest;
import com.example.demo.controller.dto.ClienteResponse;
import com.example.demo.domain.model.Cliente;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	private final ClienteService service;

	public ClienteController(ClienteService service) {
		this.service = service;
	}

	@PostMapping
	public ClienteResponse cadastro(@RequestBody ClienteRequest request) {
		Cliente cliente = service.cadastrar(request.nome());
		return new ClienteResponse("Cadastrado com ID: " + cliente.getId());
	}

	@GetMapping
	public List<Cliente> listar() {
		return service.listar();
	}

	@GetMapping("/{id}")
	public Cliente buscar(@PathVariable Long id) {
		return service.buscarPorId(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
	}

	@PatchMapping("/{id}")
	public ClienteResponse atualizar(@PathVariable Long id, @RequestBody ClienteRequest request) {
		Cliente cliente = service.atualizar(id, request.nome());
		return new ClienteResponse("Atualizado: " + cliente.getNome());
	}


	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		service.excluir(id);
	}
}
