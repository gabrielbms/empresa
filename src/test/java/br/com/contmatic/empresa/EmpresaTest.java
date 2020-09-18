package br.com.contmatic.empresa;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

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
public class EmpresaTest {

	private String cnpj;

	private String nome;

	private static Empresa empresa;
	
	private Telefone telefone;
	
	private Validator validator;

	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	
	Endereco endereco = new Endereco("03208070", 85);

	@BeforeClass
	public static void setUpBeforeClass() {
		System.out.println("Iniciamos os testes na classe empresa");
	}

	@Before
	public void setUp() {
		cnpj = "35667373000103";
		nome = "GB Conserto de computadores";
		telefone = new Telefone(TelefoneDDDType.DDD11, "978457845", TipoTelefoneType.CELULAR);
		Endereco endereco = new Endereco("03208070", 79);
		empresa = new Empresa(cnpj, nome, telefone, endereco);
	}
	
	public boolean isValid(Empresa empresa, String mensagem) {
		validator = factory.getValidator();
		boolean valido = true;
		Set<ConstraintViolation<Empresa>> restricoes = validator.validate(empresa);
		for (ConstraintViolation<Empresa> constraintViolation : restricoes)
			if (constraintViolation.getMessage().equalsIgnoreCase(mensagem))
				valido = false;
		return valido;
	}

	@Test
	public void nao_deve_aceitar_cnpj_nulo() {
		Empresa empresa = new Empresa("35667373000103");
		assertNotNull(empresa.getCnpj());
	}

	@Test
	public void nao_deve_aceitar_nome_nulo() {
		empresa.setNome("GB Conserto de computadores");
		assertNotNull(empresa.getNome());
	}

	@Test
	public void nao_deve_aceitar_telefone_nulo() {
		assertNotNull(empresa.getTelefone());
	}

	@Test
	public void nao_deve_aceitar_endereco_nulo() {
		assertNotNull(empresa.getEndereco());
	}

	@Test
	public void deve_testar_o_getCnpj_esta_funcionando_corretamente() {
		empresa.setCnpj("35667373000103");
		assertEquals(empresa.getCnpj(), "35667373000103");
	}

	@Test
	public void deve_testar_o_getNome_esta_funcionando_corretamente() {
		empresa.setNome("GB Conserto de computadores");
		assertEquals(empresa.getNome(), "GB Conserto de computadores");
	}

	@Test
	public void deve_testar_o_getTelefone_esta_funcionando_corretamente() {
		telefone.setNumero("41108521");
		assertEquals(empresa.getTelefone().getNumero(), "41108521");
	}

	@Test
	public void deve_testar_o_setEndereco_esta_funcionando_corretamente() {
		empresa.setEndereco(new Endereco("03208070", 79));
		assertThat(empresa.getEndereco().toString(), containsString("03208070"));
	}

	@Test
	public void nao_deve_aceitar_espacos_em_branco_no_cnpj() {
		assertFalse(empresa.getCnpj().trim().isEmpty());
	}

	@Test
	public void nao_deve_aceitar_espacos_em_branco_no_nome() {
		assertFalse(empresa.getNome().trim().isEmpty());
	}

	@Test
	public void nao_deve_aceitar_espacos_em_branco_no_telefone() {
		assertFalse(empresa.getTelefone().getNumero().trim().isEmpty());
	}

	@Test
	public void deve_retornar_true_no_hashCode_com_empresas_iguais() {
		Empresa Empresa2 = new Empresa("35667373000103", "GB Conserto de computadores", telefone, endereco);
		assertEquals(empresa.hashCode(), Empresa2.hashCode());
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_retornar_false_no_hashCode_com_uma_empresa_de_cnpj_null() {
		Empresa Empresa2 = new Empresa(null, "GB Conserto de computadores", telefone, endereco);
		assertNotEquals(empresa.hashCode(), Empresa2.hashCode());
	}

	@Test
	public void deve_retornar_true_no_equals_com_empresas_iguais() {
		Empresa empresa2 = new Empresa("35667373000103", "GB Conserto de computadores", telefone, endereco);
		assertEquals(empresa, empresa2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_retornar_false_no_equals_com_um_empresa_de_cnpj_null() {
		Empresa empresa2 = new Empresa(null, "GB Conserto de computadores", telefone, endereco);
		assertFalse(empresa.equals(empresa2) & empresa2.equals(empresa));
	}

	@Test
	public void deve_retornar_true_no_equals_comparando_uma_empresa_com_ela_mesmo() {
		assertEquals(empresa, empresa);
	}

	@Test
	public void deve_retornar_false_no_equals_comparando_uma_empresa_com_null() {
		assertNotEquals(empresa, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deve_retornar_true_no_equals_comparando_dois_empresas_de_cnpj_null() {
		Empresa empresa1 = new Empresa(null, "GB Conserto de computadores", telefone, endereco);
		Empresa empresa2 = new Empresa(null, "GB Conserto de computadores", telefone, endereco);
		assertEquals(empresa1, empresa2);
	}

	@Test
	public void deve_retornar_false_no_equals_com_empresas_de_cnpj_diferentes() {
		Empresa empresa1 = new Empresa("35667373000103", "GB Conserto de computadores", telefone, endereco);
		Empresa empresa2 = new Empresa("49695176000102", "GB Conserto de computadores", telefone, endereco);
		assertNotEquals(empresa2, empresa1);
	}

	@Test
	public void deve_retornar_false_no_equals_com_a_empresa_e_um_numero_aleatorio() {
		assertNotEquals(empresa, new Object());
	}

	@Test
	public void toString_deve_retornar_valores_preenchidos() {
		Empresa empresaPreenchida = new Empresa("35667373000103", "GB Conserto de computadores", telefone, endereco);
		String empresaPreenchidaToStringo = empresaPreenchida.toString();
		assertEquals(empresaPreenchida.toString(), empresaPreenchidaToStringo);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setCpf_null() {
		empresa.setCnpj(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setCpf_vazio() {
		empresa.setCnpj(" ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setCpf_tamanho_menor() {
		empresa.setCnpj("1313131313131");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setCpf_tamanho_maior() {
		empresa.setCnpj("151515151515151");
	}
		
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setNome_null() {
		empresa.setNome(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setNome_vazio() {
		empresa.setNome(" ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setNome_tamanho_menor() {
		empresa.setNome("a");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setNome_tamanho_maior() {
		empresa.setNome("abcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcaabcabcabcabcabcabcabcabcabcabcabbcabxc");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_o_regex_do_cnpj() {
		empresa.setCnpj("12345678912234");
		assertFalse(isValid(empresa, Constantes.CNPJ_INVALIDO));
	}
	
	@Test
	public void deve_testar_o_regex_do_nome() {
		empresa.setNome("1234567890");
		assertFalse(isValid(empresa, Constantes.NOME_INVALIDO));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_o_isCnpj_invalido() {
		empresa.setCnpj("12345678912234");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_o_isCnpj_numeros_iguais() {
		empresa.setCnpj("11111111111111");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_o_isCnpj_tamanho_incorreto() {
		empresa.setCnpj("123");
	}

	@After
	public void TearDown() {
		cnpj = null;
		nome = null;
		telefone = null;
	}

	@AfterClass
	public static void TearDownAfterClass() {
		System.out.println(empresa);
		System.out.println("Finalizamos os testes na classe empresa\n");
	}

}