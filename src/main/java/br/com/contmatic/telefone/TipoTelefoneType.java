package br.com.contmatic.telefone;

public enum TipoTelefoneType {
	
	/** The celular. */
	CELULAR("Celular", 9),

	/** The fixo. */
	FIXO("Fixo", 8);

	private String descricao;

	private int tamanho;

	private TipoTelefoneType(String descricao, int tamanho) {
		this.descricao = descricao;
		this.tamanho = tamanho;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public int getTamanho() {
		return this.tamanho;
	}

}
