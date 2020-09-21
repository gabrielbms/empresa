package br.com.contmatic.empresa;

import static br.com.contmatic.util.Constantes.ID_MINIMO;
import static br.com.contmatic.util.Constantes.NOME_INCORRETO;
import static br.com.contmatic.util.Constantes.NOME_INVALIDO;
import static br.com.contmatic.util.Constantes.NOME_MAX_SIZE;
import static br.com.contmatic.util.Constantes.NOME_MIN_SIZE;
import static br.com.contmatic.util.Constantes.PRECO_MINIMO_MENSAGEM;
import static br.com.contmatic.util.Constantes.PREÇO_MINIMO;
import static br.com.contmatic.util.Constantes.QUANTIDADE_MINIMA;
import static br.com.contmatic.util.Constantes.QUANTIDADE_MINIMA_MENSAGEM;

import java.math.BigDecimal;

import br.com.contmatic.util.RegexType;

public class Produto {

	private Integer id;

	private String nome;

	private Integer quantidade;

	private BigDecimal preço;

	public Produto(Integer id, String nome, Integer quantidade, BigDecimal preço) {
		this.setId(id);
		this.setNome(nome);
		this.setQuantidade(quantidade);
		this.setPreço(preço);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.validaIdIncorreto(id);
		this.id = id;
	}

	private void validaIdIncorreto(Integer id) {
		if (id == null || id.doubleValue() < ID_MINIMO) {
			throw new IllegalArgumentException("O ID foi preenchido incorretamente.");
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
		if (!RegexType.isLetrasENumeros(nome)) {
			throw new IllegalArgumentException(NOME_INVALIDO);
		}
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.validaQuantidadeIncorreta(quantidade);
		this.quantidade = quantidade;
	}

	private void validaQuantidadeIncorreta(Integer quantidade) {
		if (quantidade < QUANTIDADE_MINIMA) {
			throw new IllegalArgumentException(QUANTIDADE_MINIMA_MENSAGEM);
		}
	}

	public BigDecimal getPreço() {
		return preço;
	}

	public void setPreço(BigDecimal preço) {
		this.validaPreçoIncorreto(preço);
		this.preço = preço;
	}

	private void validaPreçoIncorreto(BigDecimal preço) {
		if (preço.doubleValue() < PREÇO_MINIMO) {
			throw new IllegalArgumentException(PRECO_MINIMO_MENSAGEM);
		}
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

}