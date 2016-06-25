package consultorioveterinario.test;

import consultorioveterinario.model.Animal;
import consultorioveterinario.model.Cliente;
import consultorioveterinario.model.Cota;
import consultorioveterinario.model.Fatura;
import consultorioveterinario.model.Item;
import consultorioveterinario.model.OrdemServico;
import consultorioveterinario.model.Recibo;
import consultorioveterinario.model.Servico;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestConsultorioVeterinario {

	public TestConsultorioVeterinario() {
	}

	@Before
	public void setUp() {

	}

	@After
	public void tearDown() {
	}

	@Test
	public void testServico() {
		Servico servico = new Servico("Exame Rotina", 120);
		Cliente cliente = new Cliente("Dave Atkins");
		assertEquals("Dave Atkins", cliente.getNomeCliente());
		assertEquals("Exame Rotina", servico.getNomeServico());
		assertEquals(120, servico.getValor(), 0);
	}

	@Test
	public void testPagar() {
		Fatura f = new Fatura();
		double dinheiroRecebido = 100;
		OrdemServico ordemServico = new OrdemServico();
		ordemServico.setValorTotal(100);
		f.pagar(dinheiroRecebido, ordemServico);
		assertEquals("Pago", f.getStatus());
	}

	@Test
	public void testExecutarOrdemServico() {
		List<Cliente> clientes = new ArrayList<>();
		List<Item> itens = new ArrayList<>();
		Fatura f;
		Cliente cliente = new Cliente("Dave Atkins");
		clientes.add(cliente);
		Animal animal = new Animal("Fofo", "Doberman", clientes);
		Servico servico = new Servico("Exame Rotina", 150);
		Servico servico2 = new Servico("Vacina Raiva", 50);
		Item item = new Item(1, 150, servico, animal);
		Item item2 = new Item(1, 50, servico2, animal);
		itens.add(item);
		itens.add(item2);
		OrdemServico os = new OrdemServico(itens, cliente);
		f = os.executar(os);
		assertEquals(200, f.getOs().get(0).getValorTotal(), 0);
	}

	@Test
	public void testEmitirFaturaFimDoMes() {
		List<Item> itens = new ArrayList<>();
		List<Cliente> clientes = new ArrayList<>();
		Fatura f;
		Cliente cliente = new Cliente("Traci Heinrich");
		clientes.add(cliente);
		Animal animal1 = new Animal("Tweedle Dee", "Gato", clientes);
		Animal animal2 = new Animal("Tweedle Dum", "Gato", clientes);
		Servico servico = new Servico("EsterilizaÃ§Ã£o", 300);
		Item item = new Item(1, 300, servico, animal1);
		Item item2 = new Item(1, 300, servico, animal2);
		itens.add(item);
		itens.add(item2);
		OrdemServico os = new OrdemServico(itens, cliente);
		f = os.executar(os);
		f.pagarFimDoMes(f);
		assertEquals(false, f.isAvista());
	}

	@Test
	public void testPagarCotaAnimal() {
		List<Fatura> faturas = new ArrayList<>();
		List<Item> itens = new ArrayList<>();
		List<Cliente> clientes = new ArrayList<>();
		List<Cota> cotas = new ArrayList<>();
		Cliente cliente1 = new Cliente("Grady Booch");
		Cliente cliente2 = new Cliente("Martin Fowler");
		Cliente cliente3 = new Cliente("Ralph Jhonson");
		Cliente cliente4 = new Cliente("Erich Gama");
		Cliente cliente5 = new Cliente("Brian Foote");
		clientes.add(cliente1);
		clientes.add(cliente2);
		clientes.add(cliente3);
		clientes.add(cliente4);
		clientes.add(cliente5);
		Animal animal = new Animal("Mensagem polimórfica", "Cavalo", clientes);
		Cota cota1 = new Cota(cliente1, animal, 30);
		Cota cota2 = new Cota(cliente2, animal, 20);
		Cota cota3 = new Cota(cliente3, animal, 20);
		Cota cota4 = new Cota(cliente4, animal, 10);
		Cota cota5 = new Cota(cliente5, animal, 20);
		cotas.add(cota1);
		cotas.add(cota2);
		cotas.add(cota3);
		cotas.add(cota4);
		cotas.add(cota5);

		Servico servico = new Servico("Esterilização", 300);
		Item item = new Item(1, 300, servico, animal);
		itens.add(item);
		OrdemServico os = new OrdemServico(itens, cliente1);
		faturas = os.executarOrdemComCota(os, cotas);
		for (Fatura f : faturas) {
			f.pagarFimDoMes(f);
			assertEquals(false, f.isAvista());
		}
	}

	@Test
	public void testPagarTaxaAdicional() {
		Cliente ralph = new Cliente("Ralph");
		ArrayList<Cliente> clientes = new ArrayList<>();
		List<Item> itens = new ArrayList<>();
		clientes.add(ralph);
		Animal animal = new Animal("Mensagem polimorfica", "Cavalo", clientes);
		double taxa = 80.00;
		Servico servico = new Servico("Saturar lacerações", taxa + 250);
		List<Servico> servicos = new ArrayList<>();
		servicos.add(servico);
		Item item = new Item(1, 300 + taxa, servico, animal);
		itens.add(item);
		OrdemServico ordemServico = new OrdemServico(itens, ralph);
		Fatura f = ordemServico.executar(ordemServico);
		f.pagarFimDoMes(f);
		assertEquals(380.00, f.getOs().get(0).getValorTotal(), 0);
	}
	
	//Ralph traz "Mensagem Polimórfica" para um exame de rotina, mas ele também traz sua iguana de estimação, 
	//"Coletora", que não tem comido bem. Dr. Roberts examina ambos. Ralph recebe uma fatura, que ele paga naquele dia,
	//que inclui a sua parte da conta do cavalo e da totalidade dos encargos da iguana. Mais tarde, naquele mês, 
	//os co-proprietários do cavalo recebem uma declaração com a sua parte dos encargos.

	@Test
	public void testPagarEncargos() {
		Fatura f;
		Recibo r;
		ArrayList<Cliente> clientes = new ArrayList<>();
		ArrayList<Item> itens = new ArrayList<>();
		Cliente ralph = new Cliente("Ralph");
		clientes.add(ralph);
		Animal iguana = new Animal("Coletora", "Iguana", clientes);
		Servico exame = new Servico("Exame de rotina", 150);
		Item item = new Item(1, 150, exame, iguana);
		itens.add(item);
		OrdemServico os = new OrdemServico(itens, ralph);
		f = os.executar(os);
		r = f.pagar(300, os);
		assertEquals(300, r.getValorFatura(), 0);
	}
	
}
