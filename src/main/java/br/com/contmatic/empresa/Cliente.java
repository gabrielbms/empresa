package br.com.contmatic.empresa;

import java.math.BigDecimal;

import javax.validation.constraints.Pattern;

import br.com.contmatic.util.Constantes;
import br.com.contmatic.util.RegexType;
import br.com.contmatic.util.Validate;

public class Cliente {

	@Pattern(regexp = RegexType.NUMEROS, message = Constantes.CPF_INVALIDO)
	private String cpf;

	@Pattern(regexp = RegexType.LETRAS, message = Constantes.NOME_INVALIDO)
	private String nome;

	@Pattern(regexp = RegexType.NUMEROS, message = Constantes.TELEFONE_INVALIDO)
	private String telefone;

	private BigDecimal boleto;

	public Cliente(String cpf, String nome, BigDecimal boleto) {
		this.setCpf(cpf);
		this.setNome(nome);
		this.setBoleto(boleto);
	}

	public Cliente(String cpf, String nome, String telefone, BigDecimal boleto) {
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
		this.setBoleto(boleto);
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		if (cpf == null || cpf.trim().isEmpty() || cpf.length() < Constantes.CPF_SIZE
				|| cpf.length() > Constantes.CPF_SIZE) {
			throw new IllegalArgumentException("O CPF foi preenchido incorretamente.");
		}

		if (Validate.isCPF(cpf) == false) {
			throw new IllegalArgumentException("O CPF é inválido.");
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
		if (telefone == null || telefone.trim().isEmpty() || telefone.length() < Constantes.TEL_MIN_SIZE
				|| telefone.length() > Constantes.TEL_MAX_SIZE) {
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