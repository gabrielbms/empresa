package br.com.contmatic.empresa;

import br.com.contmatic.endereco.Endereco;

public class Fornecedor {

	private String cnpj;

	private String nome;

	private String telefone;

	private String produto;

	private Endereco endereco;

	public Fornecedor(String cnpj, String nome) {
		this.cnpj = cnpj;
		this.nome = nome;
	}

	public Fornecedor(String cnpj, String nome, String telefone, String produto, Endereco endereco) {
		this.cnpj = cnpj;
		this.nome = nome;
		this.telefone = telefone;
		this.produto = produto;
		this.endereco = endereco;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		if (cnpj == null || cnpj.trim().isEmpty() || cnpj.length() < 14 || cnpj.length() > 14) {
			throw new IllegalArgumentException("O CNPJ foi preenchido incorretamente.");
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
		if (telefone == null || telefone.trim().isEmpty() || telefone.length() < 8 || telefone.length() > 9) {
			throw new IllegalArgumentException("O telefone foi preenchido incorretamente.");
		}
		this.telefone = telefone;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		if (produto == null || produto.trim().isEmpty() || produto.length() < 2 || produto.length() > 50) {
			throw new IllegalArgumentException("O produto foi preenchido incorretamente.");
		}
		this.produto = produto;
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
		if (this.produto != null) {
			sb.append(" produto= ").append(this.produto);
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