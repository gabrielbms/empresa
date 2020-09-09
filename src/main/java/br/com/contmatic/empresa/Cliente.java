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

	public void setCpf(String cpf) {
		if (cpf == null || cpf.trim().isEmpty() || cpf.length() < 11 || cpf.length() > 11) {
			throw new IllegalArgumentException("O CPF foi preenchido incorretamente.");
		}
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome == null || nome.trim().isEmpty() || nome.length() < 2 || nome.length() > 60) {
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

	public BigDecimal getBoleto() {
		return boleto;
	}

	public void setBoleto(BigDecimal boleto) {
		if (boleto.doubleValue() >= 1) {
			this.boleto = boleto;
		} else {
			throw new IllegalArgumentException("Boleto não pode ser menor que um.");
		}
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