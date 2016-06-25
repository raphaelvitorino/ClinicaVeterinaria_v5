package consultorioveterinario.model;

public class Item {

    private int quantidade;
    private double valorUnitario;
    private Servico servico;
    private Animal animal;

    public Item(int quantidade, double valorUnitario, Servico servico, Animal animal) {
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.servico = servico;
        this.animal = animal;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

}
