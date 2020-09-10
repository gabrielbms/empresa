package br.com.contmatic.empresa;

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
import org.junit.Test;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.util.Constantes;

@FixMethodOrder(NAME_ASCENDING)
public class FuncionarioTest {

	private String cpf;

	private String nome;

	private int idade;

	private String telefone;

	private BigDecimal salario;

	private static Funcionario funcionario;

	private static Funcionario funcionarioSemEndereco;
	
	private static Funcionario funcionarioComEndereco;
	
	private Validator validator;

	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

	@BeforeClass
	public static void setUpBeforeClass() {
		System.out.println("Iniciamos os testes na classe funcionario");
	}

	@Before
	public void setUp() {
		cpf = "99074424880";
		nome = "Gabriel Bueno";
		idade = 25;
		telefone = "41108521";
		salario = BigDecimal.valueOf(1500.00);
		Endereco endereco = new Endereco("04508010", 274);
		funcionario = new Funcionario(cpf, nome, salario);
		funcionarioSemEndereco = new Funcionario(cpf, nome, idade, telefone, salario);
		funcionarioComEndereco = new Funcionario(cpf, nome, idade, telefone, endereco, salario);
	}
	
	public boolean isValid(Funcionario funcionario, String mensagem) {
		validator = factory.getValidator();
		boolean valido = true;
		Set<ConstraintViolation<Funcionario>> restricoes = validator.validate(funcionario);
		for (ConstraintViolation<Funcionario> constraintViolation : restricoes)
			if (constraintViolation.getMessage().equalsIgnoreCase(mensagem))
				valido = false;
		return valido;
	}

	@Test
	public void nao_deve_aceitar_cpf_nulo() {
		assertNotNull(funcionario.getCpf());
	}

	@Test
	public void nao_deve_aceitar_nome_nulo() {
		assertNotNull(funcionario.getNome());
	}

	@Test
	public void nao_deve_aceitar_telefone_nulo() {
		assertNotNull(funcionarioSemEndereco.getTelefone());
	}

	@Test
	public void nao_deve_aceitar_endereco_nulo() {
		assertNotNull(funcionarioComEndereco.getEndereco());
	}

	@Test
	public void nao_deve_aceitar_salario_nulo() {
		assertNotNull(funcionario.getSalario());
	}

	@Test
	public void deve_testar_o_getCpf_esta_funcionando_corretamente() {
		funcionario.setCpf("33484349808");
		assertThat(funcionario.getCpf(), containsString("33484349808"));
	}

	@Test
	public void deve_testar_o_getNome_esta_funcionando_corretamente() {
		funcionario.setNome("Gabriel Bueno");
		assertThat(funcionario.getNome(), containsString("Gabriel Bueno"));
	}

	@Test
	public void deve_testar_o_getIdade_esta_funcionando_corretamente() {
		funcionario.setIdade(25);
		assertThat(funcionario.getIdade(), is(25));
	}

	@Test
	public void deve_testar_o_getTelefone_esta_funcionando_corretamente() {
		funcionario.setTelefone("41108521");
		assertThat(funcionario.getTelefone(), containsString("41108521"));
	}

	@Test
	public void deve_testar_o_getEndereco_esta_funcionando_corretamente() {
		funcionario.setEndereco(new Endereco("04508010", 274));
		assertEquals(funcionario.getEndereco(), (new Endereco("04508010", 274)));
	}

	@Test
	public void deve_testar_o_getSalario_esta_funcionando_corretamente() {
		funcionario.setSalario(BigDecimal.valueOf(1500.00));
		assertThat(funcionario.getSalario(), is(BigDecimal.valueOf(1500.00)));
	}

	@Test
	public void nao_deve_aceitar_espacos_em_branco_no_cpf() {
		assertFalse(funcionario.getCpf().trim().isEmpty());
	}

	@Test
	public void nao_deve_aceitar_espacos_em_branco_no_nome() {
		assertFalse(funcionario.getNome().trim().isEmpty());
	}

	@Test
	public void nao_deve_aceitar_espacos_em_branco_no_telefone() {
		assertFalse(funcionarioComEndereco.getTelefone().trim().isEmpty());
	}

	@Test
	public void deve_retornar_true_no_hashCode_com_funcionarios_iguais() {
		Funcionario funcionario2 = new Funcionario("99074424880", "Gabriel Bueno", BigDecimal.valueOf(1500.00));
		assertEquals(funcionario.hashCode(), funcionario2.hashCode());
	}

	@Test
	public void deve_retornar_false_no_hashCode_com_um_funcionario_de_cpf_null() {
		Funcionario funcionario2 = new Funcionario(null, "Gabriel Bueno", BigDecimal.valueOf(1500.00));
		assertNotEquals(funcionario.hashCode(), funcionario2.hashCode());
	}

	@Test
	public void deve_retornar_true_no_equals_com_funcionarios_iguais() {
		Funcionario funcionario2 = new Funcionario("99074424880", "Gabriel Bueno", BigDecimal.valueOf(1500.00));
		assertTrue(funcionario.equals(funcionario2) & funcionario2.equals(funcionario));
	}

	@Test
	public void deve_retornar_false_no_equals_com_um_funcionario_de_cpf_null() {
		Funcionario funcionario2 = new Funcionario(null, "Gabriel Bueno", BigDecimal.valueOf(1500.00));
		assertFalse(funcionario.equals(funcionario2) & funcionario2.equals(funcionario));
	}

	@Test
	public void deve_retornar_true_no_equals_comparando_um_funcionario_com_ele_mesmo() {
		assertEquals(funcionario, funcionario);
	}

	@Test
	public void deve_retornar_false_no_equals_comparando_um_funcionarios_com_null() {
		assertNotEquals(funcionario, null);
	}

	@Test
	public void deve_retornar_true_no_equals_comparando_dois_funcionarios_de_cpf_null() {
		Funcionario funcionario1 = new Funcionario(null, "Gabriel Bueno", BigDecimal.valueOf(1500.00));
		Funcionario funcionario2 = new Funcionario(null, "Gabriel Bueno", BigDecimal.valueOf(1500.00));
		assertEquals(funcionario1, funcionario2);
	}

	@Test
	public void deve_retornar_false_no_equals_com_funcionarios_de_cpf_diferentes() {
		Funcionario funcionario1 = new Funcionario("99074424880", "Gabriel Bueno", BigDecimal.valueOf(1500.00));
		Funcionario funcionario2 = new Funcionario("87749387897", "Gabriel Bueno", BigDecimal.valueOf(1500.00));
		assertNotEquals(funcionario2, funcionario1);
	}

	@Test
	public void deve_retornar_false_no_equals_com_funcionario_e_um_numero_aleatorio() {
		assertNotEquals(funcionario, new Object());
	}
	
	@Test
	public void toString_deve_retornar_null() {
		funcionarioComEndereco = new Funcionario(null, null, 0, null, null, new BigDecimal("1"));
		assertThat(funcionarioComEndereco.toString(), containsString("salario"));
	}
	
	@Test
	public void toString_deve_retornar_preenchido() {
		assertThat(funcionarioComEndereco.toString(), containsString("salario"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setCpf_null() {
		funcionario.setCpf(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setCpf_vazio() {
		funcionario.setCpf(" ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setCpf_tamanho_menor() {
		funcionario.setCpf("1010101010");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setCpf_tamanho_maior() {
		funcionario.setCpf("121212121212");
	}
		
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setNome_null() {
		funcionario.setNome(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setNome_vazio() {
		funcionario.setNome(" ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setNome_tamanho_menor() {
		funcionario.setNome("a");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setNome_tamanho_maior() {
		funcionario.setNome("abcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabxc");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setTelefone_null() {
		funcionario.setTelefone(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setTelefone_vazio() {
		funcionario.setTelefone(" ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setTelefone_tamanho_menor() {
		funcionario.setTelefone("1234567");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setTelefone_tamanho_maior() {
		funcionario.setTelefone("1234567890");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setIdade() {
		funcionario.setIdade(3);
	}

	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setSalario_negativo() {
		funcionario.setSalario(BigDecimal.valueOf(-50.00));
	}
	
	@Test
	public void deve_testar_o_regex_do_nome() {
		funcionario.setNome("1234567890");
		assertFalse(isValid(funcionario, Constantes.NOME_INVALIDO));
	}
	
	@Test
	public void deve_testar_o_regex_do_telefone() {
		funcionario.setTelefone("abcabcabc");
		assertFalse(isValid(funcionario, Constantes.TELEFONE_INVALIDO));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_o_isCpf_invalido() {
		funcionario.setCpf("12345678912");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_o_isCpf_numeros_iguais() {
		funcionario.setCpf("11111111111");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_o_isCpf_tamanho_incorreto() {
		funcionario.setCpf("123");
	}

	@After
	public void tearDown() {
		cpf = null;
		nome = null;
		idade = 0;
		telefone = null;
		salario = BigDecimal.ZERO;
	}

	@AfterClass
	public static void tearDownAfterClass() {
		System.out.println("Finalizamos os testes na classe funcionario\n");
	}

}
