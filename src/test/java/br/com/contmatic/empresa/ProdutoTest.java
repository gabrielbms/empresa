package br.com.contmatic.empresa;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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
import org.junit.Test;

import br.com.contmatic.util.Constantes;

public class ProdutoTest {
	
	private Integer id;
	
	private String nome;
	
	private Integer quantidade;
	
	private BigDecimal preço;
	
	private static Produto produto;
	
	private Validator validator;

	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	
	@BeforeClass
	public static void setUpBeforeClass() {
		System.out.println("Iniciamos os testes na classe produto");
	}
	
	@Before
	public void setUp() {
		id = 1;
		nome = "Placa mãe";
		quantidade = 1;
		preço = BigDecimal.valueOf(500.00);
		produto = new Produto(id, nome, quantidade, preço);
	}
	
	public boolean isValid(Produto produto, String mensagem) {
		validator = factory.getValidator();
		boolean valido = true;
		Set<ConstraintViolation<Produto>> restricoes = validator.validate(produto);
		for (ConstraintViolation<Produto> constraintViolation : restricoes)
			if (constraintViolation.getMessage().equalsIgnoreCase(mensagem))
				valido = false;
		return valido;
	}
	
	@Test
	public void nao_deve_aceitar_id_nulo() {
		produto = new Produto(1, "placa mãe");
		assertNotNull(produto.getId());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_id_negativo() {
		produto.setId(-7);
	}
	
	@Test
	public void nao_deve_aceitar_nome_nulo() {
		assertNotNull(produto.getNome());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_vazio() {
		produto.setNome(" ");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_pequeno() {
		produto.setNome("a");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_grande() {
		produto.setNome("maisqueoitentamaisqueoitentamaisqueoitentamaisqueoitentamaisqueoitentamaisqueoitenta");
	}
	
	@Test
	public void nao_deve_aceitar_quantidade_nulo() {
		assertNotNull(produto.getQuantidade());
	}
	
	@Test
	public void nao_deve_aceitar_preço_nulo() {
		assertNotNull(produto.getPreço());
	}
	
	@Test
	public void deve_testar_o_getId_esta_funcionando_corretamente() {
		produto.setId(5);
		assertTrue(produto.getId().equals(5));
	}
	
	@Test
	public void deve_testar_o_getNome_esta_funcionando_corretamente() {
		produto.setNome("placa mãe");
		assertThat(produto.getNome(), containsString("placa mãe"));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void deve_testar_o_exception_do_setQuantidad() {
		produto.setQuantidade(-3);
	}
	
	@Test
	public void deve_testar_o_getPreço_esta_funcionando_corretamente() {
		produto.setPreço(BigDecimal.valueOf(500.00));
		assertEquals(produto.getPreço(), BigDecimal.valueOf(500.00));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void deve_testar_o_exception_do_setPreço() {
		produto.setPreço(BigDecimal.valueOf(-500.00));
	}
	
	@Test 
	public void deve_testar_o_toString_preenchido() {
		produto = new Produto(1, "Processador", 2, (BigDecimal.valueOf(900)));
		String produtoToString = produto.toString();
		assertEquals(produtoToString, produto.toString());
	}
	
	@Test 
	public void deve_testar_o_toString_nullo() {
		produto = new Produto(0, null, 1, (BigDecimal.valueOf(1)));
		produto.toString();
	}
	
	@Test
	public void deve_retornar_true_no_hashCode_com_produtos_iguais() {
		Produto outroProduto = new Produto(id, nome, quantidade, preço);
		assertEquals(produto.hashCode(), outroProduto.hashCode());
	}

	@Test
	public void deve_retornar_false_no_hashCode_com_um_produto_de_id_null() {
		Produto outroProduto = new Produto(null, nome, quantidade, preço);
		assertNotEquals(produto.hashCode(), outroProduto.hashCode());
	}
	
	@Test
	public void deve_retornar_true_no_equals_com_produtos_iguais() {
		Produto outroProduto = new Produto(id, nome, quantidade, preço);
		assertTrue(produto.equals(outroProduto) & outroProduto.equals(produto));
	}
	
	@Test
	public void deve_retornar_false_no_equals_com_um_produto_de_id_null() {
		Produto outroProduto = new Produto(null, nome, quantidade, preço);
		assertNotEquals(produto, outroProduto);
	}
	
	@Test
	public void deve_retornar_true_no_equals_comparando_um_produto_com_ele_mesmo() {
		assertEquals(produto, produto);
	}
	
	@Test
	public void deve_retornar_false_no_equals_comparando_um_produto_com_null() {
		assertNotEquals(produto, null);
	}
	
	@Test
	public void deve_retornar_true_no_equals_comparando_dois_produtos_de_id_null() {
		Produto produto1 = new Produto(null, nome, quantidade, preço);
		Produto produto2 = new Produto(null, nome, quantidade, preço);
		assertTrue(produto1.equals(produto2) && produto2.equals(produto1));
	}

	@Test
	public void deve_retornar_false_no_equals_com_produtos_de_ids_diferentes() {
		Produto produto1 = new Produto(2, nome, quantidade, preço);
		Produto produto2 = new Produto(3, nome, quantidade, preço);
		assertFalse(produto1.equals(produto2) && produto2.equals(produto1));
	}
	
	@Test
	public void deve_retornar_false_no_equals_com_um_id_null_e_outro_preenchido() {
		Produto produto1 = new Produto(null, nome, quantidade, preço);
		Produto produto2 = new Produto(3, nome, quantidade, preço);
		assertNotEquals(produto1, produto2);
		assertNotEquals(produto2, produto1);
	}


	@Test
	public void deve_retornar_false_no_equals_com_um_produto_e_um_objeto_aleatorio() {
		assertNotEquals(produto, new Object());
	}
	
	@Test
	public void deve_testar_o_regex_do_nome() {
		produto.setNome("1234567890");
		assertFalse(isValid(produto, Constantes.NOME_INVALIDO));
	}
	
	@After
	public void tearDown() {
		id = null;
		nome = null;
		quantidade = null;
		preço = null;
	}

	@AfterClass
	public static void tearDownAfterClass() {
		System.out.println(produto);
		System.out.println("Finalizamos os testes na classe funcionario\n");
	}

}
