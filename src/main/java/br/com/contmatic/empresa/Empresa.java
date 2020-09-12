package br.com.contmatic.empresa;

import static br.com.contmatic.util.Constantes.CNPJ_INVALIDO;
import static br.com.contmatic.util.Constantes.NOME_INVALIDO;
import static br.com.contmatic.util.Constantes.TELEFONE_INVALIDO;
import static br.com.contmatic.util.RegexType.LETRAS;
import static br.com.contmatic.util.RegexType.NUMEROS;

import javax.validation.constraints.Pattern;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.util.Constantes;
import br.com.contmatic.util.Validate;

public class Empresa {

	@Pattern(regexp = NUMEROS, message = CNPJ_INVALIDO)
	private String cnpj;

	@Pattern(regexp = LETRAS, message = NOME_INVALIDO)
	private String nome;

	@Pattern(regexp = NUMEROS, message = TELEFONE_INVALIDO)
	private String telefone;

	private Endereco endereco;

	public Empresa(String cnpj) {
		this.setCnpj(cnpj);
	}

	public Empresa(String cnpj, String nome, String telefone) {
		this.setCnpj(cnpj);
		this.setNome(nome);
		this.setTelefone(telefone);
	}

	public Empresa(String cnpj, String nome, String telefone, Endereco endereco) {
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
		if (nome == null || nome.trim().isEmpty() || nome.length() < 2 || nome.length() > 80) {
			throw new IllegalArgumentException("O nome foi preenchido incorretamente.");
		}
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		validaTelefoneIncorreto(telefone);
		this.telefone = telefone;
	}

	private void validaTelefoneIncorreto(String telefone) {
		if (telefone == null || telefone.trim().isEmpty() || telefone.length() < Constantes.TEL_MIN_SIZE
				|| telefone.length() > Constantes.TEL_MAX_SIZE) {
			throw new IllegalArgumentException("O telefone foi preenchido incorretamente.");
		}
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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