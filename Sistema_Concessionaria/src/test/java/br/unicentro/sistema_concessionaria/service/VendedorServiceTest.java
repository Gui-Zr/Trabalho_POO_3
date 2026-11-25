package br.unicentro.sistema_concessionaria.service;

import br.unicentro.sistema_concessionaria.model.Vendedor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VendedorServiceTest {

    private VendedorService vendedorService;

    @BeforeEach
    void preparar() {
        vendedorService = new VendedorService();
    }

    @Test
    void deveRejeitarCadastroComCpfOuNomeOuSenhaInvalidos() {
        // Arrange
        Vendedor vendedorSemCpf = new Vendedor();
        vendedorSemCpf.setCpf("");
        vendedorSemCpf.setNome("Zero");
        vendedorSemCpf.setSenha("senha123");

        Vendedor vendedorSemNome = new Vendedor();
        vendedorSemNome.setCpf("12345678901");
        vendedorSemNome.setNome("   ");
        vendedorSemNome.setSenha("senha123");

        Vendedor vendedorSemSenha = new Vendedor();
        vendedorSemSenha.setCpf("12345678901");
        vendedorSemSenha.setNome("Zero");
        vendedorSemSenha.setSenha(null);

        // Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> vendedorService.salvarOuAtualizar(vendedorSemCpf),
                "Deve lançar IllegalArgumentException quando o CPF estiver vazio");
        assertThrows(IllegalArgumentException.class,
                () -> vendedorService.salvarOuAtualizar(vendedorSemNome),
                "Deve lançar IllegalArgumentException quando o nome estiver em branco");
        assertThrows(IllegalArgumentException.class,
                () -> vendedorService.salvarOuAtualizar(vendedorSemSenha),
                "Deve lançar IllegalArgumentException quando a senha for nula");
    }

    @Test
    void deveRejeitarExclusaoComCpfVazioOuNulo() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> vendedorService.excluir(""),
                "Deve lançar IllegalArgumentException ao tentar excluir com CPF vazio");
        assertThrows(IllegalArgumentException.class,
                () -> vendedorService.excluir(null),
                "Deve lançar IllegalArgumentException ao tentar excluir com CPF nulo");
    }
}