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
import br.com.contmatic.telefone.TelefoneType;;

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
		telefone = new Telefone(TelefoneDDDType.DDD11, "978457845", TelefoneType.CELULAR);
		endereco = new Endereco("03208070", 79);
		empresa = new Empresa(cnpj, nome, telefone, endereco);
	}

	@Test
	public void deve_testar_se_o_cnpj_aceita_numeros() {
		empresa.setCnpj("35667373000103");
		assertEquals("35667373000103", empresa.getCnpj());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_null_no_cnpj() {
		empresa.setCnpj(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_vazio_no_cnpj() {
		empresa.setCnpj("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_espacos_no_cnpj() {
		empresa.setCnpj("  ");
	}
	
	@Test(expected = IllegalStateException.class)
	public void nao_deve_aceitar_letras_no_cnpj() {
		empresa.setCnpj("abcdefabcadgef");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_caracteres_especiais_no_cnpj() {
		empresa.setCnpj("@#$");
	}
		
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_espacos_no_inicio_do_cnpj() {
		empresa.setCnpj(" 35667373000103");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_espacos_no_fim_do_cnpj() {
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
	
	
	@Test(expected = IllegalStateException.class)
	public void deve_testar_exception_a_validação_do_cnpj() {
		empresa.setCnpj("35667373000104");
	}
	
	@Test
	public void deve_testar_se_o_nome_aceita_letras() {
		empresa.setNome("Gabriel");
		assertEquals("Gabriel", empresa.getNome());
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_null_no_nome() {
		empresa.setNome(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_vazio_no_nome() {
		empresa.setNome("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_espacos_no_nome() {
		empresa.setNome("          ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_numeros_no_nome() {
		empresa.setNome("123456");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_caracteres_especiais_no_nome() {
		empresa.setNome("@#$");
	}
		
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_espaco_no_inicio_do_nome() {
		empresa.setNome(" Gabriel");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_espaco_no_fim_do_nome() {
		empresa.setNome("Gabriel ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_espaco_no_meio_do_nome() {
		empresa.setNome("Gabriel         Bueno");
	}
	
	@Test
	public void deve_testar_se_o_nome_aceita_um_espaco_entre_as_palavras() {
		empresa.setNome("Gabriel Bueno");
		assertEquals("Gabriel Bueno", empresa.getNome());
	}
	
	@Test
	public void deve_testar_o_getNome() {
		empresa.setNome("Gabriel Bueno");
		assertEquals("Gabriel Bueno", empresa.getNome());
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
		empresa.setTelefone(telefone);
		assertEquals(empresa.getTelefone(), telefone);
	}
	
	@Test
	public void deve_testar_o_getEndereco() {
		empresa.setEndereco(endereco);
		assertEquals(empresa.getEndereco(), endereco);
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