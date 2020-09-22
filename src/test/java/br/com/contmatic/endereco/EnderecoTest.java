package br.com.contmatic.endereco;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EnderecoTest {

	private String cep;

	private String rua;

	private Integer numero;

	private String complemento;

	private String bairro;

	private String cidade;

	private Estado estado;

	private Endereco endereco;

	private Endereco enderecoCompleto;

	@BeforeClass
	public static void InicioDosTestes() {
		System.out.println("Iniciamos os testes na classe endereco");
	}

	@Before
	public void setUp() {
		cep = "03806040";
		rua = "Rua Joseph pequeno Joseph";
		numero = 777;
		complemento = "Sem complemento";
		bairro = "Jardim Santo Eduardo";
		cidade = "São Paulo";
		estado = Estado.SP;
		enderecoCompleto = new Endereco(cep, rua, numero, complemento, bairro, cidade, estado);
		endereco = new Endereco(cep, numero);
	}

	@Test
	public void deve_testar_se_o_cep_aceita_numeros() {
		endereco.setCep("04517020");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_cep_aceita_null() {
		endereco.setCep(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_cep_aceita_vazio() {
		endereco.setCep("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_cep_aceita_espaco_em_branco() {
		endereco.setCep("  ");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_cep_aceita_letras() {
		endereco.setCep("abcdefabcde");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_cep_aceita_caracteres_especiais() {
		endereco.setCep("@#$");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_cep_aceita_espaco_no_inicio() {
		endereco.setCep(" 04517020");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_cep_aceita_espaco_no_final() {
		endereco.setCep("04517020 ");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_cep_aceita_muitos_espacos_entre_os_numeros() {
		endereco.setCep("0451   7020");
	}

	@Test
	public void deve_testar_o_getCep() {
		endereco.setCep("04517020");
		assertEquals(endereco.getCep(), "04517020");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setCep_tamanho_menor() {
		endereco.setCep("666666");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setCep_tamanho_maior() {
		endereco.setCep("121212121212");
	}

	@Test
	public void deve_testar_se_o_rua_aceita_numeros() {
		endereco.setRua("04517020");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_rua_aceita_null() {
		endereco.setRua(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_rua_aceita_vazio() {
		endereco.setRua("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_rua_aceita_espaco_em_branco() {
		endereco.setRua("  ");
	}

	@Test
	public void deve_testar_se_o_rua_aceita_letras() {
		endereco.setRua("abcdefabcde");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_rua_aceita_caracteres_especiais() {
		endereco.setRua("@#$");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_rua_aceita_espaco_no_inicio() {
		endereco.setRua(" 04517020");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_rua_aceita_espaco_no_final() {
		endereco.setRua("04517020 ");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_rua_aceita_muitos_espacos_entre_os_numeros() {
		endereco.setRua("0451   7020");
	}

	@Test
	public void deve_testar_o_getRua() {
		endereco.setRua("Jose Josue");
		assertEquals(endereco.getRua(), "Jose Josue");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setRua_tamanho_menor() {
		endereco.setRua("z");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setRua_tamanho_maior() {
		endereco.setRua(
				"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
	}

	@Test
	public void deve_testar_se_o_complemento_aceita_numeros() {
		enderecoCompleto.setComplemento("04517020");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_complemento_aceita_null() {
		endereco.setComplemento(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_complemento_aceita_vazio() {
		endereco.setComplemento("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_complemento_aceita_espaco_em_branco() {
		endereco.setComplemento("  ");
	}

	@Test
	public void deve_testar_se_o_complemento_aceita_letras() {
		enderecoCompleto.setComplemento("abcdefabcde");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_complemento_aceita_caracteres_especiais() {
		endereco.setComplemento("@#$");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_complemento_aceita_espaco_no_inicio() {
		endereco.setComplemento(" 04517020");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_complemento_aceita_espaco_no_final() {
		endereco.setComplemento("04517020 ");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_complemento_aceita_muitos_espacos_entre_os_numeros() {
		endereco.setComplemento("0451   7020");
	}

	@Test
	public void deve_testar_o_getComplemento() {
		enderecoCompleto.setComplemento("Jose Josue");
		assertEquals(enderecoCompleto.getComplemento(), "Jose Josue");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setComplemento_tamanho_menor() {
		endereco.setComplemento("z");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setComplemento_tamanho_maior() {
		endereco.setComplemento(
				"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
	}

	@Test
	public void deve_testar_se_o_bairro_aceita_numeros() {
		endereco.setBairro("04517020");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_bairro_aceita_null() {
		endereco.setBairro(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_bairro_aceita_vazio() {
		endereco.setBairro("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_bairro_aceita_espaco_em_branco() {
		endereco.setBairro("  ");
	}

	@Test
	public void deve_testar_se_o_bairro_aceita_letras() {
		enderecoCompleto.setBairro("abcdefabcde");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_bairro_aceita_caracteres_especiais() {
		endereco.setBairro("@#$");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_bairro_aceita_espaco_no_inicio() {
		endereco.setBairro(" 04517020");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_bairro_aceita_espaco_no_final() {
		endereco.setBairro("04517020 ");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_bairro_aceita_muitos_espacos_entre_os_numeros() {
		endereco.setBairro("0451   7020");
	}

	@Test
	public void deve_testar_o_getBairro() {
		enderecoCompleto.setBairro("Jose Josue");
		assertEquals(enderecoCompleto.getBairro(), "Jose Josue");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setBairro_tamanho_menor() {
		endereco.setBairro("z");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setBairro_tamanho_maior() {
		endereco.setBairro(
				"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_cidade_aceita_numeros() {
		enderecoCompleto.setCidade("04517020");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_cidade_aceita_null() {
		endereco.setCidade(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_cidade_aceita_vazio() {
		endereco.setCidade("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_cidade_aceita_espaco_em_branco() {
		endereco.setCidade("  ");
	}

	@Test
	public void deve_testar_se_o_cidade_aceita_letras() {
		enderecoCompleto.setCidade("abcdefabcde");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_cidade_aceita_caracteres_especiais() {
		endereco.setCidade("@#$");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_cidade_aceita_espaco_no_inicio() {
		endereco.setCidade(" 04517020");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_cidade_aceita_espaco_no_final() {
		endereco.setCidade("04517020 ");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_cidade_aceita_muitos_espacos_entre_os_numeros() {
		endereco.setCidade("0451   7020");
	}

	@Test
	public void deve_testar_o_getCidade() {
		enderecoCompleto.setCidade("Jose Josue");
		assertEquals(enderecoCompleto.getCidade(), "Jose Josue");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setCidade_tamanho_menor() {
		endereco.setCidade("z");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setCidade_tamanho_maior() {
		endereco.setCidade(
				"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_estado_aceita_null() {
		endereco.setEstado(null);
	}

	@Test
	public void deve_testar_o_getNumero() {
		endereco.getNumero();
	}

	@Test
	public void deve_testar_o_getEstado() {
		endereco.getEstado();
	}

	@Test
	public void deve_retornar_true_no_hashCode_com_enderecos_iguais() {
		Endereco endereco2 = new Endereco("03806040", 777);
		assertTrue(endereco.hashCode() == endereco2.hashCode());
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_retornar_false_no_hashCode_com_um_endereco_de_cep_null() {
		Endereco endereco2 = new Endereco(null, rua, numero, complemento, bairro, cidade, estado);
		assertFalse(endereco.hashCode() == endereco2.hashCode());
	}

	@Test
	public void deve_retornar_true_no_equals_com_enderecos_iguais() {
		Endereco endereco2 = new Endereco("03806040", 777);
		assertEquals(endereco, endereco2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_retornar_false_no_equals_com_um_endereco_de_cep_null() {
		Endereco endereco2 = new Endereco(null, rua, numero, complemento, bairro, cidade, estado);
		assertNotEquals(enderecoCompleto, endereco2);
	}

	@Test
	public void deve_retornar_true_no_equals_comparando_um_endereco_com_ele_mesmo() {
		assertEquals(endereco, endereco);
	}

	@Test
	public void deve_retornar_false_no_equals_comparando_um_endereco_com_null() {
		assertNotEquals(endereco, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_retornar_true_no_equals_comparando_dois_enderecos_de_cep_null() {
		Endereco endereco1 = new Endereco(null, rua, numero, complemento, bairro, cidade, estado);
		Endereco endereco2 = new Endereco(null, rua, numero, complemento, bairro, cidade, estado);
		assertEquals(endereco1, endereco2);
	}

	@Test
	public void deve_retornar_false_no_equals_com_enderecos_de_ceps_diferentes() {
		Endereco endereco1 = new Endereco("03806040", 777);
		Endereco endereco2 = new Endereco("03806050", 767);
		assertNotEquals(endereco1, endereco2);
	}

	@Test
	public void deve_retornar_false_no_equals_com_endereco_e_um_numero_aleatorio() {
		assertNotEquals(endereco, new Object());
	}

	@Test(expected = IllegalArgumentException.class)
	public void toString_deve_retornar_null() {
		Endereco enderecoNull = new Endereco(null, null, 0, null, null, null, null);
		String enderecoNullToString = enderecoNull.toString();
		assertEquals(enderecoNull.toString(), enderecoNullToString);
	}

	@Test
	public void toString_deve_retornar_preenchido() {
		Endereco enderecoPreenchido = new Endereco("03806040", "Rua Joseph pequeno Joseph", 777,
				"Rua Joseph pequeno Joseph", "Jardim Santo Eduardo", "São Paulo", estado);
		String enderecoPreenchidoToString = enderecoPreenchido.toString();
		assertEquals(enderecoPreenchido.toString(), enderecoPreenchidoToString);
	}

	@Ignore
	@After
	public void tearDown() {
		cep = null;
		rua = null;
		numero = null;
		complemento = null;
		bairro = null;
		cidade = null;
		estado = null;
	}

	@AfterClass
	public static void teste_no_toString() {
		System.out.println("Finalizamos os testes na classe endereco\n");
	}

}