package br.com.contmatic.telefone;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Random;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.contmatic.endereco.Endereco;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TelefoneTest {
	
	private TelefoneDDDType ddd;

	private String numero;

	private TipoTelefoneType tipoTelefone;
	
	private Telefone telefone;
	
	private Validator validator;

	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	
	@BeforeClass
	public static void InicioDosTestes() {
		System.out.println("Iniciamos os testes na classe telefone");
	}
	
	@Before
	public void setUp() {
		ddd = TelefoneDDDType.values()[new Random().nextInt(66)];
		numero = "945781245";
		tipoTelefone = validaTipoTelefone(numero);
		telefone = new Telefone(ddd, numero, tipoTelefone);
	}
	
	public TipoTelefoneType validaTipoTelefone(String numero) {
		if (numero.length() == 8) {
			return TipoTelefoneType.FIXO;
		}
		if (numero.length() == 9) {
			return TipoTelefoneType.CELULAR;
		}
		else {
			throw new IllegalArgumentException("O telefone foi preenchido incorretamente.");
		}
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
	public void nao_deve_aceitar_ddd_nulo() {
		assertNotNull(telefone.getDdd());
	}
	
	@Test
	public void nao_deve_aceitar_numero_nulo() {
		assertNotNull(telefone.getNumero());
	}
	
	@Test
	public void nao_deve_aceitar_tipoTelefone_nulo() {
		assertNotNull(telefone.getTipoTelefone());
	}
	
	@Test
	public void deve_testar_o_getNumero_esta_funcionando_corretamente() {
		telefone.setNumero("946756084");
		assertEquals(telefone.getNumero(), "946756084");
	}
	
	@Test
	public void nao_deve_aceitar_espacos_em_branco_no_numero() {
		assertFalse(telefone.getNumero().trim().isEmpty());
	}
	
	@Test
	public void deve_testar_se_o_valida_tipo_telefone_esta_funcionando() {
		telefone.setNumero("27219689");
		assertEquals(telefone.getTipoTelefone(), TipoTelefoneType.FIXO);
	}
	
	@Test
	public void deve_retornar_true_no_hashCode_com_telefones_iguais() {
		Telefone telefone1 = new Telefone(TelefoneDDDType.DDD11, "978457845", TipoTelefoneType.CELULAR);
		Telefone telefone2 = new Telefone(TelefoneDDDType.DDD11, "978457845", TipoTelefoneType.CELULAR);
		assertTrue(telefone1.hashCode() == telefone2.hashCode());
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_retornar_false_no_hashCode_com_um_telefone_de_numero_null() {
		Telefone outroTelefone = new Telefone(TelefoneDDDType.DDD11, null, TipoTelefoneType.CELULAR);
		assertFalse(telefone.hashCode() == outroTelefone.hashCode());
	}

	@Test
	public void deve_retornar_true_no_equals_com_telefones_iguais() {
		Telefone telefone1 = new Telefone(TelefoneDDDType.DDD11, "978457845", TipoTelefoneType.CELULAR);
		Telefone telefone2 = new Telefone(TelefoneDDDType.DDD11, "978457845", TipoTelefoneType.CELULAR);
		assertEquals(telefone1, telefone2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_retornar_false_no_equals_com_um_telefone_de_numero_null() {
		Telefone outroTelefone = new Telefone(TelefoneDDDType.DDD11, null, TipoTelefoneType.CELULAR);
		assertNotEquals(telefone, outroTelefone);
	}

	@Test
	public void deve_retornar_true_no_equals_comparando_um_telefone_com_ele_mesmo() {
		assertEquals(telefone, telefone);
	}

	@Test
	public void deve_retornar_false_no_equals_comparando_um_telefone_com_null() {
		assertNotEquals(telefone, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_retornar_true_no_equals_comparando_dois_telefone_de_numero_null() {
		Telefone telefone1 = new Telefone(TelefoneDDDType.DDD11, null, TipoTelefoneType.CELULAR);
		Telefone telefone2 = new Telefone(TelefoneDDDType.DDD11, null, TipoTelefoneType.CELULAR);
		assertEquals(telefone1, telefone2);
	}

	@Test
	public void deve_retornar_false_no_equals_com_telefone_de_numeros_diferentes() {
		Telefone outroTelefone = new Telefone(TelefoneDDDType.DDD11, "978457857", TipoTelefoneType.CELULAR);
		assertNotEquals(telefone, outroTelefone);
	}

	@Test
	public void deve_retornar_false_no_equals_com_telefone_e_um_objeto_aleatorio() {
		assertNotEquals(telefone, new Object());
	}

	@Test(expected = IllegalArgumentException.class)
	public void toString_deve_retornar_null() {
		Telefone telefoneNull = new Telefone(null, null, null);
		assertThat(telefoneNull.toString(), containsString(""));
	}

	@Test
	public void toString_deve_retornar_preenchido() {
		String telefoneToString = telefone.toString();
		assertEquals(telefoneToString, telefone.toString());
	}
	
	@Test
	public void deve_testar_o_getDdd_do_TelefoneDDDType () {
		TelefoneDDDType telefoneDDD = TelefoneDDDType.DDD11;
		assertEquals(telefoneDDD.getDdd(), 11);
		
	}
	
	@Test
	public void deve_testar_o_getComplemento_do_TelefoneDDDType () {
		TelefoneDDDType telefoneDddComplemento = TelefoneDDDType.DDD11;
		assertEquals(telefoneDddComplemento.getComplemento(), "São Paulo – SP");
	}
	
	@Test
	public void deve_testar_o_getDescricao_do_TipoTelefoneType () {
		TipoTelefoneType telefoneDescricao = TipoTelefoneType.CELULAR;
		assertEquals(telefoneDescricao.getDescricao(), "Celular");
		
	}
	
	@Test
	public void deve_testar_o_getTamanho_do_TipoTelefoneType () {
		TipoTelefoneType telefoneDescricao = TipoTelefoneType.CELULAR;
		assertEquals(telefoneDescricao.getTamanho(), 9);
	}
	
}
