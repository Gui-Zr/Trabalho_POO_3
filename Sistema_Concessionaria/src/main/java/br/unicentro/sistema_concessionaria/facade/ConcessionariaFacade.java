package br.unicentro.sistema_concessionaria.facade;

import br.unicentro.sistema_concessionaria.model.Carro;
import br.unicentro.sistema_concessionaria.model.Venda;
import br.unicentro.sistema_concessionaria.model.Vendedor;
import br.unicentro.sistema_concessionaria.service.CarroService;
import br.unicentro.sistema_concessionaria.service.VendaService;
import br.unicentro.sistema_concessionaria.service.VendedorService;
import br.unicentro.sistema_concessionaria.strategy.*;

import java.time.LocalDate;
import java.util.List;

public class ConcessionariaFacade {

    private final CarroService carroService;
    private final VendedorService vendedorService;
    private final VendaService vendaService;

    public ConcessionariaFacade() {
        this.carroService = new CarroService();
        this.vendedorService = new VendedorService();
        this.vendaService = new VendaService();
    }

    /**
     * Fachada para orquestrar o fluxo completo de realização de uma venda.
     * Ela seleciona a Strategy adequada, valida as informações e salva via Service.
     */
    public void registrarOuAtualizarVenda(Venda venda, String tipoPagamento) throws Exception {
        // 1. Seleção da Strategy de pagamento
        CalculoVendaStrategy estrategia;
        if (tipoPagamento != null && tipoPagamento.contains("À Vista")) {
            estrategia = new DescontoAVistaStrategy();
        } else if (tipoPagamento != null && tipoPagamento.contains("Financiado")) {
            estrategia = new VendaFinanciadaStrategy();
        } else {
            estrategia = new VendaNormalStrategy();
        }

        // 2. Orquestra o registro através do VendaService usando a Strategy
        vendaService.registrarVendaComEstrategia(venda, estrategia);
    }

    public void excluirVenda(int id) throws Exception {
        vendaService.excluir(id);
    }

    public List<Venda> listarTodasVendas() {
        return vendaService.listarTodas();
    }

    public List<Carro> listarTodosCarros() {
        return carroService.listarTodos();
    }

    public List<Vendedor> listarTodosVendedores() {
        return vendedorService.listarTodos();
    }
}