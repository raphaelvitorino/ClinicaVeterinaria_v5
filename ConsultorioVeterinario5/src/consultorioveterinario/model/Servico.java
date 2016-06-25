package consultorioveterinario.model;

public class Servico {

    private String nomeServico;
    private double valor;

    public Servico(String nomeServico, double valor) {
        this.nomeServico = nomeServico;
        this.valor = valor;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
