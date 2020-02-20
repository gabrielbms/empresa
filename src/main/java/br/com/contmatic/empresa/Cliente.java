package br.com.contmatic.empresa;

import java.math.BigDecimal;

public class Cliente {

	private String cpf;

	private String nome;

	private String telefone;

	private BigDecimal boleto;

	public Cliente(String cpf, String nome, String telefone, BigDecimal boleto) {
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
		this.boleto = boleto;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String setCpf) {
		if (setCpf == null || setCpf.trim().isEmpty()) {
			throw new IllegalArgumentException("Dados não preenchidos corretamente");
		}
		this.cpf = setCpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public BigDecimal getBoleto() {
		return boleto;
	}

	public void setBoleto(BigDecimal boleto) {
		this.boleto = boleto;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		if (this.cpf != null) {
			sb.append("cpf= ").append(this.cpf);
		}
		if (this.nome != null) {
			sb.append(" nome= ").append(this.nome);
		}
		if (this.telefone != null) {
			sb.append(" telefone= ").append(this.telefone);
		}
		if (this.boleto != BigDecimal.valueOf(0)) {
			sb.append(" boleto= ").append(this.boleto);
		}
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
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
		Cliente other = (Cliente) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}
}