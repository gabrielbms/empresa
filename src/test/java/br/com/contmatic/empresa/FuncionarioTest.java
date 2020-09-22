package br.com.contmatic.empresa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.telefone.TelefoneDDDType;
import br.com.contmatic.telefone.TipoTelefoneType;

@FixMethodOrder(NAME_ASCENDING)
public class FuncionarioTest {

	private String cpf;

	private String nome;

	private int idade;

	private Telefone telefone;

	private BigDecimal salario;

	private Funcionario funcionario;
	
	private Funcionario funcionarioCompleto;

	@BeforeClass
	public static void setUpBeforeClass() {
		System.out.println("Iniciamos os testes na classe funcionario");
	}

	@Before
	public void setUp() {
		cpf = "99074424880";
		nome = "Gabriel Bueno";
		idade = 25;
		salario = BigDecimal.valueOf(1500.00);
		telefone = new Telefone(TelefoneDDDType.DDD11, "978457845", TipoTelefoneType.CELULAR);
		Endereco endereco = new Endereco("04508010", 274);
		funcionario = new Funcionario(cpf, nome, salario);
		funcionarioCompleto = new Funcionario(cpf, nome, idade, telefone, endereco, salario);
	}

	@Test
	public void deve_testar_se_o_cpf_aceita_numeros() {
		funcionario.setCpf("43701888817");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_cpf_aceita_null() {
		funcionario.setCpf(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_cpf_aceita_vazio() {
		funcionario.setCpf("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_cpf_aceita_espaco_em_branco() {
		funcionario.setCpf("  ");
	}
	
	@Test(expected = IllegalStateException.class)
	public void deve_testar_se_o_cpf_aceita_letras() {
		funcionario.setCpf("abcdefabcde");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_cpf_aceita_caracteres_especiais() {
		funcionario.setCpf("@#$");
	}
		
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_cpf_aceita_espaco_no_inicio() {
		funcionario.setCpf(" 43701888817");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_cpf_aceita_espaco_no_final() {
		funcionario.setCpf("43701888817 ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_cpf_aceita_muitos_espacos_entre_os_numeros() {
		funcionario.setCpf("437018      88817");
	}
	
	@Test
	public void deve_testar_o_getCpf() {
		funcionario.setCpf("43701888817");
		assertEquals(funcionario.getCpf(), "43701888817");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setCpf_tamanho_menor() {
		funcionario.setCpf("1010101010");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setCpf_tamanho_maior() {
		funcionario.setCpf("121212121212");
	}
	
	@Test(expected = IllegalStateException.class)
	public void deve_testar_exception_a_validação_do_cpf() {
		funcionario.setCpf("43701888818");
	}

	@Test
	public void deve_testar_se_o_nome_aceita_letras() {
		funcionario.setNome("Gabriel");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_nome_aceita_null() {
		funcionario.setNome(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_nome_aceita_vazio() {
		funcionario.setNome("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_nome_aceita_espaco_em_branco() {
		funcionario.setNome("          ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_nome_aceita_numeros() {
		funcionario.setNome("123456");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_nome_aceita_caracteres_especiais() {
		funcionario.setNome("@#$");
	}
		
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_nome_aceita_espaco_no_inicio() {
		funcionario.setNome(" Gabriel");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_nome_aceita_espaco_no_final() {
		funcionario.setNome("Gabriel ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_nome_aceita_muitos_espacos_entre_as_palavras() {
		funcionario.setNome("Gabriel         Bueno");
	}
	
	@Test
	public void deve_testar_se_o_nome_aceita_um_espaco_entre_as_palavras() {
		funcionario.setNome("Gabriel Bueno");
	}
	
	@Test
	public void deve_testar_o_getNome() {
		funcionario.setNome("Gabriel Bueno");
		assertEquals(funcionario.getNome(), "Gabriel Bueno");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setNome_tamanho_menor() {
		funcionario.setNome("a");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setNome_tamanho_maior() {
		funcionario.setNome("abcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcaabcabcabcabcabcaabcabcabc"
				+ "abcabcaabcabcabcabcabcabcabcabcabcabcabxc");
	}
	
	@Test
	public void deve_testar_o_getIdade() {
		funcionario.getIdade();
	}
	
	@Test
	public void deve_testar_o_getTelefone() {
		funcionario.getTelefone();
	}
	
	@Test
	public void deve_testar_o_getEndereco() {
		funcionario.getEndereco();
	}
	
	@Test
	public void deve_testar_o_getSalario() {
		funcionario.getSalario();
	}
	
	@Test
	public void deve_retornar_true_no_hashCode_com_funcionarios_iguais() {
		Funcionario funcionario2 = new Funcionario("99074424880", "Gabriel Bueno", BigDecimal.valueOf(1500.00));
		assertEquals(funcionario.hashCode(), funcionario2.hashCode());
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_retornar_false_no_hashCode_com_um_funcionario_de_cpf_null() {
		Funcionario funcionario2 = new Funcionario(null, "Gabriel Bueno", BigDecimal.valueOf(1500.00));
		assertNotEquals(funcionario.hashCode(), funcionario2.hashCode());
	}

	@Test
	public void deve_retornar_true_no_equals_com_funcionarios_iguais() {
		Funcionario funcionario2 = new Funcionario("99074424880", "Gabriel Bueno", BigDecimal.valueOf(1500.00));
		assertTrue(funcionario.equals(funcionario2) & funcionario2.equals(funcionario));
	}

	@Test(expected = IllegalArgumentException.class)
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

	@Test(expected = IllegalArgumentException.class)
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
	
	@Test(expected = IllegalArgumentException.class)
	public void toString_deve_retornar_null() {
		funcionarioCompleto = new Funcionario(null, null, 0, null, null, new BigDecimal("1"));
		String funcionarioCompletoToString = funcionarioCompleto.toString();
		assertEquals(funcionarioCompleto.toString(), funcionarioCompletoToString);
	}
	
	@Test
	public void toString_deve_retornar_preenchido() {
		String funcionarioCompletoToString = funcionarioCompleto.toString();
		assertEquals(funcionarioCompleto.toString(), funcionarioCompletoToString);
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
