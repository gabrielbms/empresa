package br.com.contmatic.empresa;

import static br.com.contmatic.util.Constantes.CNPJ_INCORRETO;
import static br.com.contmatic.util.Constantes.CNPJ_INVALIDO;
import static br.com.contmatic.util.Constantes.CNPJ_SIZE;
import static br.com.contmatic.util.Constantes.ENDERECO_VAZIO;
import static br.com.contmatic.util.Constantes.NOME_INCORRETO;
import static br.com.contmatic.util.Constantes.NOME_INVALIDO;
import static br.com.contmatic.util.Constantes.NOME_MAX_SIZE;
import static br.com.contmatic.util.Constantes.NOME_MIN_SIZE;
import static br.com.contmatic.util.Constantes.PRODUTO_VAZIO;
import static br.com.contmatic.util.Constantes.TELEFONE_VAZIO;

import java.util.Set;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.util.RegexType;
import br.com.contmatic.util.Validate;

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
		if (Validate.isCNPJ(cnpj) == false) {
			throw new IllegalStateException(CNPJ_INVALIDO);
		}
	}

	private void validaCnpjIncorreto(String cnpj) {
		if (cnpj == null || cnpj.trim().isEmpty() || cnpj.length() < CNPJ_SIZE || cnpj.length() > CNPJ_SIZE) {
			throw new IllegalArgumentException(CNPJ_INCORRETO);
		}
	}

	private void validaRegexCnpj(String cnpj) {
		if (!RegexType.isNumeros(cnpj)) {
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
		if (nome == null || nome.trim().isEmpty() || nome.length() < NOME_MIN_SIZE || nome.length() > NOME_MAX_SIZE) {
			throw new IllegalArgumentException(NOME_INCORRETO);
		}
	}

	private void validaRegexNome(String nome) {
		if (!RegexType.isLetrasENumeros(nome)) {
			throw new IllegalArgumentException(NOME_INVALIDO);
		}
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		validaTelefoneNullo(telefone);
	}

	private void validaTelefoneNullo(Telefone telefone) {
		if (telefone != null) {
			this.telefone = telefone;
		} else {
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
		if (produto != null) {
			this.produtos = produto;
		} else {
			throw new IllegalArgumentException(PRODUTO_VAZIO);
		}
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		validaEnderecoNullo(endereco);
	}

	private void validaEnderecoNullo(Endereco endereco) {
		if (endereco != null) {
			this.endereco = endereco;
		} else {
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