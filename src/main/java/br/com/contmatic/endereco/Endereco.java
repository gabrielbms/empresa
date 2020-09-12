package br.com.contmatic.endereco;

import static br.com.contmatic.util.Constantes.BAIRRO_INVALIDO;
import static br.com.contmatic.util.Constantes.CEP_INVALIDO;
import static br.com.contmatic.util.Constantes.CEP_SIZE;
import static br.com.contmatic.util.Constantes.CIDADE_INVALIDA;
import static br.com.contmatic.util.Constantes.COMPLEMENTO_INVALIDO;
import static br.com.contmatic.util.Constantes.ESTADO_INVALIDO;
import static br.com.contmatic.util.Constantes.RUA_INVALIDA;
import static br.com.contmatic.util.RegexType.LETRAS;
import static br.com.contmatic.util.RegexType.LETRAS_E_NUMEROS;
import static br.com.contmatic.util.RegexType.NUMEROS;

import javax.validation.constraints.Pattern;

public class Endereco {

	@Pattern(regexp = NUMEROS, message = CEP_INVALIDO)
	private String cep;

	@Pattern(regexp = LETRAS_E_NUMEROS, message = RUA_INVALIDA)
	private String rua;

	private Integer numero;

	@Pattern(regexp = LETRAS_E_NUMEROS, message = COMPLEMENTO_INVALIDO)
	private String complemento;

	@Pattern(regexp = LETRAS_E_NUMEROS, message = BAIRRO_INVALIDO)
	private String bairro;

	@Pattern(regexp = LETRAS, message = CIDADE_INVALIDA)
	private String cidade;

	@Pattern(regexp = LETRAS, message = ESTADO_INVALIDO)
	private String estado;

	public Endereco(String cep, Integer numero) {
		this.setCep(cep);
		this.setNumero(numero);
	}

	public Endereco(String cep, String rua, Integer numero, String complemento, String bairro, String cidade,
			String estado) {
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
		this.cep = cep;
	}

	private void validaCepIncorreto(String cep) {
		if (cep == null || cep.trim().isEmpty() || cep.length() < CEP_SIZE || cep.length() > CEP_SIZE) {
			throw new IllegalArgumentException("O nome foi preenchido incorretamente.");
		}
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.validaNumeroIncorreto(numero);
		this.numero = numero;
	}

	private void validaNumeroIncorreto(Integer numero) {
		if (numero == null || numero < 1) {
			throw new IllegalArgumentException("O numero foi preenchido incorretamente.");
		}
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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