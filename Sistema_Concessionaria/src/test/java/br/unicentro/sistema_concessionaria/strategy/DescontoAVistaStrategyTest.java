package br.unicentro.sistema_concessionaria.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DescontoAVistaStrategyTest {

    private DescontoAVistaStrategy strategy;

    @BeforeEach
    void preparar() {
        strategy = new DescontoAVistaStrategy();
    }

    @Test
    void deveCalcularValorFinalComDescontoParaEntradasValidas() {
        double precoCarro1 = 100000.0;
        double precoCarro2 = 50000.0;
        double resultado1 = strategy.calcularValorFinal(precoCarro1);
        double resultado2 = strategy.calcularValorFinal(precoCarro2);

        assertEquals(95000.0, resultado1, 0.001,
                "Para um veículo de R$ 100.000,00 à vista, o valor final com 5% de desconto deve ser R$ 95.000,00");
        assertEquals(47500.0, resultado2, 0.001,
                "Para um veículo de R$ 50.000,00 à vista, o valor final com 5% de desconto deve ser R$ 47.500,00");
        assertNotEquals(precoCarro1, resultado1,
                "O valor final à vista não deve ser igual ao preço base quando o desconto é aplicado");
        assertTrue(resultado1 < precoCarro1,
                "O valor final à vista deve ser menor que o valor original do veículo");
    }

    @Test
    void deveRejeitarPrecoBaseInvalido() {
        double precoNegativo = -10000.0;
        double precoZero = 0.0;

        assertThrows(IllegalArgumentException.class,
                () -> strategy.calcularValorFinal(precoNegativo),
                "Deve lançar IllegalArgumentException ao informar preço negativo");
        assertThrows(IllegalArgumentException.class,
                () -> strategy.calcularValorFinal(precoZero),
                "Deve lançar IllegalArgumentException ao informar preço igual a zero");
    }
}