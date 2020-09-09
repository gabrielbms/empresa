package br.com.contmatic.empresa;

import java.math.BigDecimal;

import br.com.contmatic.endereco.Endereco;

public class Funcionario {

	private String cpf;

	private String nome;

	private int idade;

	private String telefone;

	private Endereco endereco;

	private BigDecimal salario;

	public Funcionario(String cpf, String nome, BigDecimal salario) {
		this.cpf = cpf;
		this.nome = nome;
		this.salario = salario;
	}

	public Funcionario(String cpf, String nome, int idade, String telefone, Endereco endereco, BigDecimal salario) {
		this.cpf = cpf;
		this.nome = nome;
		this.idade = idade;
		this.telefone = telefone;
		this.endereco = endereco;
		this.salario = salario;
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
	public int getIdade() {
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
		if (telefone == null || telefone.trim().isEmpty() || telefone.length() < 8 || telefone.length() > 9) {
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