package br.unicentro.sistema_concessionaria.strategy;

public class DescontoAVistaStrategy implements CalculoVendaStrategy {
    @Override
    public double calcularValorFinal(double precoBase) {
        if (precoBase <= 0) {
            throw new IllegalArgumentException("O preço base deve ser maior que zero.");
        }
        return precoBase * 0.95; // 5% de desconto
    }
}