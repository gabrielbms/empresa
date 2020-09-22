package br.com.contmatic.empresa;

import static java.math.BigDecimal.ZERO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;

import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.telefone.TelefoneDDDType;
import br.com.contmatic.telefone.TipoTelefoneType;

@FixMethodOrder(NAME_ASCENDING)
public class ClienteTest {

	private String cpf;

	private String nome;

	private BigDecimal boleto;

	private static Cliente cliente;
	
	private static Telefone telefone;

	@BeforeClass
	public static void setUpBeforeClass() {
		System.out.println("Iniciamos os testes na classe cliente");
	}

	@Before
	public void setUp() {
		cpf = "20081498896";
		nome = "Gabriel";
		boleto = BigDecimal.valueOf(250.00);
		cliente = new Cliente(cpf, nome, boleto);
		telefone = new Telefone(TelefoneDDDType.DDD11, "978457845", TipoTelefoneType.CELULAR);
	}
	
	@Test
	public void deve_testar_se_o_cpf_aceita_numeros() {
		cliente.setCpf("43701888817");
		assertEquals("43701888817", cliente.getCpf());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_cpf_aceita_null() {
		cliente.setCpf(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_cpf_aceita_vazio() {
		cliente.setCpf("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_cpf_aceita_espaco_em_branco() {
		cliente.setCpf("  ");
	}
	
	@Test(expected = IllegalStateException.class)
	public void deve_testar_se_o_cpf_aceita_letras() {
		cliente.setCpf("abcdefabcde");
	}
	
	@Test(expected = IllegalStateException.class)
	public void deve_testar_se_o_cpf_aceita_cpf_invalido() {
		cliente.setCpf("43701888818");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_cpf_aceita_caracteres_especiais() {
		cliente.setCpf("@#$");
	}
		
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_cpf_aceita_espaco_no_inicio() {
		cliente.setCpf(" 43701888817");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_cpf_aceita_espaco_no_final() {
		cliente.setCpf("43701888817 ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_cpf_aceita_muitos_espacos_entre_os_numeros() {
		cliente.setCpf("437018      88817");
	}
	
	@Test
	public void deve_testar_o_setCpf() {
		cliente.setCpf("43701888817");
		assertEquals(cliente.getCpf(), "43701888817");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setCpf_tamanho_menor() {
		cliente.setCpf("1010101010");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setCpf_tamanho_maior() {
		cliente.setCpf("121212121212");
	}

	@Test
	public void deve_testar_se_o_nome_aceita_letras() {
		cliente.setNome("Gabriel");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_nome_aceita_null() {
		cliente.setNome(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_nome_aceita_vazio() {
		cliente.setNome("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_nome_aceita_espaco_em_branco() {
		cliente.setNome("          ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_nome_aceita_numeros() {
		cliente.setNome("123456");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_nome_aceita_caracteres_especiais() {
		cliente.setNome("@#$");
	}
		
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_nome_aceita_espaco_no_inicio() {
		cliente.setNome(" Gabriel");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_nome_aceita_espaco_no_final() {
		cliente.setNome("Gabriel ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_nome_aceita_muitos_espacos_entre_as_palavras() {
		cliente.setNome("Gabriel         Bueno");
	}
	
	@Test
	public void deve_testar_se_o_nome_aceita_um_espaco_entre_as_palavras() {
		cliente.setNome("Gabriel Bueno");
	}
	
	@Test
	public void deve_testar_o_getNome() {
		cliente.setNome("Gabriel Bueno");
		assertEquals(cliente.getNome(), "Gabriel Bueno");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setNome_tamanho_menor() {
		cliente.setNome("a");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setNome_tamanho_maior() {
		cliente.setNome("abcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcaabcabcabcabcabcaabcabcabc"
				+ "abcabcaabcabcabcabcabcabcabcabcabcabcabxc");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_telefone_nulo() {
		cliente.setTelefone(null);
	}
	
	@Test
	public void deve_testar_o_getTelefone() {
		cliente.getTelefone();
	}
	
	@Test
	public void deve_testar_o_getBoleto() {
		cliente.getBoleto();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_o_exception_do_boleto() {
		cliente.setBoleto(BigDecimal.valueOf(-20.00));
	}

	@Test
	public void deve_retornar_true_no_hashCode_com_clientes_iguais() {
		Cliente cliente2 = new Cliente("20081498896", "Gabriel", telefone, BigDecimal.valueOf(250.00));
		assertEquals(cliente.hashCode(), cliente2.hashCode());
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_retornar_false_no_hashCode_com_um_cliente_de_cpf_null() {
		Cliente cliente2 = new Cliente(null, "Gabriel", telefone, BigDecimal.valueOf(250.00));
		assertNotEquals(cliente.hashCode(), cliente2.hashCode());
	}

	@Test
	public void deve_retornar_true_no_equals_com_clientes_iguais() {
		Cliente cliente2 = new Cliente("20081498896", "Gabriel", telefone, BigDecimal.valueOf(250.00));
		assertEquals(cliente, cliente2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_retornar_false_no_equals_com_um_cliente_de_cpf_null() {
		Cliente cliente2 = new Cliente(null, "Gabriela", telefone, BigDecimal.valueOf(270.00));
		assertNotEquals(cliente, cliente2);
	}

	@Test
	public void deve_retornar_true_no_equals_comparando_um_cliente_com_ele_mesmo() {
		assertEquals(cliente, cliente);
	}

	@Test
	public void deve_retornar_false_no_equals_comparando_um_cliente_com_null() {
		assertNotEquals(cliente, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_retornar_true_no_equals_comparando_dois_clientes_de_cpf_null() {
		Cliente cliente1 = new Cliente(null, "Gabriel", telefone, BigDecimal.valueOf(250.00));
		Cliente cliente2 = new Cliente(null, "Gabriela", telefone, BigDecimal.valueOf(270.00));
		assertEquals(cliente1, cliente2);
	}

	@Test(expected = IllegalStateException.class)
	public void deve_retornar_false_no_equals_com_clientes_de_cpf_diferentes() {
		Cliente cliente1 = new Cliente("43701888820", "Gabriel", telefone, BigDecimal.valueOf(250.00));
		Cliente cliente2 = new Cliente("43701888819", "Gabriela", telefone, BigDecimal.valueOf(270.00));
		assertNotEquals(cliente2, cliente1);
	}

	@Test
	public void deve_retornar_false_no_equals_com_clientes_e_um_numero_aleatorio() {
		assertNotEquals(cliente, new Object());
	}

	@Test(expected = IllegalArgumentException.class)
	public void toString_deve_retornar_null() {
		Cliente clienteNull = new Cliente(null, null, null, new BigDecimal("1"));
		String clienteNullToString = clienteNull.toString();
		assertEquals(clienteNull.toString(), clienteNullToString);
	}

	@Test
	public void toString_deve_retornar_valores_preenchidos() {
		String clienteToString = cliente.toString();
		assertEquals(cliente.toString(), clienteToString);
	}
	
	@After
	@Ignore
	public void tearDown() {
		cpf = null;
		nome = null;
		boleto = ZERO;
	}

	@AfterClass
	public static void TearDownAfterClass() {
		System.out.println(cliente);
		System.out.println("Finalizamos os testes na classe cliente\n");
	}
}