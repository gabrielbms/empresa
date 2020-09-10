package br.com.contmatic.empresa;

import java.util.Set;

import javax.validation.constraints.Pattern;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.util.Constantes;
import br.com.contmatic.util.RegexType;
import br.com.contmatic.util.Validate;

public class Fornecedor {

	@Pattern(regexp = RegexType.NUMEROS, message = Constantes.CNPJ_INVALIDO)
	private String cnpj;

	@Pattern(regexp = RegexType.LETRAS, message = Constantes.NOME_INVALIDO)
	private String nome;

	@Pattern(regexp = RegexType.NUMEROS, message = Constantes.TELEFONE_INVALIDO)
	private String telefone;

	private Set<Produto> produtos;

	private Endereco endereco;

	public Fornecedor(String cnpj, String nome) {
		this.cnpj = cnpj;
		this.setNome(nome);
	}

	public Fornecedor(String cnpj, String nome, String telefone, Set<Produto> produto, Endereco endereco) {
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
		if (cnpj == null || cnpj.trim().isEmpty() || cnpj.length() < Constantes.CNPJ_SIZE
				|| cnpj.length() > Constantes.CNPJ_SIZE) {
			throw new IllegalArgumentException("O CNPJ foi preenchido incorretamente.");
		}
		if (Validate.isCNPJ(cnpj) == false) {
			throw new IllegalArgumentException("O CNPJ é inválido.");
		}
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome == null || nome.trim().isEmpty() || nome.length() < 2 || nome.length() > 80) {
			throw new IllegalArgumentException("O nome foi preenchido incorretamente.");
		}
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		if (telefone == null || telefone.trim().isEmpty() || telefone.length() < Constantes.TEL_MIN_SIZE
				|| telefone.length() > Constantes.TEL_MAX_SIZE) {
			throw new IllegalArgumentException("O telefone foi preenchido incorretamente.");
		}
		this.telefone = telefone;
	}

	public Set<Produto> getProduto() {
		return produtos;
	}

	public void setProduto(Set<Produto> produto) {
		this.produtos = produto;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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

}