package br.com.contmatic.telefone;

import static br.com.contmatic.util.Constantes.DDD_VAZIO;
import static br.com.contmatic.util.Constantes.TELEFONE_INVALIDO;
import static br.com.contmatic.util.Constantes.TELEFONE_PREENCHIDO_INCORRETAMENTE;
import static br.com.contmatic.util.Constantes.TEL_MAX_SIZE;
import static br.com.contmatic.util.Constantes.TEL_MIN_SIZE;
import static br.com.contmatic.util.Constantes.TIPO_TELEFONE_VAZIO;
import static br.com.contmatic.util.RegexType.isNotNumeros;

public class Telefone {

	private TelefoneDDDType ddd;

	private String numero;

	private TelefoneType tipoTelefone;

	public Telefone(TelefoneDDDType ddd, String numero, TelefoneType tipoTelefone) {
		this.setDdd(ddd);
		this.setNumero(numero);
		this.setTipoTelefone(tipoTelefone);
	}

	public TelefoneDDDType getDdd() {
		return ddd;
	}
	
	public void setDdd(TelefoneDDDType ddd) {
		this.dddVazio(ddd);
		this.ddd = ddd;
	}
	
	public void dddVazio(TelefoneDDDType ddd) {
		if (ddd == null) {
			throw new IllegalArgumentException(DDD_VAZIO);
		}
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.validaNumetoTelefoneIncorreto(numero);
		this.validaRegexNumero(numero);
		this.numero = numero;
	}

	private void validaNumetoTelefoneIncorreto(String numero) {
		if (numero == null || numero.trim().isEmpty() || numero.length() < TEL_MIN_SIZE
				|| numero.length() > TEL_MAX_SIZE) {
			throw new IllegalArgumentException(TELEFONE_PREENCHIDO_INCORRETAMENTE);
		}
	}

	private void validaRegexNumero(String numero) {
		if (isNotNumeros(numero)) {
			throw new IllegalArgumentException(TELEFONE_INVALIDO);
		}
	}

	public TelefoneType getTipoTelefone() {
		return validaTipoTelefone(numero);
	}
	
	public void setTipoTelefone(TelefoneType tipoTelefone) {
		this.tipoTelefoneVazio(tipoTelefone);
		this.tipoTelefone = tipoTelefone;
	}
	
	public void tipoTelefoneVazio(TelefoneType tipoTelefone) {
		if (tipoTelefone == null) {
			throw new IllegalArgumentException(TIPO_TELEFONE_VAZIO);
		}
	}

	private TelefoneType validaTipoTelefone(String numero) {
		if (numero.length() == TEL_MAX_SIZE) {
			return TelefoneType.CELULAR;
		}
		if (numero.length() == TEL_MIN_SIZE) {
			return TelefoneType.FIXO;
		} else {
			throw new IllegalArgumentException(TELEFONE_INVALIDO);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ddd == null) ? 0 : ddd.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		Telefone other = (Telefone) obj;
		if (ddd != other.ddd)
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (this.ddd != null) {
			sb.append("DDD= ").append(this.ddd);
		}
		if (this.numero != null) {
			sb.append(" numero= ").append(this.numero);
		}
		if (this.tipoTelefone != null) {
			sb.append(" tipo do telefone= ").append(this.tipoTelefone);
		}
		return sb.toString();
	}

}
