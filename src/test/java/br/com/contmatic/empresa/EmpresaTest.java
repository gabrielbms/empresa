package br.com.contmatic.empresa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.telefone.TelefoneDDDType;
import br.com.contmatic.telefone.TipoTelefoneType;;

@FixMethodOrder(NAME_ASCENDING)
public class EmpresaTest {

	private String cnpj;

	private String nome;

	private Empresa empresa;
	
	private Telefone telefone;
	
	private Endereco endereco;

	@BeforeClass
	public static void setUpBeforeClass() {
		System.out.println("Iniciamos os testes na classe empresa");
	}

	@Before
	public void setUp() {
		cnpj = "35667373000103";
		nome = "GB Conserto de computadores";
		telefone = new Telefone(TelefoneDDDType.DDD11, "978457845", TipoTelefoneType.CELULAR);
		endereco = new Endereco("03208070", 79);
		empresa = new Empresa(cnpj, nome, telefone, endereco);
	}

	@Test
	public void deve_testar_se_o_cnpj_aceita_numeros() {
		empresa.setCnpj("35667373000103");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_cnpj_aceita_null() {
		empresa.setCnpj(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_cnpj_aceita_vazio() {
		empresa.setCnpj("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_cnpj_aceita_espaco_em_branco() {
		empresa.setCnpj("  ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_cnpj_aceita_letras() {
		empresa.setCnpj("abcdef");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_cnpj_aceita_caracteres_especiais() {
		empresa.setCnpj("@#$");
	}
		
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_cnpj_aceita_espaco_no_inicio() {
		empresa.setCnpj(" 35667373000103");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_cnpj_aceita_espaco_no_final() {
		empresa.setCnpj("35667373000103 ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_cnpj_aceita_muitos_espacos_entre_os_numeros() {
		empresa.setCnpj("3566737       3000103");
	}
	
	@Test
	public void deve_testar_o_getCnpj() {
		empresa.setCnpj("35667373000103");
		assertEquals(empresa.getCnpj(), ("35667373000103"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_cnpj_tamanho_menor() {
		empresa.setCnpj("1313131313131");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_cnpj_tamanho_maior() {
		empresa.setCnpj("1515151515151515");
	}
	
	@Test
	public void deve_testar_se_o_nome_aceita_letras() {
		empresa.setNome("Gabriel");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_nome_aceita_null() {
		empresa.setNome(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_nome_aceita_vazio() {
		empresa.setNome("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_nome_aceita_espaco_em_branco() {
		empresa.setNome("          ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_nome_aceita_numeros() {
		empresa.setNome("123456");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_nome_aceita_caracteres_especiais() {
		empresa.setNome("@#$");
	}
		
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_nome_aceita_espaco_no_inicio() {
		empresa.setNome(" Gabriel");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_nome_aceita_espaco_no_final() {
		empresa.setNome("Gabriel ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_se_o_nome_aceita_muitos_espacos_entre_as_palavras() {
		empresa.setNome("Gabriel         Bueno");
	}
	
	@Test
	public void deve_testar_se_o_nome_aceita_um_espaco_entre_as_palavras() {
		empresa.setNome("Gabriel Bueno");
	}
	
	@Test
	public void deve_testar_o_getNome() {
		empresa.setNome("Gabriel Bueno");
		assertEquals(empresa.getNome(), "Gabriel Bueno");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_nome_tamanho_menor() {
		empresa.setNome("a");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_nome_tamanho_maior() {
		empresa.setNome("abcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcaabcabcabcabcabcaabcabcabc"
				+ "abcabcaabcabcabcabcabcabcabcabcabcabcabxc");
	}
	
	@Test
	public void deve_testar_o_getTelefone() {
		empresa.getTelefone();
	}
	
	@Test
	public void deve_testar_o_getEndereco() {
		empresa.getEndereco();
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

	@After
	public void TearDown() {
		cnpj = null;
		nome = null;
		telefone = null;
	}

	@AfterClass
	public static void TearDownAfterClass() {
		System.out.println("Finalizamos os testes na classe empresa\n");
	}

}