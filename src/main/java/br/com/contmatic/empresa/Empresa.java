package br.com.contmatic.empresa;

import static br.com.contmatic.util.Constantes.CNPJ_INVALIDO;
import static br.com.contmatic.util.Constantes.NOME_INVALIDO;
import static br.com.contmatic.util.Constantes.NOME_MAX_SIZE;
import static br.com.contmatic.util.Constantes.NOME_MIN_SIZE;
import static br.com.contmatic.util.RegexType.LETRAS;
import static br.com.contmatic.util.RegexType.NUMEROS;

import javax.validation.constraints.Pattern;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.util.Constantes;
import br.com.contmatic.util.Validate;

public class Empresa {

	@Pattern(regexp = NUMEROS, message = CNPJ_INVALIDO)
	private String cnpj;

	@Pattern(regexp = LETRAS, message = NOME_INVALIDO)
	private String nome;

	private Telefone telefone;

	private Endereco endereco;

	public Empresa(String cnpj) {
		this.setCnpj(cnpj);
	}

	public Empresa(String cnpj, String nome, Telefone telefone, Endereco endereco) {
		this.setCnpj(cnpj);
		this.setNome(nome);
		this.setTelefone(telefone);
		this.setEndereco(endereco);
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		validaCnpjIncorreto(cnpj);
		validaCnpjInvalido(cnpj);
		this.cnpj = cnpj;
	}

	private void validaCnpjIncorreto(String cnpj) {
		if (cnpj == null || cnpj.trim().isEmpty() || cnpj.length() < Constantes.CNPJ_SIZE 
				|| cnpj.length() > Constantes.CNPJ_SIZE) {
			throw new IllegalArgumentException("O CNPJ foi preenchido incorretamente.");
		}
	}
	
	private void validaCnpjInvalido(String cnpj) {
		if (Validate.isCNPJ(cnpj) == false) {
			throw new IllegalArgumentException("O CNPJ é inválido.");
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		validaNomeIncorreto(nome);
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
			throw new IllegalArgumentException("O endereco não foi preenchido.");
		}
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
		Empresa other = (Empresa) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		return true;
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
		if (this.endereco != null) {
			sb.append(" endereco= ").append(this.endereco);
		}
		return sb.toString();
	}

}