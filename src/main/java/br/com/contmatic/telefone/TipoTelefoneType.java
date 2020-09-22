package br.com.contmatic.telefone;

public enum TipoTelefoneType {

	CELULAR("Celular", 9),

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
