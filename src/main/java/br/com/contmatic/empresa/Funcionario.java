package br.com.contmatic.empresa;

import static br.com.contmatic.util.Constantes.CPF_INVALIDO;
import static br.com.contmatic.util.Constantes.CPF_SIZE;
import static br.com.contmatic.util.Constantes.ENDERECO_VAZIO;
import static br.com.contmatic.util.Constantes.IDADE_MINIMA;
import static br.com.contmatic.util.Constantes.IDADE_MINIMA_MENSAGEM;
import static br.com.contmatic.util.Constantes.NOME_INCORRETO;
import static br.com.contmatic.util.Constantes.NOME_INVALIDO;
import static br.com.contmatic.util.Constantes.NOME_MAX_SIZE;
import static br.com.contmatic.util.Constantes.NOME_MIN_SIZE;
import static br.com.contmatic.util.Constantes.SALARIO_MINIMO;
import static br.com.contmatic.util.Constantes.SALARIO_MINIMO_MENSAGEM;
import static br.com.contmatic.util.Constantes.TELEFONE_VAZIO;

import java.math.BigDecimal;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.util.RegexType;
import br.com.contmatic.util.Validate;

public class Funcionario {

	private String cpf;

	private String nome;

	private Integer idade;

	private Telefone telefone;

	private Endereco endereco;

	private BigDecimal salario;

	public Funcionario(String cpf, String nome, BigDecimal salario) {
		this.setCpf(cpf);
		this.setNome(nome);
		this.setSalario(salario);
	}

	public Funcionario(String cpf, String nome, int idade, Telefone telefone, Endereco endereco, BigDecimal salario) {
		this.setCpf(cpf);
		this.setNome(nome);
		this.setIdade(idade);
		this.setTelefone(telefone);
		this.setEndereco(endereco);
		this.setSalario(salario);
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
		if (Validate.isCPF(cpf) == false) {
			throw new IllegalStateException("O CPF é inválido.");
		}
	}

	private void validaCpfIncorreto(String cpf) {
		if (cpf == null || cpf.trim().isEmpty() || cpf.length() < CPF_SIZE || cpf.length() > CPF_SIZE) {
			throw new IllegalArgumentException("O CPF foi preenchido incorretamente.");
		}
	}

	private void validaRegexCpf(String cpf) {
		if (!RegexType.isNumeros(cpf)) {
			throw new IllegalArgumentException(CPF_INVALIDO);
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
		if (!RegexType.isLetras(nome)) {
			throw new IllegalArgumentException(NOME_INVALIDO);
		}
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.validaIdade(idade);
	}

	private void validaIdade(int idade) {
		if (idade >= IDADE_MINIMA) {
			this.idade = idade;
		} else {
			throw new IllegalArgumentException(IDADE_MINIMA_MENSAGEM);
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

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.validaSalario(salario);
	}

	private void validaSalario(BigDecimal salario) {
		if (salario.doubleValue() >= SALARIO_MINIMO) {
			this.salario = salario;
		} else {
			throw new IllegalArgumentException(SALARIO_MINIMO_MENSAGEM);
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
		Funcionario other = (Funcionario) obj;
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
		if (this.idade != null) {
			sb.append(" idade= ").append(this.idade);
		}
		if (this.telefone != null) {
			sb.append(" telefone= ").append(this.telefone);
		}
		if (this.endereco != null) {
			sb.append(" endereco= ").append(this.endereco);
		}
		if (this.salario != BigDecimal.valueOf(0)) {
			sb.append(" salario= ").append(this.salario);
		}
		return sb.toString();
	}

}