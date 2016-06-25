package consultorioveterinario.model;

import java.util.ArrayList;
import java.util.List;

public class Animal {

    private String nomeAnimal;
    private String raca;
    private List<Cliente> clientes;

    public Animal(String nomeAnimal, String raca, List<Cliente> clientes) {
        this.nomeAnimal = nomeAnimal;
        this.raca = raca;
        this.clientes = clientes;
    }

	public String getNomeAnimal() {
		return nomeAnimal;
	}

	public void setNomeAnimal(String nomeAnimal) {
		this.nomeAnimal = nomeAnimal;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}


}
