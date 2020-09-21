package br.com.contmatic.empresa;

import static br.com.contmatic.util.Constantes.CNPJ_INCORRETO;
import static br.com.contmatic.util.Constantes.CNPJ_INVALIDO;
import static br.com.contmatic.util.Constantes.CNPJ_SIZE;
import static br.com.contmatic.util.Constantes.ENDERECO_VAZIO;
import static br.com.contmatic.util.Constantes.NOME_INCORRETO;
import static br.com.contmatic.util.Constantes.NOME_INVALIDO;
import static br.com.contmatic.util.Constantes.NOME_MAX_SIZE;
import static br.com.contmatic.util.Constantes.NOME_MIN_SIZE;
import static br.com.contmatic.util.Constantes.TELEFONE_VAZIO;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.util.RegexType;
import br.com.contmatic.util.Validate;

public class Empresa {

	private String cnpj;

	private String nome;

	private Telefone telefone;

	private Endereco endereco;

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
		this.validaCnpjIncorreto(cnpj);
		this.validaCnpjInvalido(cnpj);
		this.validaRegexCnpj(cnpj);
		this.cnpj = cnpj;
	}

	private void validaCnpjIncorreto(String cnpj) {
		if (cnpj == null || cnpj.trim().isEmpty() || cnpj.length() < CNPJ_SIZE || cnpj.length() > CNPJ_SIZE) {
			throw new IllegalArgumentException(CNPJ_INCORRETO);
		}
	}

	private void validaCnpjInvalido(String cnpj) {
		if (Validate.isCNPJ(cnpj) == false) {
			throw new IllegalStateException(CNPJ_INVALIDO);
		}
	}

	private void validaRegexCnpj(String cnpj) {
		if (!RegexType.isNumeros(cnpj)) {
			throw new IllegalArgumentException(CNPJ_INVALIDO);
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