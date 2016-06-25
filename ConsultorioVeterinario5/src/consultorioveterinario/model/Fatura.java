package consultorioveterinario.model;

import java.util.ArrayList;
import java.util.List;

public class Fatura {

    private String status;
    private List<OrdemServico> os;
    private boolean avista;

    public boolean isAvista() {
        return avista;
    }

    public void setAvista(boolean avista) {
        this.avista = avista;
    }
    
    
    public String getStatus() {
    return status;
    }
    public Fatura() {
        this.os = new ArrayList<>();
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrdemServico> getOs() {
        return os;
    }

    public void setOs(List<OrdemServico> os) {
        this.os = os;
    }
    
    public void addOS(OrdemServico ordem) {
        os.add(ordem);
    }

    public Recibo pagar(double dinheiroRecebido, OrdemServico os) {
        Recibo recibo = new Recibo();

        double valorFatura = os.getValorTotal();

        double restantePagar = valorFatura - dinheiroRecebido;

        if (restantePagar == 0) {
            this.setStatus("Pago");
        } else if (restantePagar > 0) {
            this.setStatus("Pagamento parcial.");
        } else if (restantePagar < 0) {
            this.setStatus("Juros / acréscimos.");
        }

        recibo.setValorFatura(restantePagar);

        return recibo;
    }
    
    public Fatura gerarFatura(List<OrdemServico> os) {
        Fatura fatura = new Fatura();
        
        return fatura;
    }
    
    public void pagarFimDoMes(Fatura fatura) {
        fatura.setAvista(false);
        fatura.setStatus("A pagar");
    }
    
    public String emitirDeclaracao() {
    	
    }
  
}
