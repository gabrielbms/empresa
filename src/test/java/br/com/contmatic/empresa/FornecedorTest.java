package br.com.contmatic.empresa;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import br.com.contmatic.empresa.Fornecedor;
import br.com.contmatic.endereco.Endereco;;

@FixMethodOrder(NAME_ASCENDING)
public class FornecedorTest {

	private String cnpj;

	private String nome;

	private String telefone;

	private String produto;

	private static Fornecedor fornecedor;

	@BeforeClass
	public static void setUpBeforeClass() {
		System.out.println("Iniciamos os testes na classe fornecedor");
	}

	@Before
	public void setUp() {
		cnpj = "97904702000131";
		nome = "CA peças LTDA";
		telefone = "25871235";
		Endereco endereco = new Endereco("02708010", 21);
		produto = "5 placas mães";
		fornecedor = new Fornecedor(cnpj, nome, telefone, produto, endereco);
	}

	@Test
	public void nao_deve_aceitar_cnpj_nulo() {
		assertNotNull(fornecedor.getCnpj());
	}

	@Test //
	public void nao_deve_aceitar_nome_nulo() {
		fornecedor.setNome("CA peças LTDA");
		assertNotNull(fornecedor.getNome());
	}

	@Test
	public void nao_deve_aceitar_telefone_nulo() {
		assertNotNull(fornecedor.getTelefone());
	}

	@Test
	public void nao_deve_aceitar_endereco_nulo() {
		assertNotNull(fornecedor.getEndereco());
	}

	@Test
	public void nao_deve_aceitar_produto_nulo() {
		assertNotNull(fornecedor.getProduto());
	}

	@Test
	public void deve_testar_o_getCpf_esta_funcionando_corretamente() {
		fornecedor.setCnpj("97904702000131");
		assertThat(fornecedor.getCnpj(), containsString("97904702000131"));
	}

	@Test
	public void deve_testar_o_getNome_esta_funcionando_corretamente() {
		fornecedor.setCnpj("CA peças LTDA");
		assertThat(fornecedor.getNome(), containsString("CA peças LTDA"));
	}

	@Test
	public void deve_testar_o_getTelefone_esta_funcionando_corretamente() {
		fornecedor.setTelefone("25871235");
		assertThat(fornecedor.getTelefone(), containsString("25871235"));
	}

	@Test
	public void deve_testar_o_getEndereco_esta_funcionando_corretamente() {
		fornecedor.setEndereco(new Endereco("02708010", 21));
		assertThat(fornecedor.getEndereco(), is(new Endereco("02708010", 21)));
	}

	@Test
	public void deve_testar_o_getProduto_esta_funcionando_corretamente() {
		fornecedor.setProduto("5 placas mães");
		assertThat(fornecedor.getProduto(), is("5 placas mães"));
	}

	@Test
	public void nao_deve_aceitar_espacos_em_branco_no_cnpj() {
		assertFalse(fornecedor.getCnpj().trim().isEmpty());
	}

	@Test
	public void nao_deve_aceitar_espacos_em_branco_no_nome() {
		assertFalse(fornecedor.getNome().trim().isEmpty());
	}

	@Test
	public void nao_deve_aceitar_espacos_em_branco_no_telefone() {
		assertFalse(fornecedor.getTelefone().trim().isEmpty());
	}

	@Test
	public void nao_deve_aceitar_espacos_em_branco_no_produto() {
		assertFalse(fornecedor.getProduto().trim().isEmpty());
	}

	@Test
	public void deve_retornar_true_no_hashCode_com_fornecedor_iguais() {
		Fornecedor fornecedor2 = new Fornecedor("97904702000131", "CA peças LTDA");
		assertTrue(fornecedor.hashCode() == fornecedor2.hashCode());
	}

	@Test
	public void deve_retornar_false_no_hashCode_com_uma_fornecedor_de_cnpj_null() {
		Fornecedor fornecedor2 = new Fornecedor(null, "CA peças LTDA");
		assertFalse(fornecedor.hashCode() == fornecedor2.hashCode());
	}

	@Test
	public void deve_retornar_true_no_equals_com_fornecedores_iguais() {
		Fornecedor fornecedor2 = new Fornecedor("97904702000131", "CA peças LTDA");
		assertTrue(fornecedor.equals(fornecedor2) & fornecedor2.equals(fornecedor));
	}

	@Test
	public void deve_retornar_false_no_equals_com_um_fornecedor_de_cnpj_null() {
		Fornecedor fornecedor2 = new Fornecedor(null, "CA peças LTDA");
		assertFalse(fornecedor.equals(fornecedor2) & fornecedor2.equals(fornecedor));
	}

	@Test
	public void deve_retornar_true_no_equals_comparando_um_fornecedor_com_ela_mesmo() {
		assertTrue(fornecedor.equals(fornecedor));
	}

	@Test
	public void deve_retornar_false_no_equals_comparando_um_fornecedor_com_null() {
		assertFalse(fornecedor.equals(null));
	}

	@Test
	public void deve_retornar_true_no_equals_comparando_dois_fornecedores_de_cnpj_null() {
		Fornecedor fornecedor1 = new Fornecedor(null, "CA peças LTDA");
		Fornecedor fornecedor2 = new Fornecedor(null, "CA peças LTDA");
		assertTrue(fornecedor1.equals(fornecedor2));
	}

	@Test
	public void deve_retornar_false_no_equals_com_fornecedores_de_cnpj_diferentes() {
		Fornecedor fornecedor1 = new Fornecedor("97904702000131", "CA peças LTDA");
		Fornecedor fornecedor2 = new Fornecedor("97904702000132", "CA peças LTDA");
		assertFalse(fornecedor2.equals(fornecedor1));
	}

	@Test
	public void deve_retornar_false_no_equals_com_a_fornecedor_e_um_numero_aleatorio() {
		assertFalse(fornecedor.equals(new Object()));
	}

	@Test
	public void toString_deve_retornar_null() {
		Fornecedor fornecedorNull = new Fornecedor(null, null);
		assertThat(fornecedorNull.toString(), containsString(""));
	}

	@Test
	public void toString_deve_retornar_valores_preenchidos() {
		Fornecedor fornecedorPreenchido = new Fornecedor("97904702000131", "CA peças LTDA");
		assertThat(fornecedorPreenchido.toString(), containsString(""));
	}

	@After
	public void tearDown() {
		cnpj = null;
		nome = null;
		telefone = null;
		produto = null;
	}

	@AfterClass
	public static void tearDownAfterClass() {
		System.out.println(fornecedor);
		System.out.println("Finalizamos os testes na classe funcionario\n");
	}

}