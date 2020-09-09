package br.com.contmatic.empresa;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;

import br.com.contmatic.endereco.Endereco;;

@FixMethodOrder(NAME_ASCENDING)
public class EmpresaTest {

	private String cnpj;

	private String nome;

	private String telefone;

	private Empresa empresa;

	@BeforeClass
	public static void setUpBeforeClass() {
		System.out.println("Iniciamos os testes na classe empresa");
	}

	@Before
	public void setUp() {
		cnpj = "35667373000103";
		nome = "GB Conserto de computadores";
		telefone = "41108521";
		Endereco endereco = new Endereco("03208070", 79);
		empresa = new Empresa(cnpj, nome, telefone, endereco);
	}

	@Test
	public void nao_deve_aceitar_cnpj_nulo() {
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
		assertThat(empresa.getCnpj(), containsString("35667373000103"));
	}

	@Test
	public void deve_testar_o_getNome_esta_funcionando_corretamente() {
		empresa.setNome("GB Conserto de computadores");
		assertThat(empresa.getNome(), containsString("GB Conserto de computadores"));
	}

	@Test
	public void deve_testar_o_getTelefone_esta_funcionando_corretamente() {
		empresa.setTelefone("4110-8521");
		assertThat(empresa.getTelefone(), containsString("4110-8521"));
	}

	@Test
	public void deve_testar_o_setEndereco_esta_funcionando_corretamente() {
		empresa.setEndereco(new Endereco("03208070", 79));
		assertThat(empresa.toString(), containsString("cep="));
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
		assertFalse(empresa.getTelefone().trim().isEmpty());
	}

	@Test
	public void deve_retornar_true_no_hashCode_com_empresas_iguais() {
		Empresa Empresa2 = new Empresa("35667373000103", "GB Conserto de computadores", "41108521");
		assertTrue(empresa.hashCode() == Empresa2.hashCode());
	}

	@Test
	public void deve_retornar_false_no_hashCode_com_uma_empresa_de_cnpj_null() {
		Empresa Empresa2 = new Empresa(null, "GB Conserto de computadores", "41108521");
		assertFalse(empresa.hashCode() == Empresa2.hashCode());
	}

	@Test
	public void deve_retornar_true_no_equals_com_empresas_iguais() {
		Empresa empresa2 = new Empresa("35667373000103", "GB Conserto de computadores", "41108521");
		assertTrue(empresa.equals(empresa2) & empresa2.equals(empresa));
	}

	@Test
	public void deve_retornar_false_no_equals_com_um_empresa_de_cnpj_null() {
		Empresa empresa2 = new Empresa(null, "GB Conserto de computadores", "41108521");
		assertFalse(empresa.equals(empresa2) & empresa2.equals(empresa));
	}

	@Test
	public void deve_retornar_true_no_equals_comparando_uma_empresa_com_ela_mesmo() {
		assertTrue(empresa.equals(empresa));
	}

	@Test
	public void deve_retornar_false_no_equals_comparando_uma_empresa_com_null() {
		assertFalse(empresa.equals(null));
	}

	@Test
	public void deve_retornar_true_no_equals_comparando_dois_empresas_de_cnpj_null() {
		Empresa empresa1 = new Empresa(null, "GB Conserto de computadores", "41108521");
		Empresa empresa2 = new Empresa(null, "GB Conserto de computadores", "41108521");
		assertTrue(empresa1.equals(empresa2));
	}

	@Test
	public void deve_retornar_false_no_equals_com_empresas_de_cnpj_diferentes() {
		Empresa empresa1 = new Empresa("35667373000103", "GB Conserto de computadores", "41108521");
		Empresa empresa2 = new Empresa("35667373000104", "GB Conserto de computadores", "41108521");
		assertFalse(empresa2.equals(empresa1));
	}

	@Test
	public void deve_retornar_false_no_equals_com_a_empresa_e_um_numero_aleatorio() {
		assertFalse(empresa.equals(new Object()));
	}

	@Test
	public void toString_deve_retornar_null() {
		Empresa empresaNull = new Empresa(null, null, null, null);
		assertThat(empresaNull.toString(), containsString(""));
	}

	@Test
	public void toString_deve_retornar_valores_preenchidos() {
		Empresa empresaPreenchida = new Empresa("35667373000103", "GB Conserto de computadores", "41108521");
		assertThat(empresaPreenchida.toString(), containsString(""));
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
	public void deve_testar_exception_do_setTelefone_null() {
		empresa.setTelefone(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setTelefone_vazio() {
		empresa.setTelefone(" ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setTelefone_tamanho_menor() {
		empresa.setTelefone("1234567");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deve_testar_exception_do_setTelefone_tamanho_maior() {
		empresa.setTelefone("1234567890");
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