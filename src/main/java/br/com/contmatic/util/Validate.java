package br.com.contmatic.util;

public class Validate {

	public boolean validaCaracteresAlphanumericos(String nome) {
		return nome.matches(RegexType.LETRAS);
	}
	
	public boolean validaNumeros(String numero) {
		return numero.matches(RegexType.NUMEROS);
	}

}
