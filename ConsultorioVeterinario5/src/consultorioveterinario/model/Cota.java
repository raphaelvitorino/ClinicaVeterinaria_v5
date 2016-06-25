/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultorioveterinario.model;

/**
 *
 * @author fabiano.eger
 */
public class Cota {
	private Cliente cliente;
	private Animal animal;
	private double percentual;

	public Cota(Cliente cliente, Animal animal, double percentual) {
		this.cliente = cliente;
		this.percentual = percentual;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public double getPercentual() {
		return percentual;
	}

	public void setPercentual(double percentual) {
		this.percentual = percentual;
	}

}
