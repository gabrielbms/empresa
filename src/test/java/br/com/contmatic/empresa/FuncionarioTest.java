package br.com.contmatic.empresa;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.endereco.Endereco;

@FixMethodOrder(NAME_ASCENDING)
public class FuncionarioTest {

	private String cpf;

	private String nome;

	private int idade;

	private String telefone;

	private BigDecimal salario;

	private static Funcionario funcionario;

	private static Funcionario funcionarioCompleto;

	@BeforeClass
	public static void setUpBeforeClass() {
		System.out.println("Iniciamos os testes na classe funcionario");
	}

	@Before
	public void setUp() {
		cpf = "43701888817";
		nome = "Gabriel Bueno";
		idade = 25;
		telefone = "41108521";
		salario = BigDecimal.valueOf(1500.00);
		Endereco endereco = new Endereco("04508010", 274);
		funcionario = new Funcionario(cpf, nome, salario);
		funcionarioCompleto = new Funcionario(cpf, nome, idade, telefone, endereco, salario);
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
	public void nao_deve_aceitar_idade_nulo() {
		assertNotNull(funcionario.getIdade());
	}

	@Test
	public void nao_deve_aceitar_telefone_nulo() {
		assertNotNull(funcionarioCompleto.getTelefone());
	}

	@Test
	public void nao_deve_aceitar_endereco_nulo() {
		assertNotNull(funcionarioCompleto.getEndereco());
	}

	@Test
	public void nao_deve_aceitar_salario_nulo() {
		assertNotNull(funcionario.getSalario());
	}

	@Test
	public void deve_testar_o_getCpf_esta_funcionando_corretamente() {
		funcionario.setCpf("43701888818");
		assertThat(funcionario.getCpf(), containsString("43701888818"));
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
		assertTrue(funcionario.getEndereco().equals(new Endereco("04508010", 274)));
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
		assertFalse(funcionarioCompleto.getTelefone().trim().isEmpty());
	}

	@Test
	public void deve_retornar_true_no_hashCode_com_funcionarios_iguais() {
		Funcionario funcionario2 = new Funcionario("43701888817", "Gabriel Bueno", BigDecimal.valueOf(1500.00));
		assertTrue(funcionario.hashCode() == funcionario2.hashCode());
	}

	@Test
	public void deve_retornar_false_no_hashCode_com_um_cliente_de_cpf_null() {
		Funcionario funcionario2 = new Funcionario(null, "Gabriel Bueno", BigDecimal.valueOf(1500.00));
		assertFalse(funcionario.hashCode() == funcionario2.hashCode());
	}

	@Test
	public void deve_retornar_true_no_equals_com_funcionarios_iguais() {
		Funcionario funcionario2 = new Funcionario("43701888817", "Gabriel Bueno", BigDecimal.valueOf(1500.00));
		assertTrue(funcionario.equals(funcionario2) & funcionario2.equals(funcionario));
	}

	@Test
	public void deve_retornar_false_no_equals_com_um_funcionario_de_cpf_null() {
		Funcionario funcionario2 = new Funcionario(null, "Gabriel Bueno", BigDecimal.valueOf(1500.00));
		assertFalse(funcionario.equals(funcionario2) & funcionario2.equals(funcionario));
	}

	@Test
	public void deve_retornar_true_no_equals_comparando_um_funcionario_com_ele_mesmo() {
		assertTrue(funcionario.equals(funcionario));
	}

	@Test
	public void deve_retornar_false_no_equals_comparando_um_funcionarios_com_null() {
		assertFalse(funcionario.equals(null));
	}

	@Test
	public void deve_retornar_true_no_equals_comparando_dois_funcionarios_de_cpf_null() {
		Funcionario funcionario1 = new Funcionario(null, "Gabriel Bueno", BigDecimal.valueOf(1500.00));
		Funcionario funcionario2 = new Funcionario(null, "Gabriel Bueno", BigDecimal.valueOf(1500.00));
		assertTrue(funcionario1.equals(funcionario2));
	}

	@Test
	public void deve_retornar_false_no_equals_com_funcionarios_de_cpf_diferentes() {
		Funcionario funcionario1 = new Funcionario("43701888817", "Gabriel Bueno", BigDecimal.valueOf(1500.00));
		Funcionario funcionario2 = new Funcionario("43701888818", "Gabriel Bueno", BigDecimal.valueOf(1500.00));
		assertFalse(funcionario2.equals(funcionario1));
	}

	@Test
	public void deve_retornar_false_no_equals_com_funcionario_e_um_numero_aleatorio() {
		assertFalse(funcionario.equals(new Object()));
	}

	@Test
	public void toString_deve_retornar_null() {
		Funcionario funcionarioNull = new Funcionario(null, null, 0, null, null, new BigDecimal("0"));
		assertThat(funcionarioNull.toString(), containsString("salario"));
	}

	@Test
	public void toString_deve_retornar_nulla() {
		Funcionario funcionarioNull = new Funcionario("43701888817", "Gabriel Bueno", 25, "41108521",
				(new Endereco("04508010", 274)), new BigDecimal("0"));
		assertThat(funcionarioNull.toString(), containsString("salario"));
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
		System.out.println(funcionario);
		System.out.println("Finalizamos os testes na classe funcionario\n");
	}

}
