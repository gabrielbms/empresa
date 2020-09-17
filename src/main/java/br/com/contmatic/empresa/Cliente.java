package br.com.contmatic.empresa;

import static br.com.contmatic.util.Constantes.CPF_INVALIDO;
import static br.com.contmatic.util.Constantes.CPF_SIZE;
import static br.com.contmatic.util.Constantes.NOME_INVALIDO;
import static br.com.contmatic.util.Constantes.NOME_MAX_SIZE;
import static br.com.contmatic.util.Constantes.NOME_MIN_SIZE;
import static br.com.contmatic.util.RegexType.LETRAS;
import static br.com.contmatic.util.RegexType.NUMEROS;

import java.math.BigDecimal;

import javax.validation.constraints.Pattern;

import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.util.Validate;

public class Cliente {

	@Pattern(regexp = NUMEROS, message = CPF_INVALIDO)
	private String cpf;

	@Pattern(regexp = LETRAS, message = NOME_INVALIDO)
	private String nome;

	private Telefone telefone;

	private BigDecimal boleto;

	public Cliente(String cpf, String nome, BigDecimal boleto) {
		this.setCpf(cpf);
		this.setNome(nome);
		this.setBoleto(boleto);
	}

	public Cliente(String cpf, String nome, Telefone telefone, BigDecimal boleto) {
		this.setCpf(cpf);
		this.setNome(nome);
		this.setTelefone(telefone);
		this.setBoleto(boleto);
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.validaCpfIncorreto(cpf);
		this.validaCalculoCpf(cpf);
		this.cpf = cpf;
	}

	private void validaCalculoCpf(String cpf) {
		if (Validate.isCPF(cpf) == false) {
			throw new IllegalArgumentException("O CPF é inválido.");
		}
	}

	private void validaCpfIncorreto(String cpf) {
		if (cpf == null || cpf.trim().isEmpty() || cpf.length() < CPF_SIZE	|| cpf.length() > CPF_SIZE) {
			throw new IllegalArgumentException("O CPF foi preenchido incorretamente.");
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.validaNomeIncorreto(nome);
		this.nome = nome;
	}

	private void validaNomeIncorreto(String nome) {
		if (nome == null || nome.trim().isEmpty() || nome.length() < NOME_MIN_SIZE || nome.length() > NOME_MAX_SIZE) {
			throw new IllegalArgumentException("O nome foi preenchido incorretamente.");
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
			throw new IllegalArgumentException("O telefone não foi preenchido.");
		}
	}

	public BigDecimal getBoleto() {
		return boleto;
	}

	public void setBoleto(BigDecimal boleto) {
		this.validaValorBoleto(boleto);
	}

	private void validaValorBoleto(BigDecimal boleto) {
		if (boleto.doubleValue() >= 1) {
			this.boleto = boleto;
		} else {
			throw new IllegalArgumentException("Boleto não pode ser menor que um.");
		}
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

}