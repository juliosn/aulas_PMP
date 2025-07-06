package br.com.fatec.frete.controller;

import br.com.fatec.frete.config.FreteServiceTestConfig;
import br.com.fatec.frete.controller.dto.request.PedidoRequest;
import br.com.fatec.frete.entity.*;
import br.com.fatec.frete.service.FreteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(FreteServiceTestConfig.class)
@WebMvcTest(FreteController.class)
class FreteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FreteService freteService;

    @Autowired
    private ObjectMapper objectMapper;

    private final Endereco endereco = new Endereco("01000-000", "Rua A", "Compl", "Centro", "SP", "SP");
    private final User usuario = new User("123", "João", "joao@email.com");
    private final Pedido pedido = new Pedido("1", usuario, endereco, 0.0, StatusPedido.PROCESSANDO);

    @Test
    void deveRetornar201ECriarFrete() throws Exception {
        PedidoRequest request = new PedidoRequest("João", "joao@email.com", "01000-000", "Rua A", "Compl", "Centro", "SP", "SP");

        when(freteService.calcularFrete(any(), any())).thenReturn(pedido);

        mockMvc.perform(post("/frete/v1/pedido") // <--- aqui o endpoint correto
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome", is("João")))
                .andExpect(jsonPath("$.email", is("joao@email.com")))
                .andExpect(jsonPath("$.cep", is("01000-000")))
                .andExpect(jsonPath("$.valorFrete", is(0.0)))
                .andExpect(jsonPath("$.status", is("PROCESSANDO")));
    }


    @Test
    void deveRetornarPedidoPorId() throws Exception {
        String pedidoId = "6869da6a7cbd1a29cea869a9";
        Pedido pedido = new Pedido(pedidoId, usuario, endereco, 0.0, StatusPedido.PROCESSANDO);

        when(freteService.buscarPorId(pedidoId)).thenReturn(pedido);

        mockMvc.perform(get("/frete/v1/pedido/" + pedidoId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(pedidoId)))
                .andExpect(jsonPath("$.nome", is("João")))
                .andExpect(jsonPath("$.status", is("PROCESSANDO")));
    }
}
