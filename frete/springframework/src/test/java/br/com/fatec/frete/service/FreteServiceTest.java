package br.com.fatec.frete.service;

import br.com.fatec.frete.entity.*;
import br.com.fatec.frete.exception.BadRequestException;
import br.com.fatec.frete.repository.PedidoRepository;
import br.com.fatec.frete.service.FreteService;
import br.com.fatec.frete.service.FreteService.FreteEventPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FreteServiceTest {

    private PedidoRepository repository;
    private FreteEventPublisher publisher;
    private FreteService service;

    private final User usuario = new User("123", "João", "joao@email.com");

    @BeforeEach
    void setup() {
        repository = mock(PedidoRepository.class);
        publisher = mock(FreteEventPublisher.class);
        service = new FreteService(repository, publisher);
    }

    @Test
    void deveCalcularFreteGratisParaSP() {
        Endereco endereco = new Endereco("01000-000", "Rua A", "", "Centro", "São Paulo", "SP");
        Pedido pedidoEsperado = new Pedido("123", usuario, endereco, 0.0, StatusPedido.PROCESSANDO);

        when(repository.save(any())).thenReturn(pedidoEsperado);

        Pedido resultado = service.calcularFrete(usuario, endereco);

        assertEquals(0.0, resultado.valorFrete());
        assertEquals(StatusPedido.PROCESSANDO, resultado.status());
        verify(repository).save(any());
        verify(publisher).send(any());
    }

    @Test
    void deveCalcularFrete20ParaRJ() {
        Endereco endereco = new Endereco("20000-000", "Rua B", "", "Centro", "Rio", "RJ");
        Pedido pedidoEsperado = new Pedido("123", usuario, endereco, 20.0, StatusPedido.PROCESSANDO);

        when(repository.save(any())).thenReturn(pedidoEsperado);

        Pedido resultado = service.calcularFrete(usuario, endereco);

        assertEquals(20.0, resultado.valorFrete());
    }

    @Test
    void deveCalcularFrete50ParaMG() {
        Endereco endereco = new Endereco("30000-000", "Rua C", "", "Centro", "Belo Horizonte", "MG");
        Pedido pedidoEsperado = new Pedido("123", usuario, endereco, 50.0, StatusPedido.PROCESSANDO);

        when(repository.save(any())).thenReturn(pedidoEsperado);

        Pedido resultado = service.calcularFrete(usuario, endereco);

        assertEquals(50.0, resultado.valorFrete());
    }

    @Test
    void deveLancarExcecaoParaEstadoNaoAtendido() {
        Endereco endereco = new Endereco("99999-000", "Rua Z", "", "Bairro", "Cidade", "AM");

        BadRequestException exception = assertThrows(
                BadRequestException.class,
                () -> service.calcularFrete(usuario, endereco)
        );

        assertTrue(exception.getMessage().contains("Não realizamos entregas"));
        verify(repository, never()).save(any());
        verify(publisher, never()).send(any());
    }

    @Test
    void deveBuscarPedidoPorId() {
        Pedido pedido = new Pedido("abc123", usuario, null, 0.0, StatusPedido.PROCESSANDO);
        when(repository.findById("abc123")).thenReturn(pedido);

        Pedido resultado = service.buscarPorId("abc123");

        assertEquals("abc123", resultado.id());
        verify(repository).findById("abc123");
    }

    @Test
    void deveDeletarPedido() {
        service.deletarPedido("abc123");
        verify(repository).delete("abc123");
    }
}
