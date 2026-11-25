package br.unicentro.sistema_concessionaria.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarroTest {

    @Test
    void deveConstruirCarroComDadosValidos() {
        Carro carro = new Carro.CarroBuilder()
                .comPlaca("ABC1D23")
                .comModelo("Civic")
                .comMarca("Honda")
                .comAno(2020)
                .comPreco(85000.0)
                .comImagem("civic.png")
                .build();

        assertNotNull(carro, "O objeto Carro construído pelo CarroBuilder não deve ser nulo");
        assertEquals("ABC1D23", carro.getPlaca(),
                "A placa do carro deve corresponder ao valor configurado no Builder");
        assertEquals("Civic", carro.getModelo(),
                "O modelo do carro deve corresponder ao valor configurado no Builder");
        assertEquals(85000.0, carro.getPreco(), 0.001,
                "O preço do carro deve corresponder ao valor configurado no Builder");
        assertNotEquals(0.0, carro.getPreco(),
                "O preço de um carro válido não deve permanecer zerado");
    }

    @Test
    void deveRejeitarPrecoInvalidoNoBuilder() {
        assertThrows(IllegalArgumentException.class,
                () -> new Carro.CarroBuilder().comPreco(-5000.0),
                "O CarroBuilder deve lançar IllegalArgumentException ao informar preço negativo");
        assertThrows(IllegalArgumentException.class,
                () -> new Carro.CarroBuilder().comPreco(0.0),
                "O CarroBuilder deve lançar IllegalArgumentException ao informar preço igual a zero");
    }
}