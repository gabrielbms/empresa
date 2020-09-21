package br.com.contmatic.endereco;

import static br.com.contmatic.util.Constantes.BAIRRO_INCORRETO;
import static br.com.contmatic.util.Constantes.BAIRRO_INVALIDO;
import static br.com.contmatic.util.Constantes.BAIRRO_MAX_SIZE;
import static br.com.contmatic.util.Constantes.BAIRRO_MIN_SIZE;
import static br.com.contmatic.util.Constantes.CEP_INCORRETO;
import static br.com.contmatic.util.Constantes.CEP_INVALIDO;
import static br.com.contmatic.util.Constantes.CEP_SIZE;
import static br.com.contmatic.util.Constantes.CIDADE_INCORRETO;
import static br.com.contmatic.util.Constantes.CIDADE_INVALIDA;
import static br.com.contmatic.util.Constantes.CIDADE_MAX_SIZE;
import static br.com.contmatic.util.Constantes.CIDADE_MIN_SIZE;
import static br.com.contmatic.util.Constantes.COMPLEMENTO_INCORRETO;
import static br.com.contmatic.util.Constantes.COMPLEMENTO_INVALIDO;
import static br.com.contmatic.util.Constantes.COMPLEMENTO_MAX_SIZE;
import static br.com.contmatic.util.Constantes.COMPLEMENTO_MIN_SIZE;
import static br.com.contmatic.util.Constantes.ESTADO_VAZIO;
import static br.com.contmatic.util.Constantes.NUMERO_INCORRETO;
import static br.com.contmatic.util.Constantes.NUMERO_MINIMO;
import static br.com.contmatic.util.Constantes.RUA_INCORRETO;
import static br.com.contmatic.util.Constantes.RUA_INVALIDA;
import static br.com.contmatic.util.Constantes.RUA_MAX_SIZE;
import static br.com.contmatic.util.Constantes.RUA_MIN_SIZE;

import br.com.contmatic.util.RegexType;

public class Endereco {

	private String cep;

	private String rua;

	private Integer numero;

	private String complemento;

	private String bairro;

	private String cidade;

	private Estado estado;

	public Endereco(String cep, Integer numero) {
		this.setCep(cep);
		this.setNumero(numero);
	}

	public Endereco(String cep, String rua, Integer numero, String complemento, String bairro, String cidade,
			Estado estado) {
		this.setCep(cep);
		this.setRua(rua);
		this.setNumero(numero);
		this.setComplemento(complemento);
		this.setBairro(bairro);
		this.setCidade(cidade);
		this.setEstado(estado);
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.validaCepIncorreto(cep);
		this.validaRegexCep(cep);
		this.cep = cep;
	}

	private void validaCepIncorreto(String cep) {
		if (cep == null || cep.trim().isEmpty() || cep.length() < CEP_SIZE || cep.length() > CEP_SIZE) {
			throw new IllegalArgumentException(CEP_INCORRETO);
		}
	}

	private void validaRegexCep(String cep) {
		if (!RegexType.isNumeros(cep)) {
			throw new IllegalArgumentException(CEP_INVALIDO);
		}
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.validaRuaIncorreto(rua);
		this.validaRegexRua(rua);
		this.rua = rua;
	}

	private void validaRuaIncorreto(String rua) {
		if (rua == null || rua.trim().isEmpty() || rua.length() < RUA_MIN_SIZE || rua.length() > RUA_MAX_SIZE) {
			throw new IllegalArgumentException(RUA_INCORRETO);
		}
	}

	private void validaRegexRua(String rua) {
		if (!RegexType.isLetrasENumeros(rua)) {
			throw new IllegalArgumentException(RUA_INVALIDA);
		}
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.validaNumeroIncorreto(numero);
		this.numero = numero;
	}

	private void validaNumeroIncorreto(Integer numero) {
		if (numero == null || numero < NUMERO_MINIMO) {
			throw new IllegalArgumentException(NUMERO_INCORRETO);
		}
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.validaComplementoIncorreto(complemento);
		this.validaRegexComplemento(complemento);
		this.complemento = complemento;
	}

	private void validaComplementoIncorreto(String complemento) {
		if (complemento == null || complemento.trim().isEmpty() || complemento.length() < COMPLEMENTO_MIN_SIZE
				|| complemento.length() > COMPLEMENTO_MAX_SIZE) {
			throw new IllegalArgumentException(COMPLEMENTO_INCORRETO);
		}
	}

	private void validaRegexComplemento(String complemento) {
		if (!RegexType.isLetrasENumeros(complemento)) {
			throw new IllegalArgumentException(COMPLEMENTO_INVALIDO);
		}
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.validaBairroIncorreto(bairro);
		this.validaRegexBairro(bairro);
		this.bairro = bairro;
	}

	private void validaBairroIncorreto(String bairro) {
		if (bairro == null || bairro.trim().isEmpty() || bairro.length() < BAIRRO_MIN_SIZE
				|| bairro.length() > BAIRRO_MAX_SIZE) {
			throw new IllegalArgumentException(BAIRRO_INCORRETO);
		}
	}

	private void validaRegexBairro(String bairro) {
		if (!RegexType.isLetrasENumeros(bairro)) {
			throw new IllegalArgumentException(BAIRRO_INVALIDO);
		}
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.validaCidadeIncorreto(cidade);
		this.validaRegexCidade(cidade);
		this.cidade = cidade;
	}

	private void validaCidadeIncorreto(String cidade) {
		if (cidade == null || cidade.trim().isEmpty() || cidade.length() < CIDADE_MIN_SIZE
				|| cidade.length() > CIDADE_MAX_SIZE) {
			throw new IllegalArgumentException(CIDADE_INCORRETO);
		}
	}

	private void validaRegexCidade(String cidade) {
		if (!RegexType.isLetras(cidade)) {
			throw new IllegalArgumentException(CIDADE_INVALIDA);
		}
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estadoVazio(estado);
		this.estado = estado;
	}

	public void estadoVazio(Estado estado) {
		if (estado == null) {
			throw new IllegalArgumentException(ESTADO_VAZIO);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
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
		Endereco other = (Endereco) obj;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (this.cep != null) {
			sb.append("cep= ").append(this.cep);
		}
		if (this.rua != null) {
			sb.append(" rua= ").append(this.rua);
		}
		if (this.numero != 0) {
			sb.append(" numero= ").append(this.numero);
		}
		if (this.complemento != null) {
			sb.append(" complemento= ").append(this.complemento);
		}
		if (this.bairro != null) {
			sb.append(" bairro= ").append(this.bairro);
		}
		if (this.cidade != null) {
			sb.append(" cidade= ").append(this.cidade);
		}
		if (this.estado != null) {
			sb.append(" estado= ").append(this.estado);
		}
		return sb.toString();
	}

}