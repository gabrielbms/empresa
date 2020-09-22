package br.com.contmatic.empresa;

import static br.com.contmatic.util.Constantes.BOLETO_NEGATIVO;
import static br.com.contmatic.util.Constantes.CPF_INVALIDO;
import static br.com.contmatic.util.Constantes.CPF_SIZE;
import static br.com.contmatic.util.Constantes.CPF_VAZIO;
import static br.com.contmatic.util.Constantes.NOME_INVALIDO;
import static br.com.contmatic.util.Constantes.NOME_MAX_SIZE;
import static br.com.contmatic.util.Constantes.NOME_MIN_SIZE;
import static br.com.contmatic.util.Constantes.NOME_VAZIO;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_CPF_GRANDE_DEMAIS;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_CPF_PEQUENO_DEMAIS;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_NOME_GRANDE_DEMAIS;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_NOME_PEQUENO_DEMAIS;
import static br.com.contmatic.util.Constantes.TELEFONE_VAZIO;
import static br.com.contmatic.util.RegexType.isLetras;
import static br.com.contmatic.util.RegexType.isNumeros;
import static br.com.contmatic.util.Validate.isCPF;

import java.math.BigDecimal;

import br.com.contmatic.telefone.Telefone;

public class Cliente {

	private String cpf;

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
		this.validaRegexCpf(cpf);
		this.cpf = cpf;
	}

	private void validaCalculoCpf(String cpf) {
		if (!isCPF(cpf)) {
			throw new IllegalStateException(CPF_INVALIDO);
		}
	}

	private void validaCpfIncorreto(String cpf) {
		if (cpf == null || cpf.trim().isEmpty()) {
			throw new IllegalArgumentException(CPF_VAZIO);
		}
		if (cpf.length() < CPF_SIZE) {
			throw new IllegalArgumentException(TAMANHO_DO_CPF_PEQUENO_DEMAIS);
		}
		if (cpf.length() > CPF_SIZE) {
			throw new IllegalArgumentException(TAMANHO_DO_CPF_GRANDE_DEMAIS);
		}
	}

	private void validaRegexCpf(String cpf) {
		if (!isNumeros(cpf)) {
			throw new IllegalArgumentException(CPF_INVALIDO);
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.validaNomeIncorreto(nome);
		validaRegexLetras(nome);
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

	private void validaRegexLetras(String nome) {
		if (!isLetras(nome)) {
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

	public BigDecimal getBoleto() {
		return boleto;
	}

	public void setBoleto(BigDecimal boleto) {
		this.validaValorBoleto(boleto);
		this.boleto = boleto;
	}

	private void validaValorBoleto(BigDecimal boleto) {
		if (boleto.doubleValue() < 1) {
			throw new IllegalArgumentException(BOLETO_NEGATIVO);
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