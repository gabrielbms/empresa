package br.com.contmatic.empresa;

import static br.com.contmatic.util.Constantes.CNPJ_INVALIDO;
import static br.com.contmatic.util.Constantes.CNPJ_SIZE;
import static br.com.contmatic.util.Constantes.CNPJ_VAZIO;
import static br.com.contmatic.util.Constantes.ENDERECO_VAZIO;
import static br.com.contmatic.util.Constantes.NOME_INVALIDO;
import static br.com.contmatic.util.Constantes.NOME_MAX_SIZE;
import static br.com.contmatic.util.Constantes.NOME_MIN_SIZE;
import static br.com.contmatic.util.Constantes.NOME_VAZIO;
import static br.com.contmatic.util.Constantes.PRODUTO_VAZIO;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_CNPJ_GRANDE_DEMAIS;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_CNPJ_PEQUENO_DEMAIS;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_NOME_GRANDE_DEMAIS;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_NOME_PEQUENO_DEMAIS;
import static br.com.contmatic.util.Constantes.TELEFONE_VAZIO;
import static br.com.contmatic.util.RegexType.isLetrasENumeros;
import static br.com.contmatic.util.RegexType.isNumeros;
import static br.com.contmatic.util.Validate.isCNPJ;

import java.util.Set;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;

public class Fornecedor {

	private String cnpj;

	private String nome;

	private Telefone telefone;

	private Set<Produto> produtos;

	private Endereco endereco;

	public Fornecedor(String cnpj, String nome) {
		this.setCnpj(cnpj);
		this.setNome(nome);
	}

	public Fornecedor(String cnpj, String nome, Telefone telefone, Set<Produto> produto, Endereco endereco) {
		this.setCnpj(cnpj);
		this.setNome(nome);
		this.setTelefone(telefone);
		this.setProduto(produto);
		this.setEndereco(endereco);
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.validaCnpjIncorreto(cnpj);
		this.validaCalculoCnpj(cnpj);
		this.validaRegexCnpj(cnpj);
		this.cnpj = cnpj;
	}

	private void validaCalculoCnpj(String cnpj) {
		if (!isCNPJ(cnpj)) {
			throw new IllegalStateException(CNPJ_INVALIDO);
		}
	}

	private void validaCnpjIncorreto(String cnpj) {
		if (cnpj == null || cnpj.trim().isEmpty()) {
			throw new IllegalArgumentException(CNPJ_VAZIO);
		}
		if (cnpj.length() < CNPJ_SIZE) {
			throw new IllegalArgumentException(TAMANHO_DO_CNPJ_PEQUENO_DEMAIS);
		}
		if (cnpj.length() > CNPJ_SIZE) {
			throw new IllegalArgumentException(TAMANHO_DO_CNPJ_GRANDE_DEMAIS);
		}
	}

	private void validaRegexCnpj(String cnpj) {
		if (!isNumeros(cnpj)) {
			throw new IllegalArgumentException(CNPJ_INVALIDO);
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.validaNomeIncorreto(nome);
		this.validaRegexNome(nome);
		this.nome = nome;
	}

	private void validaNomeIncorreto(String nome) {
		if (nome == null || nome.trim().isEmpty()) {
			throw new IllegalArgumentException(NOME_VAZIO);
		}
		if (nome.length() < NOME_MIN_SIZE) {
			throw new IllegalArgumentException(TAMANHO_DO_NOME_PEQUENO_DEMAIS);
		}
		if (nome.length() > NOME_MAX_SIZE) {
			throw new IllegalArgumentException(TAMANHO_DO_NOME_GRANDE_DEMAIS);
		}
	}
	private void validaRegexNome(String nome) {
		if (!isLetrasENumeros(nome)) {
			throw new IllegalArgumentException(NOME_INVALIDO);
		}
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		validaTelefoneNullo(telefone);
		this.telefone = telefone;
	}

	private void validaTelefoneNullo(Telefone telefone) {
		if (telefone == null) {
			throw new IllegalArgumentException(TELEFONE_VAZIO);
		}
	}

	public Set<Produto> getProduto() {
		return produtos;
	}

	public void setProduto(Set<Produto> produto) {
		validaProdutoNullo(produto);
		this.produtos = produto;
	}

	private void validaProdutoNullo(Set<Produto> produto) {
		if (produto == null) {
			throw new IllegalArgumentException(PRODUTO_VAZIO);
		}
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		validaEnderecoNullo(endereco);
		this.endereco = endereco;
	}

	private void validaEnderecoNullo(Endereco endereco) {
		if (endereco == null) {
			throw new IllegalArgumentException(ENDERECO_VAZIO);
		} 
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fornecedor other = (Fornecedor) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (this.cnpj != null) {
			sb.append("cnpj= ").append(this.cnpj);
		}
		if (this.nome != null) {
			sb.append(" nome= ").append(this.nome);
		}
		if (this.telefone != null) {
			sb.append(" telefone= ").append(this.telefone);
		}
		if (this.produtos != null) {
			sb.append(" produto= ").append(this.produtos);
		}
		if (this.endereco != null) {
			sb.append(" endereco= ").append(this.endereco);
		}
		return sb.toString();
	}

}