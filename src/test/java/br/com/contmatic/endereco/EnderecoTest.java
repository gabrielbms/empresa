package br.com.contmatic.endereco;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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
import org.junit.runners.MethodSorters;

import br.com.contmatic.util.Constantes;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EnderecoTest {

	private String cep;

	private String rua;

	private Integer numero;

	private String complemento;

	private String bairro;

	private String cidade;

	private String estado;

	private static Endereco endereco;

	private static Endereco enderecoCompleto;
	
	private Validator validator;

	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

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
		estado = "SP";
		enderecoCompleto = new Endereco(cep, rua, numero, complemento, bairro, cidade, estado);
		endereco = new Endereco(cep, numero);
	}
	
	public boolean isValid(Endereco endereco, String mensagem) {
		validator = factory.getValidator();
		boolean valido = true;
		Set<ConstraintViolation<Endereco>> restricoes = validator.validate(endereco);
		for (ConstraintViolation<Endereco> constraintViolation : restricoes)
			if (constraintViolation.getMessage().equalsIgnoreCase(mensagem))
				valido = false;
		return valido;
	}

	@Test
	public void nao_deve_aceitar_cep_nulo() {
		assertNotNull(endereco.getCep());
	}

	@Test
	public void nao_deve_aceitar_numero_nulo() {
		assertNotNull(endereco.getNumero());
	}

	@Test
	public void nao_deve_aceitar_rua_nulo() {
		assertNotNull(enderecoCompleto.getRua());
	}

	@Test
	public void nao_deve_aceitar_complemento_nulo() {
		assertNotNull(enderecoCompleto.getComplemento());
	}

	@Test
	public void nao_deve_aceitar_bairro_nulo() {
		assertNotNull(enderecoCompleto.getBairro());
	}

	@Test
	public void nao_deve_aceitar_cidade_nulo() {
		assertNotNull(enderecoCompleto.getCidade());
	}

	@Test
	public void nao_deve_aceitar_estado_nulo() {
		assertNotNull(enderecoCompleto.getEstado());
	}

	@Test
	public void deve_testar_o_getCep_esta_funcionando_corretamente() {
		endereco.setCep("03806040");
		assertThat(endereco.getCep(), containsString("03806040"));
	}

	@Test
	public void deve_testar_o_getNumero_esta_funcionando_corretamente() {
		endereco.setNumero(777);
		assertTrue(endereco.getNumero() == 777);
	}

	@Test
	public void deve_testar_o_getRua_esta_funcionando_corretamente() {
		enderecoCompleto.setRua("Rua Joseph pequeno Joseph");
		assertThat(enderecoCompleto.getRua(), containsString("Rua Joseph pequeno Joseph"));
	}

	@Test
	public void deve_testar_o_getComplemento_esta_funcionando_corretamente() {
		enderecoCompleto.setComplemento("Sem complemento");
		assertThat(enderecoCompleto.getComplemento(), containsString("Sem complemento"));
	}

	@Test
	public void deve_testar_o_getBairro_esta_funcionando_corretamente() {
		enderecoCompleto.setBairro("Jardim Santo Eduardo");
		assertThat(enderecoCompleto.getBairro(), containsString("Jardim Santo Eduardo"));
	}

	@Test
	public void deve_testar_o_getCidade_esta_funcionando_corretamente() {
		enderecoCompleto.setCidade("São Paulo");
		assertThat(enderecoCompleto.getCidade(), containsString("São Paulo"));
	}

	@Test
	public void deve_testar_o_getEstado_esta_funcionando_corretamente() {
		enderecoCompleto.setEstado("SP");
		assertThat(enderecoCompleto.getEstado(), containsString("SP"));
	}

	@Test
	public void nao_deve_aceitar_espacos_em_branco_no_cep() {
		assertFalse(endereco.getCep().trim().isEmpty());
	}

	@Test
	public void nao_deve_aceitar_espacos_em_branco_na_rua() {
		assertFalse(enderecoCompleto.getRua().trim().isEmpty());
	}

	@Test
	public void nao_deve_aceitar_espacos_em_branco_no_completo() {
		assertFalse(enderecoCompleto.getComplemento().trim().isEmpty());
	}

	@Test
	public void nao_deve_aceitar_espacos_em_branco_no_bairro() {
		assertFalse(enderecoCompleto.getBairro().trim().isEmpty());
	}

	@Test
	public void nao_deve_aceitar_espacos_em_branco_no_cidade() {
		assertFalse(enderecoCompleto.getCidade().trim().isEmpty());
	}

	@Test
	public void nao_deve_aceitar_espacos_em_branco_no_estado() {
		assertFalse(enderecoCompleto.getEstado().trim().isEmpty());
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
		assertTrue(endereco.equals(endereco2) & endereco2.equals(endereco));
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_retornar_false_no_equals_com_um_endereco_de_cep_null() {
		Endereco endereco2 = new Endereco(null, rua, numero, complemento, bairro, cidade, estado);
		assertFalse(enderecoCompleto.equals(endereco2) & endereco2.equals(enderecoCompleto));
	}

	@Test
	public void deve_retornar_true_no_equals_comparando_um_endereco_com_ele_mesmo() {
		assertTrue(endereco.equals(endereco));
	}

	@Test
	public void deve_retornar_false_no_equals_comparando_um_endereco_com_null() {
		assertFalse(endereco.equals(null));
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_retornar_true_no_equals_comparando_dois_enderecos_de_cep_null() {
		Endereco endereco1 = new Endereco(null, rua, numero, complemento, bairro, cidade, estado);
		Endereco endereco2 = new Endereco(null, rua, numero, complemento, bairro, cidade, estado);
		assertTrue(endereco1.equals(endereco2));
	}

	@Test
	public void deve_retornar_false_no_equals_com_enderecos_de_ceps_diferentes() {
		Endereco endereco1 = new Endereco("03806040", 777);
		Endereco endereco2 = new Endereco("03806050", 767);
		assertFalse(endereco2.equals(endereco1));
	}

	@Test
	public void deve_retornar_false_no_equals_com_endereco_e_um_numero_aleatorio() {
		assertFalse(endereco.equals(new Object()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void toString_deve_retornar_null() {
		Endereco enderecoNull = new Endereco(null, null, 0, null, null, null, null);
		assertThat(enderecoNull.toString(), containsString(""));
	}

	@Test
	public void toString_deve_retornar_preenchido() {
		Endereco enderecoPreenchido = new Endereco("03806040", "Rua Joseph pequeno Joseph", 777,
				"Rua Joseph pequeno Joseph", "Jardim Santo Eduardo", "São Paulo", "SP");
		assertThat(enderecoPreenchido.toString(), containsString(""));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setCep_nullo() {
		endereco.setCep(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setCep_vazio() {
		endereco.setCep(" ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setCep_tamanho_menor() {
		endereco.setCep("1234567");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setCep_tamanho_maio() {
		endereco.setCep("123456789");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setNumero() {
		endereco.setNumero(0);
	}
	
	@Test
	public void deve_testar_o_regex_do_cep() {
		enderecoCompleto.setCep("abcdefav");
		assertFalse(isValid(enderecoCompleto, Constantes.CEP_INVALIDO));
	}
	
	@Test
	public void deve_testar_o_regex_da_rua() {
		enderecoCompleto.setRua("    #$%");
		assertFalse(isValid(enderecoCompleto, Constantes.RUA_INVALIDA));
	}

	
	@Test
	public void deve_testar_o_regex_do_complemento() {
		enderecoCompleto.setComplemento("    #$ ");
		assertFalse(isValid(enderecoCompleto, Constantes.COMPLEMENTO_INVALIDO));
	}
	
	@Test
	public void deve_testar_o_regex_do_bairro() {
		enderecoCompleto.setBairro("@#%$%%");
		assertFalse(isValid(enderecoCompleto, Constantes.BAIRRO_INVALIDO));
	}
	
	@Test
	public void deve_testar_o_regex_da_cidade() {
		enderecoCompleto.setCidade("123");
		assertFalse(isValid(enderecoCompleto, Constantes.CIDADE_INVALIDA));
	}
	
	@Test
	public void deve_testar_o_regex_do_estado() {
		enderecoCompleto.setEstado("4778");
		assertFalse(isValid(enderecoCompleto, Constantes.ESTADO_INVALIDO));
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
		System.out.println(endereco);
		System.out.println("Finalizamos os testes na classe endereco\n");
	}

}