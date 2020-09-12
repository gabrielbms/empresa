package br.com.contmatic.empresa;

import static br.com.contmatic.util.Constantes.CPF_INVALIDO;
import static br.com.contmatic.util.Constantes.CPF_SIZE;
import static br.com.contmatic.util.Constantes.NOME_INVALIDO;
import static br.com.contmatic.util.Constantes.TELEFONE_INVALIDO;
import static br.com.contmatic.util.Constantes.TEL_MAX_SIZE;
import static br.com.contmatic.util.Constantes.TEL_MIN_SIZE;
import static br.com.contmatic.util.RegexType.LETRAS;
import static br.com.contmatic.util.RegexType.NUMEROS;

import java.math.BigDecimal;

import javax.validation.constraints.Pattern;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.util.Validate;

public class Funcionario {

	@Pattern(regexp = NUMEROS, message = CPF_INVALIDO)
	private String cpf;

	@Pattern(regexp = LETRAS, message = NOME_INVALIDO)
	private String nome;

	private Integer idade;

	@Pattern(regexp = NUMEROS, message = TELEFONE_INVALIDO)
	private String telefone;

	private Endereco endereco;

	private BigDecimal salario;

	public Funcionario(String cpf, String nome, BigDecimal salario) {
		this.setCpf(cpf);
		this.setNome(nome);
		this.setSalario(salario);
	}

	public Funcionario(String cpf, String nome, int idade, String telefone, BigDecimal salario) {
		this.setCpf(cpf);
		this.setNome(nome);
		this.setIdade(idade);
		this.setTelefone(telefone);
		this.setSalario(salario);
	}

	public Funcionario(String cpf, String nome, int idade, String telefone, Endereco endereco, BigDecimal salario) {
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
		if (nome == null || nome.trim().isEmpty() || nome.length() < 2 || nome.length() > 60) {
			throw new IllegalArgumentException("O nome foi preenchido incorretamente.");
		}
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.validaIdade(idade);
	}

	private void validaIdade(int idade) {
		if (idade >= 14) {
			this.idade = idade;
		} else {
			throw new IllegalArgumentException("salario não pode ser menor que 14.");
		}
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.validaTelefoneIncorreto(telefone);
		this.telefone = telefone;
	}

	private void validaTelefoneIncorreto(String telefone) {
		if (telefone == null || telefone.trim().isEmpty() || telefone.length() < TEL_MIN_SIZE|| telefone.length() > TEL_MAX_SIZE) {
			throw new IllegalArgumentException("O telefone foi preenchido incorretamente.");
		}
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.validaSalario(salario);
	}

	private void validaSalario(BigDecimal salario) {
		if (salario.doubleValue() >= 1) {
			this.salario = salario;
		} else {
			throw new IllegalArgumentException("salario não pode ser menor que um.");
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
		if (this.idade != 0) {
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