package br.com.contmatic.empresa;

import static java.math.BigDecimal.ZERO;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;

import br.com.contmatic.util.Constantes;

@FixMethodOrder(NAME_ASCENDING)
public class ClienteTest {

	private String cpf;

	private String nome;

	private BigDecimal boleto;

	private static Cliente cliente;
	
	private Validator validator;

	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

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
	}
	
	public boolean isValid(Cliente cliente, String mensagem) {
		validator = factory.getValidator();
		boolean valido = true;
		Set<ConstraintViolation<Cliente>> restricoes = validator.validate(cliente);
		for (ConstraintViolation<Cliente> constraintViolation : restricoes)
			if (constraintViolation.getMessage().equalsIgnoreCase(mensagem))
				valido = false;
		return valido;
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
		cliente.setCpf("22594921858");
		assertThat(cliente.getCpf(), containsString("22594921858"));
	}

	@Test
	public void deve_testar_o_getNome_esta_funcionando_corretamente() {
		cliente.setNome("Gabriel");
		assertThat(cliente.getNome(), containsString("Gabriel"));
	}

	@Test
	public void deve_testar_o_getTelefone_esta_funcionando_corretamente() {
		cliente.setTelefone("27219389");
		assertThat(cliente.getTelefone(), containsString("27219389"));
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
		cliente.setTelefone("123456788");
		assertFalse(cliente.getTelefone().trim().isEmpty());
	}

	@Test
	public void deve_retornar_true_no_hashCode_com_clientes_iguais() {
		Cliente cliente2 = new Cliente("20081498896", "Gabriel", "27219389", BigDecimal.valueOf(250.00));
		assertEquals(cliente.hashCode(), cliente2.hashCode());
	}

	@Test
	public void deve_retornar_false_no_hashCode_com_um_cliente_de_cpf_null() {
		Cliente cliente2 = new Cliente(null, "Gabriel", "27219389", BigDecimal.valueOf(250.00));
		assertNotEquals(cliente.hashCode(), cliente2.hashCode());
	}

	@Test
	public void deve_retornar_true_no_equals_com_clientes_iguais() {
		Cliente cliente2 = new Cliente("20081498896", "Gabriel", "27219389", BigDecimal.valueOf(250.00));
		assertTrue(cliente.equals(cliente2) & cliente2.equals(cliente));
	}

	@Test
	public void deve_retornar_false_no_equals_com_um_cliente_de_cpf_null() {
		Cliente cliente2 = new Cliente(null, "Gabriela", "27219390", BigDecimal.valueOf(270.00));
		assertFalse(cliente.equals(cliente2) & cliente2.equals(cliente));
	}

	@Test
	public void deve_retornar_true_no_equals_comparando_um_cliente_com_ele_mesmo() {
		assertEquals(cliente, cliente);
	}

	@Test
	public void deve_retornar_false_no_equals_comparando_um_cliente_com_null() {
		assertNotEquals(cliente, null);
	}

	@Test
	public void deve_retornar_true_no_equals_comparando_dois_clientes_de_cpf_null() {
		Cliente cliente1 = new Cliente(null, "Gabriel", "27219389", BigDecimal.valueOf(250.00));
		Cliente cliente2 = new Cliente(null, "Gabriela", "27219390", BigDecimal.valueOf(270.00));
		assertEquals(cliente1, cliente2);
	}

	@Test
	public void deve_retornar_false_no_equals_com_clientes_de_cpf_diferentes() {
		Cliente cliente1 = new Cliente("43701888820", "Gabriel", "27219389", BigDecimal.valueOf(250.00));
		Cliente cliente2 = new Cliente("43701888819", "Gabriela", "27219390", BigDecimal.valueOf(270.00));
		assertNotEquals(cliente2, cliente1);
	}

	@Test
	public void deve_retornar_false_no_equals_com_clientes_e_um_numero_aleatorio() {
		assertNotEquals(cliente, new Object());
	}

	@Test
	public void toString_deve_retornar_null() {
		Cliente clienteNull = new Cliente(null, null, null, new BigDecimal("1"));
		assertThat(clienteNull.toString(), containsString("boleto"));
	}

	@Test
	public void toString_deve_retornar_valores_preenchidos() {
		Cliente clienteNull = new Cliente("43701888820", "Gabriel", "27219389", BigDecimal.valueOf(250.00));
		assertThat(clienteNull.toString(), containsString("boleto"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setCpf_null() {
		cliente.setCpf(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setCpf_vazio() {
		cliente.setCpf(" ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setCpf_tamanho_menor() {
		cliente.setCpf("1010101010");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setCpf_tamanho_maior() {
		cliente.setCpf("121212121212");
	}
		
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setNome_null() {
		cliente.setNome(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setNome_vazio() {
		cliente.setNome(" ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setNome_tamanho_menor() {
		cliente.setNome("a");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setNome_tamanho_maior() {
		cliente.setNome("abcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabxc");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setTelefone_null() {
		cliente.setTelefone(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setTelefone_vazio() {
		cliente.setTelefone(" ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setTelefone_tamanho_menor() {
		cliente.setTelefone("1234567");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setTelefone_tamanho_maior() {
		cliente.setTelefone("1234567890");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setBoleto_negativo() {
		cliente.setBoleto(BigDecimal.valueOf(-50.00));
	}
	
	@Test
	public void deve_testar_o_regex_do_nome() {
		cliente.setNome("1234567890");
		assertFalse(isValid(cliente, Constantes.NOME_INVALIDO));
	}
	
	@Test
	public void deve_testar_o_regex_do_telefone() {
		cliente.setTelefone("abcabcabc");
		assertFalse(isValid(cliente, Constantes.TELEFONE_INVALIDO));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_o_isCpf_invalido() {
		cliente.setCpf("12345678912");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_o_isCpf_numeros_iguais() {
		cliente.setCpf("11111111111");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_o_isCpf_tamanho_incorreto() {
		cliente.setCpf("123");
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