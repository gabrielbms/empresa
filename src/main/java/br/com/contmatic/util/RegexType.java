package br.com.contmatic.util;

public class RegexType {

	public static final String LETRAS = "[A-Za-záÁ-úÚÇÑ \\s]";

	public static final String NUMEROS = "[0-9]";

	public static final String LETRAS_E_NUMEROS = "^[A-Za-záÁ-úÚÇÑ0-9_ '\\\\s]+$";

	private RegexType() {
	}
	
}