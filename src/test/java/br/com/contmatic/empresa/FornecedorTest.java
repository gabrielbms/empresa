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
import java.util.HashSet;
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
import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.telefone.TelefoneDDDType;
import br.com.contmatic.telefone.TipoTelefoneType;
import br.com.contmatic.util.Constantes;;

@FixMethodOrder(NAME_ASCENDING)
public class FornecedorTest {

	private String cnpj;

	private String nome;

	private Telefone telefone;

	private static Fornecedor fornecedor;
	
	private static Produto produto;
	
	private Validator validator;

	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

	@BeforeClass
	public static void setUpBeforeClass() {
		System.out.println("Iniciamos os testes na classe fornecedor");
	}

	@Before
	public void setUpProduto() {
		produto = new Produto(1, "placa mãe", 1, BigDecimal.valueOf(500.00));
	}
	
	@Before
	public void setUp() {
		cnpj = "97904702000131";
		nome = "CA peças LTDA";
		Set<Produto> produtos = new HashSet<>();
		produtos.add(produto);
		telefone = new Telefone(TelefoneDDDType.DDD11, "978457845", TipoTelefoneType.CELULAR);
		Endereco endereco = new Endereco("02708010", 21);
		fornecedor = new Fornecedor(cnpj, nome, telefone, produtos, endereco);
	}
	
	public boolean isValid(Fornecedor fornecedor, String mensagem) {
		validator = factory.getValidator();
		boolean valido = true;
		Set<ConstraintViolation<Fornecedor>> restricoes = validator.validate(fornecedor);
		for (ConstraintViolation<Fornecedor> constraintViolation : restricoes)
			if (constraintViolation.getMessage().equalsIgnoreCase(mensagem))
				valido = false;
		return valido;
	}

	@Test
	public void nao_deve_aceitar_cnpj_nulo() {
		assertNotNull(fornecedor.getCnpj());
	}

	@Test
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
		fornecedor.setNome("CA peças LTDA");
		assertThat(fornecedor.getNome(), containsString("CA peças LTDA"));
	}

	@Test
	public void deve_testar_o_getTelefone_esta_funcionando_corretamente() {
		telefone.setNumero("25871235");
		assertThat(fornecedor.getTelefone().getNumero(), containsString("25871235"));
	}

	@Test
	public void deve_testar_o_getEndereco_esta_funcionando_corretamente() {
		fornecedor.setEndereco(new Endereco("02708010", 21));
		assertThat(fornecedor.getEndereco(), is(new Endereco("02708010", 21)));
	}

	@Test
	public void deve_testar_o_getProduto_esta_funcionando_corretamente() {
		produto = new Produto(1, "Processador Ryzen 5 2600");
		assertThat(produto.getNome(), is("Processador Ryzen 5 2600"));
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
		assertFalse(fornecedor.getTelefone().getNumero().trim().isEmpty());
	}

	@Test
	public void deve_retornar_true_no_hashCode_com_fornecedor_iguais() {
		Fornecedor fornecedor2 = new Fornecedor("97904702000131", "CA peças LTDA");
		assertEquals(fornecedor.hashCode(), fornecedor2.hashCode());
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_retornar_false_no_hashCode_com_uma_fornecedor_de_cnpj_null() {
		Fornecedor fornecedor2 = new Fornecedor(null, "CA peças LTDA");
		assertNotEquals(fornecedor.hashCode(), fornecedor2.hashCode());
	}

	@Test
	public void deve_retornar_true_no_equals_com_fornecedores_iguais() {
		Fornecedor fornecedor2 = new Fornecedor("97904702000131", "CA peças LTDA");
		assertTrue(fornecedor.equals(fornecedor2) & fornecedor2.equals(fornecedor));
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_retornar_false_no_equals_com_um_fornecedor_de_cnpj_null() {
		Fornecedor fornecedor2 = new Fornecedor(null, "CA peças LTDA");
		assertFalse(fornecedor.equals(fornecedor2) & fornecedor2.equals(fornecedor));
	}

	@Test
	public void deve_retornar_true_no_equals_comparando_um_fornecedor_com_ela_mesmo() {
		assertEquals(fornecedor, fornecedor);
	}

	@Test
	public void deve_retornar_false_no_equals_comparando_um_fornecedor_com_null() {
		assertNotEquals(fornecedor, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_retornar_true_no_equals_comparando_dois_fornecedores_de_cnpj_null() {
		Fornecedor fornecedor1 = new Fornecedor(null, "CA peças LTDA");
		Fornecedor fornecedor2 = new Fornecedor(null, "CA peças LTDA");
		assertEquals(fornecedor1, fornecedor2);
	}

	@Test
	public void deve_retornar_false_no_equals_com_fornecedores_de_cnpj_diferentes() {
		Fornecedor fornecedor1 = new Fornecedor("97904702000131", "CA peças LTDA");
		Fornecedor fornecedor2 = new Fornecedor("43202648000153", "CA peças LTDA");
		assertNotEquals(fornecedor2, fornecedor1);
	}

	@Test
	public void deve_retornar_false_no_equals_com_a_fornecedor_e_um_numero_aleatorio() {
		assertNotEquals(fornecedor, new Object());
	}
	
	@Test
	public void toString_deve_retornar_valores_preenchidos() {
		Fornecedor fornecedorPreenchido = new Fornecedor("97904702000131", "CA peças LTDA");
		assertThat(fornecedorPreenchido.toString(), containsString(""));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setCpf_null() {
		fornecedor.setCnpj(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setCpf_vazio() {
		fornecedor.setCnpj(" ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setCpf_tamanho_menor() {
		fornecedor.setCnpj("1313131313131");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setCpf_tamanho_maior() {
		fornecedor.setCnpj("151515151515151");
	}
		
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setNome_null() {
		fornecedor.setNome(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setNome_vazio() {
		fornecedor.setNome(" ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setNome_tamanho_menor() {
		fornecedor.setNome("a");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setNome_tamanho_maior() {
		fornecedor.setNome("abcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcaabcabcabcabcabcabcabcabcabcabcabbcabxc");
	}
	
	@Test
	public void deve_testar_o_regex_do_nome() {
		fornecedor.setNome("1234567890");
		assertFalse(isValid(fornecedor, Constantes.NOME_INVALIDO));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_o_isCnpj_invalido() {
		fornecedor.setCnpj("12345678912234");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_o_isCnpj_numeros_iguais() {
		fornecedor.setCnpj("11111111111111");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_o_isCnpj_tamanho_incorreto() {
		fornecedor.setCnpj("123");
	}

	@After
	public void tearDown() {
		cnpj = null;
		nome = null;
		telefone = null;
	}

	@AfterClass
	public static void tearDownAfterClass() {
		System.out.println(fornecedor);
		System.out.println("Finalizamos os testes na classe funcionario\n");
	}

}