package consultorioveterinario.model;

import java.util.ArrayList;
import java.util.List;

public class OrdemServico {

	protected List<Item> itens;
	protected double valorTotal;
	protected Cliente cliente;

	public OrdemServico(List<Item> itens, Cliente cliente) {
		this.itens = itens;
		this.cliente = cliente;
	}

	public OrdemServico() {
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Fatura executar(OrdemServico ordemServico) {
		Fatura fatura = new Fatura();
		double valorOrdem = 0;
		for (Item item : itens) {
			valorOrdem += item.getQuantidade() * item.getValorUnitario();
		}
		ordemServico.setValorTotal(valorOrdem);
		fatura.addOS(ordemServico);
		fatura.setStatus("Em aberto.");
		return fatura;
	}

	public List<Fatura> executarOrdemComCota(OrdemServico ordemServico, List<Cota> cotas) {
		List<Fatura> faturas = new ArrayList<>();
		double valorOrdem = 0.00;
		double valorCotado = 0.00;
		for (Item item : itens) {
			valorOrdem += item.getQuantidade() * item.getValorUnitario();
		}
		for (Cota cota : cotas) {
			Fatura fatura = new Fatura();
			valorCotado = valorCota(cota.getPercentual(), valorOrdem);
			ordemServico.setValorTotal(valorCotado);
			fatura.addOS(ordemServico);
			fatura.setStatus("Em aberto.");
			faturas.add(fatura);
		}		
		return faturas;
	}

	public double valorCota(double percentual, double valor) {
		double percentualAjustado = percentual / 100;
		double resultado = percentualAjustado * valor;
		return resultado;
	}

}
