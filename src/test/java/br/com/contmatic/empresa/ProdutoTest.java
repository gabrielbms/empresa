package br.com.contmatic.empresa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ProdutoTest {
	
	private Integer id;
	
	private String nome;
	
	private Integer quantidade;
	
	private BigDecimal preço;
	
	private Produto produto;
	
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
	
	@Test
	public void deve_testar_se_o_nome_aceita_letras() {
		produto.setNome("Ryzen 5 2600");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_nome_aceita_null() {
		produto.setNome(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_nome_aceita_vazio() {
		produto.setNome("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_nome_aceita_espaco_em_branco() {
		produto.setNome("          ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_nome_aceita_caracteres_especiais() {
		produto.setNome("@#$");
	}
		
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_nome_aceita_espaco_no_inicio() {
		produto.setNome(" Ryzen 5 2600");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_nome_aceita_espaco_no_final() {
		produto.setNome("Ryzen 5 2600 ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_nome_aceita_muitos_espacos_entre_as_palavras() {
		produto.setNome("Ryzen 5        2600");
	}
	
	@Test
	public void deve_testar_se_o_nome_aceita_um_espaco_entre_as_palavras() {
		produto.setNome("Ryzen 5 2600");
	}
	
	@Test
	public void deve_testar_o_getNome() {
		produto.setNome("Gabriel Bueno");
		assertEquals(produto.getNome(), "Gabriel Bueno");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setNome_tamanho_menor() {
		produto.setNome("a");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setNome_tamanho_maior() {
		produto.setNome("abcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcaabcabcabcabcabcaabcabcabc"
				+ "abcabcaabcabcabcabcabcabcabcabcabcabcabxc");
	}
	
	
	@Test (expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_id_negativo() {
		produto.setId(-7);
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
	
	@Test (expected = IllegalArgumentException.class)
	public void deve_testar_o_toString_nullo() {
		produto = new Produto(0, null, 1, (BigDecimal.valueOf(1)));
		produto.toString();
	}
	
	@Test
	public void deve_retornar_true_no_hashCode_com_produtos_iguais() {
		Produto outroProduto = new Produto(id, nome, quantidade, preço);
		assertEquals(produto.hashCode(), outroProduto.hashCode());
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_retornar_false_no_hashCode_com_um_produto_de_id_null() {
		Produto outroProduto = new Produto(null, nome, quantidade, preço);
		assertNotEquals(produto.hashCode(), outroProduto.hashCode());
	}
	
	@Test
	public void deve_retornar_true_no_equals_com_produtos_iguais() {
		Produto outroProduto = new Produto(id, nome, quantidade, preço);
		assertEquals(produto, outroProduto);
	}
	
	@Test(expected = IllegalArgumentException.class)
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
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_retornar_true_no_equals_comparando_dois_produtos_de_id_null() {
		Produto produto1 = new Produto(null, nome, quantidade, preço);
		Produto produto2 = new Produto(null, nome, quantidade, preço);
		assertEquals(produto1, produto2);
	}

	@Test
	public void deve_retornar_false_no_equals_com_produtos_de_ids_diferentes() {
		Produto produto1 = new Produto(2, nome, quantidade, preço);
		Produto produto2 = new Produto(3, nome, quantidade, preço);
		assertNotEquals(produto1, produto2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_retornar_false_no_equals_com_um_id_null_e_outro_preenchido() {
		Produto produto1 = new Produto(null, nome, quantidade, preço);
		Produto produto2 = new Produto(3, nome, quantidade, preço);
		assertNotEquals(produto1, produto2);
	}

	@Test
	public void deve_retornar_false_no_equals_com_um_produto_e_um_objeto_aleatorio() {
		assertNotEquals(produto, new Object());
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
		System.out.println("Finalizamos os testes na classe funcionario\n");
	}

}