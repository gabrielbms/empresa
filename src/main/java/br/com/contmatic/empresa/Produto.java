package br.com.contmatic.empresa;

import java.math.BigDecimal;

public class Produto {
	
	private Integer id;
	
	private String nome;
	
	private Integer quantidade;
	
	private BigDecimal preço;

	public Produto(Integer id, String nome) {
		super();
		this.setId(id);
		this.setNome(nome);
	}

	public Produto(Integer id, String nome, Integer quantidade, BigDecimal preço) {
		super();
		this.id = id;
		this.nome = nome;
		this.quantidade = quantidade;
		this.preço = preço;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		if (id == null || id.doubleValue() < 1) {
			throw new IllegalArgumentException("O ID foi preenchido incorretamente.");
		}
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome == null || nome.trim().isEmpty() || nome.length() < 2 || nome.length() > 80) {
			throw new IllegalArgumentException("O nome foi preenchido incorretamente.");
		}
		this.nome = nome;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		if (quantidade < 1) {
			throw new IllegalArgumentException("A quantidade não pode ser menor que um.");
		} 
		this.quantidade = quantidade;
	}

	public BigDecimal getPreço() {
		return preço;
	}

	public void setPreço(BigDecimal preço) {
		if (preço.doubleValue() < 1) {
			throw new IllegalArgumentException("O preço não pode ser menor que um.");
		}
		this.preço = preço;
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		if (this.id != 0) {
			sb.append("id= ").append(this.id);
		}
		if (this.nome != null) {
			sb.append(" nome= ").append(this.nome);
		}
		if (this.quantidade != 0) {
			sb.append(" quantidade= ").append(this.quantidade);
		}
		if (this.preço != BigDecimal.valueOf(0)) {
			sb.append(" preço= ").append(this.preço);
		}
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
