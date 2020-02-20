package br.com.contmatic.empresa;

import static java.math.BigDecimal.ZERO;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;

import br.com.contmatic.empresa.Cliente;

@FixMethodOrder(NAME_ASCENDING)
public class ClienteTest {

	private String cpf;

	private String nome;

	private String telefone;

	private BigDecimal boleto;

	private static Cliente cliente;

	@BeforeClass
	public static void setUpBeforeClass() {
		System.out.println("Iniciamos os testes na classe cliente");
	}

	@Before
	public void setUp() {
		cpf = "43701888818";
		nome = "Gabriel";
		telefone = "27219389";
		boleto = BigDecimal.valueOf(250.00);
		cliente = new Cliente(cpf, nome, telefone, boleto);
	}

	@Test
	public void nao_deve_aceitar_cpf_nulo() {
		assertNotNull(cliente.getCpf());
	}

	@Test
	public void nao_deve_aceitar_nome_nulo() {
		assertNotNull(cliente.getNome());
	}

	@Test
	public void nao_deve_aceitar_telefone_nulo() {
		cliente.setTelefone("27219389");
		assertNotNull(cliente.getTelefone());
	}

	@Test
	public void nao_deve_aceitar_boleto_nulo() {
		assertNotNull(cliente.getBoleto());
	}

	@Test
	public void deve_testar_o_getCpf_esta_funcionando_corretamente() { 
		cliente.setCpf("437.018.888-18");
		assertThat(cliente.getCpf(), containsString("437.018.888-18"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_o_exception_do_setcpf() {
		cliente.setCpf(null);
	}

	@Test
	public void deve_testar_o_getNome_esta_funcionando_corretamente() {
		cliente.setNome("Gabriel");
		assertThat(cliente.getNome(), containsString("Gabriel"));
	}

	@Test
	public void deve_testar_o_getTelefone_esta_funcionando_corretamente() {
		cliente.setTelefone("2721-9389");
		assertThat(cliente.getTelefone(), containsString("2721-9389"));
	}

	@Test
	public void deve_testar_o_getBoleto_esta_funcionando_corretamente() {
		cliente.setBoleto(BigDecimal.valueOf(250.00));
		assertThat(cliente.getBoleto(), is(BigDecimal.valueOf(250.00)));
	}

	@Test
	public void nao_deve_aceitar_espacos_em_branco_no_cpf() {
		assertFalse(cliente.getCpf().trim().isEmpty());
	}

	@Test
	public void nao_deve_aceitar_espacos_em_branco_no_nome() {
		assertFalse(cliente.getNome().trim().isEmpty());
	}

	@Test
	public void nao_deve_aceitar_espacos_em_branco_no_telefone() {
		assertFalse(cliente.getTelefone().trim().isEmpty());
	}

	@Test
	public void deve_retornar_true_no_hashCode_com_clientes_iguais() {
		Cliente cliente2 = new Cliente("43701888818", "Gabriel", "27219389", BigDecimal.valueOf(250.00));
		assertTrue(cliente.hashCode() == cliente2.hashCode());
	}

	@Test
	public void deve_retornar_false_no_hashCode_com_um_cliente_de_cpf_null() {
		Cliente cliente2 = new Cliente(null, "Gabriel", "2721-9389", BigDecimal.valueOf(250.00));
		assertFalse(cliente.hashCode() == cliente2.hashCode());
	}

	@Test
	public void deve_retornar_true_no_equals_com_clientes_iguais() {
		Cliente cliente2 = new Cliente("43701888818", "Gabriel", "27219389", BigDecimal.valueOf(250.00));
		assertTrue(cliente.equals(cliente2) & cliente2.equals(cliente));
	}

	@Test
	public void deve_retornar_false_no_equals_com_um_cliente_de_cpf_null() {
		Cliente cliente2 = new Cliente(null, "Gabriela", "2721-9390", BigDecimal.valueOf(270.00));
		assertFalse(cliente.equals(cliente2) & cliente2.equals(cliente));
	}

	@Test
	public void deve_retornar_true_no_equals_comparando_um_cliente_com_ele_mesmo() {
		assertTrue(cliente.equals(cliente));
	}

	@Test
	public void deve_retornar_false_no_equals_comparando_um_cliente_com_null() {
		assertFalse(cliente.equals(null));
	}

	@Test
	public void deve_retornar_true_no_equals_comparando_dois_clientes_de_cpf_null() {
		Cliente cliente1 = new Cliente(null, "Gabriel", "2721-9389", BigDecimal.valueOf(250.00));
		Cliente cliente2 = new Cliente(null, "Gabriela", "2721-9390", BigDecimal.valueOf(270.00));
		assertTrue(cliente1.equals(cliente2));
	}

	@Test
	public void deve_retornar_false_no_equals_com_clientes_de_cpf_diferentes() {
		Cliente cliente1 = new Cliente("437.018.888-20", "Gabriel", "2721-9389", BigDecimal.valueOf(250.00));
		Cliente cliente2 = new Cliente("437.018.888-19", "Gabriela", "2721-9390", BigDecimal.valueOf(270.00));
		assertFalse(cliente2.equals(cliente1));
	}

	@Test
	public void deve_retornar_false_no_equals_com_clientes_e_um_numero_aleatorio() {
		assertFalse(cliente.equals(new Object()));
	}

	@Test
	public void toString_deve_retornar_null() {
		Cliente clienteNull = new Cliente(null, null, null, new BigDecimal("0"));
		assertThat(clienteNull.toString(), containsString("boleto"));
	}

	@Test
	public void toString_deve_retornar_valores_preenchidos() {
		Cliente clienteNull = new Cliente("437.018.888-20", "Gabriel", "2721-9389", BigDecimal.valueOf(250.00));
		assertThat(clienteNull.toString(), containsString("boleto"));
	}

	@After
	@Ignore
	public void tearDown() {
		cpf = null;
		nome = null;
		telefone = null;
		boleto = ZERO;
	}

	@AfterClass
	public static void TearDownAfterClass() {
		System.out.println(cliente);
		System.out.println("Finalizamos os testes na classe cliente\n");
	}
}