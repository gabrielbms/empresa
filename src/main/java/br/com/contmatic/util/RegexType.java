package br.com.contmatic.util;

import static br.com.contmatic.util.Constantes.ACEITA_CARACTERES_ESPECIAIS;
import static br.com.contmatic.util.Constantes.ACEITA_ESPACO_NO_INICIO_OU_NO_FIM;
import static br.com.contmatic.util.Constantes.ACEITA_LETRAS;
import static br.com.contmatic.util.Constantes.ACEITA_MAIS_QUE_UM_ESPACO_ENTRE_AS_PALAVRAS;
import static br.com.contmatic.util.Constantes.ACEITA_NUMEROS;
import static br.com.contmatic.util.Constantes.NAO_ACEITA_CARACTERES_ESPECIAIS;
import static br.com.contmatic.util.Constantes.NAO_ACEITA_ESPACO_NO_INICIO_OU_NO_FIM;
import static br.com.contmatic.util.Constantes.NAO_ACEITA_LETRAS;
import static br.com.contmatic.util.Constantes.NAO_ACEITA_MAIS_QUE_UM_ESPACO_ENTRE_AS_PALAVRAS;
import static java.util.regex.Pattern.compile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RegexType {

	public static final String LETRAS = "[A-Za-záÁ-úÚÇÑ \\s]{2,15}$";

	public static final String NUMEROS = "[0-9]";

	public static final String LETRAS_E_NUMEROS = "[A-Za-záÁ-úÚÇÑ0-9_ '\\\\s]+$";

	public static final String ESPACOS_DUPLICADOS = "\\s\\s+";

	public static final String CARACTERES_ESPECIAIS = "[!@#$%¨&*()_+=|`´^~\\/{}]";

	private RegexType() {
	}
	
	public static boolean isLetras(String string) {
		try {
			verificaSeTemLetras(string);
			verificaSeNaoTemNumeros(string);
			verificaSeNaoTemCaracteresEspeciais(string);
			verificaSeNaoTemEspacosNoInicioOuFim(string);
			verificaSeNaoTemMaisQueUmEspacoEntreAsPalavras(string);
		} catch (IllegalArgumentException e) {
			throw e;
		} 
		return true;
	}
	
	public static boolean isNotLetras(String string) {
		return !isLetras(string);
	}
	
	public static boolean isNumeros(String string) {
		try {
			verificaSeTemNumeros(string);
			verificaSeNaoTemLetras(string);
			verificaSeNaoTemCaracteresEspeciais(string);
			verificaSeNaoTemEspacosNoInicioOuFim(string);
			verificaSeNaoTemMaisQueUmEspacoEntreAsPalavras(string);
		} catch (IllegalArgumentException e) {
			throw e;
		} 
		return true;
	}
	
	public static boolean isNotNumeros(String string) {
		return !isNumeros(string);
	}
	
	
	public static boolean isLetrasENumeros(String string) {
		try {
			verificaSeNaoTemCaracteresEspeciais(string);
			verificaSeNaoTemEspacosNoInicioOuFim(string);
			verificaSeNaoTemMaisQueUmEspacoEntreAsPalavras(string);
		} catch (IllegalArgumentException e) {
			throw e;
		} 
		return true;
	}
	
	public static boolean isNotLetrasENumeros(String string) {
		return !isLetrasENumeros(string);
	}

	public static void verificaSeTemLetras(String string) {
		if (!contemLetras(string)) {
			throw new IllegalArgumentException(ACEITA_LETRAS);
		}
	}
	
	public static void verificaSeNaoTemLetras(String string) {
		if (contemLetras(string)) {
			throw new IllegalArgumentException(NAO_ACEITA_LETRAS);
		}
	}

	private static boolean contemLetras(String string) {
		Pattern regexLetras = compile(LETRAS);
		Matcher matcherLetras = regexLetras.matcher(string);
		return matcherLetras.find();
	}

	public static void verificaSeTemNumeros(String string) {
		if (!contemNumeros(string)) {
			throw new IllegalArgumentException(ACEITA_NUMEROS);
		}
	}
	
	public static void verificaSeNaoTemNumeros(String string) {
		if (contemNumeros(string)) {
			throw new IllegalArgumentException(NAO_ACEITA_LETRAS);
		}
	}

	private static boolean contemNumeros(String string) {
		Pattern regexNumeros = Pattern.compile(NUMEROS);
		Matcher matcherNumeros = regexNumeros.matcher(string);
		return matcherNumeros.find();
	}
	
	public static void verificaSeTemCaracteresEspeciais(String string) {
		if (!contemCaracteresEspeciais(string)) {
			throw new IllegalArgumentException(ACEITA_CARACTERES_ESPECIAIS);
		}
	}
	
	public static void verificaSeNaoTemCaracteresEspeciais(String string) {
		if (contemCaracteresEspeciais(string)) {
			throw new IllegalArgumentException(NAO_ACEITA_CARACTERES_ESPECIAIS);
		}
	}

	public static boolean contemCaracteresEspeciais(String string) {
		Pattern regexCaracteresEspeciais = Pattern.compile(CARACTERES_ESPECIAIS);
		Matcher matcherCaracteresEspeciais = regexCaracteresEspeciais.matcher(string);
		return matcherCaracteresEspeciais.find();
	}
	
	public static void verificaSeTemEspacosNoInicioOuFim(String string) {
		if (!contemEspacosNoInicioOuFim(string)) {
			throw new IllegalArgumentException(ACEITA_ESPACO_NO_INICIO_OU_NO_FIM);
		}
	}
	
	public static void verificaSeNaoTemEspacosNoInicioOuFim(String string) {
		if (contemEspacosNoInicioOuFim(string)) {
			throw new IllegalArgumentException(NAO_ACEITA_ESPACO_NO_INICIO_OU_NO_FIM);
		}
	}

	private static boolean contemEspacosNoInicioOuFim(String string) {
		String stringSemTrim = string;
		String stringComTrim = string.trim();
		return !stringSemTrim.equals(stringComTrim);
	}
	
	public static void verificaSeTemMaisQueUmEspacoEntreAsPalavras(String string) {
		if (!ContemMaisQueUmEspacoEntreAsPalavras(string)) {
			throw new IllegalArgumentException(ACEITA_MAIS_QUE_UM_ESPACO_ENTRE_AS_PALAVRAS);
		}
	}
	
	public static void verificaSeNaoTemMaisQueUmEspacoEntreAsPalavras(String string) {
		if (ContemMaisQueUmEspacoEntreAsPalavras(string)) {
			throw new IllegalArgumentException(NAO_ACEITA_MAIS_QUE_UM_ESPACO_ENTRE_AS_PALAVRAS);
		}
	}

	public static boolean ContemMaisQueUmEspacoEntreAsPalavras(String string) {
		String patternStr = ESPACOS_DUPLICADOS;
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(string);
		return matcher.find();
	}

}