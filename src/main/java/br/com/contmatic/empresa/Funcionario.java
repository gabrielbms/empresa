package br.com.contmatic.empresa;

import java.math.BigDecimal;

import javax.validation.constraints.Pattern;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.util.Constantes;
import br.com.contmatic.util.RegexType;
import br.com.contmatic.util.Validate;

public class Funcionario {

	@Pattern(regexp = RegexType.NUMEROS, message = Constantes.CPF_INVALIDO)
	private String cpf;

	@Pattern(regexp = RegexType.LETRAS, message = Constantes.NOME_INVALIDO)
	private String nome;

	private Integer idade;

	@Pattern(regexp = RegexType.NUMEROS, message = Constantes.TELEFONE_INVALIDO)
	private String telefone;

	private Endereco endereco;

	private BigDecimal salario;

	public Funcionario(String cpf, String nome, BigDecimal salario) {
		this.cpf = cpf;
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
		this.cpf = cpf;
		this.nome = nome;
		this.idade = idade;
		this.telefone = telefone;
		this.endereco = endereco;
		this.setSalario(salario);
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

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
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
		if (telefone == null || telefone.trim().isEmpty() || telefone.length() < Constantes.TEL_MIN_SIZE
				|| telefone.length() > Constantes.TEL_MAX_SIZE) {
			throw new IllegalArgumentException("O telefone foi preenchido incorretamente.");
		}
		this.telefone = telefone;
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
		if (salario.doubleValue() >= 1) {
			this.salario = salario;
		} else {
			throw new IllegalArgumentException("salario não pode ser menor que um.");
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
}