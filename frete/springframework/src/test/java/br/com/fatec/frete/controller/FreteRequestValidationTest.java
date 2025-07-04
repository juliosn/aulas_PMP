package br.com.fatec.frete.controller;

import br.com.fatec.frete.config.FreteServiceTestConfig;
import br.com.fatec.frete.controller.dto.request.PedidoRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import br.com.fatec.frete.service.FreteService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(FreteServiceTestConfig.class)
@WebMvcTest(FreteController.class)
class FreteRequestValidationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private FreteService freteService;

    @Test
    void deveRetornar400QuandoNomeEstaEmBranco() throws Exception {
        PedidoRequest request = new PedidoRequest(
                "", "email@email.com", "01000-000",
                "Rua A", "Compl", "Centro", "SP", "SP");

        mockMvc.perform(post("/frete/v1/calcular")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRetornar400QuandoEmailInvalido() throws Exception {
        PedidoRequest request = new PedidoRequest(
                "João", "email_invalido", "01000-000",
                "Rua A", "Compl", "Centro", "SP", "SP");

        mockMvc.perform(post("/frete/v1/calcular")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRetornar400QuandoUfNula() throws Exception {
        PedidoRequest request = new PedidoRequest(
                "João", "email@email.com", "01000-000",
                "Rua A", "Compl", "Centro", "SP", "");

        mockMvc.perform(post("/frete/v1/calcular")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveAceitarQuandoTodosCamposEstaoValidos() throws Exception {
        PedidoRequest request = new PedidoRequest(
                "João", "email@email.com", "01000-000",
                "Rua A", "Compl", "Centro", "SP", "SP");

        mockMvc.perform(post("/frete/v1/calcular")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }
}
