package br.com.contmatic.endereco;

public class Endereco {

	private String cep;

	private String rua;

	private Integer numero;

	private String complemento;

	private String bairro;

	private String cidade;

	private String estado;
	
	private static final int CEP_SIZE = 8;

	public Endereco(String cep, Integer numero) {
		this.setCep(cep);
		this.setNumero(numero);
	}

	public Endereco(String cep, String rua, Integer numero, String complemento, String bairro, String cidade,
			String estado) {
		this.cep = cep;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		if (cep == null || cep.trim().isEmpty() || cep.length() < CEP_SIZE || cep.length() > CEP_SIZE) {
			throw new IllegalArgumentException("O nome foi preenchido incorretamente.");
		}
		this.cep = cep;
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
		if (numero == null || numero < 1) {
			throw new IllegalArgumentException("O numero foi preenchido incorretamente.");
		}
		this.numero = numero;
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
}