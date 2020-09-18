package br.com.contmatic.telefone;

import static br.com.contmatic.util.Constantes.TEL_MAX_SIZE;
import static br.com.contmatic.util.Constantes.TEL_MIN_SIZE;

public class Telefone {

	private TelefoneDDDType ddd;

	private String numero;

	private TipoTelefoneType tipoTelefone;

	public Telefone(TelefoneDDDType ddd, String numero, TipoTelefoneType tipoTelefone) {
		this.ddd = ddd;
		this.setNumero(numero);
		this.tipoTelefone = tipoTelefone;
	}

	public TelefoneDDDType getDdd() {
		return ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		validaNumetoTelefoneIncorreto(numero);
		this.numero = numero;
	}

	private void validaNumetoTelefoneIncorreto(String numero) {
		if (numero == null || numero.trim().isEmpty() || numero.length() < TEL_MIN_SIZE
				|| numero.length() > TEL_MAX_SIZE) {
			throw new IllegalArgumentException("O numero do telefone foi preenchido incorretamente.");
		}
	}

	public TipoTelefoneType getTipoTelefone() {
		return validaTipoTelefone(numero);
	}

	private TipoTelefoneType validaTipoTelefone(String numero) {
		if (numero.length() == TEL_MAX_SIZE) {
			return TipoTelefoneType.CELULAR;
		} 
		if (numero.length() == TEL_MIN_SIZE) {
			return TipoTelefoneType.FIXO;
		} else {
			throw new IllegalArgumentException("O telefone foi preenchido incorretamente.");
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
