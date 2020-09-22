package br.com.contmatic.util;

import java.util.InputMismatchException;

public final class Validate {
	
	private Validate() {
	}

	public static boolean isCPF(String CPF) {
		return validaCpf(CPF);
	}
	
	public static boolean isNotCPF(String CPF) {
		return !isCPF(CPF);
	}

	private static boolean validaCpf(String CPF) {
		if (verificaCpfIncorreto(CPF)) {
			return false;
		}
		return realizaCalculoDoDigitoVerificadorDoCpf(CPF);
	}

	private static boolean verificaCpfIncorreto(String CPF) {
		return CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222")
				|| CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555")
				|| CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888")
				|| CPF.equals("99999999999") || (CPF.length() != 11);
	}

	private static boolean realizaCalculoDoDigitoVerificadorDoCpf(String CPF) {
		try {
			char dig10 = calculoDosDigitosVerificadoresDoCpf(CPF, 10);
			char dig11 = calculoDosDigitosVerificadoresDoCpf(CPF, 11);
			return verificaPosicaoDosDigitosDoCpf(CPF, dig10, dig11);
		} catch (InputMismatchException erro) {
			return (false);
		}
	}

	private static char calculoDosDigitosVerificadoresDoCpf(String CPF, int peso) {
		int soma = realizaContaDoDigitoVerificadorDoCpf(CPF, peso);
		return verificaResultadoDaContaDoDigitoVerificadorDoCpf(soma);
	}

	private static int realizaContaDoDigitoVerificadorDoCpf(String CPF, int peso) {
		int soma = 0;
		int loop = peso - 1;
		for (int i = 0; i < loop; i++) {
			int num = (int) (CPF.charAt(i) - 48);
			soma = soma + (num * peso);
			peso = peso - 1;
		}
		return soma;
	}

	private static char verificaResultadoDaContaDoDigitoVerificadorDoCpf(int soma) {
		int resultado = 11 - (soma % 11);
		char dig;
		if ((resultado == 10) || (resultado == 11)) {
			dig = '0';
		} else {
			dig = (char) (resultado + 48);
		}
		return dig;
	}

	private static boolean verificaPosicaoDosDigitosDoCpf(String CPF, char dig10, char dig11) {
		if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isCNPJ(String CNPJ) {
		return validaCnpj(CNPJ);
	}
	
	public static boolean isNotCNPJ(String CPF) {
		return !isCNPJ(CPF);
	}

	private static boolean validaCnpj(String CNPJ) {
		if (verificaCnpjIncorreto(CNPJ)) {
			return false;
		}
		return realizaCalculoDoDigitoVerificadorDoCnpj(CNPJ);
	}

	private static boolean verificaCnpjIncorreto(String CNPJ) {
		return CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111") || CNPJ.equals("22222222222222")
				|| CNPJ.equals("33333333333333") || CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555")
				|| CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777") || CNPJ.equals("88888888888888")
				|| CNPJ.equals("99999999999999") || (CNPJ.length() != 14);
	}

	private static boolean realizaCalculoDoDigitoVerificadorDoCnpj(String CNPJ) {
		try {
			char dig13 = calculoDosDigitosVerificadoresDoCnpj(CNPJ, 13);
			char dig14 = calculoDosDigitosVerificadoresDoCnpj(CNPJ, 14);
			return verificaPosicaoDosDigitosDoCnpj(CNPJ, dig13, dig14);
		} catch (InputMismatchException erro) {
			return (false);
		}
	}

	private static char calculoDosDigitosVerificadoresDoCnpj(String CNPJ, int dig) {
		int soma = realizaContaDoDigitoVerificadorDoCnpj(CNPJ, dig);
		return verificaResultadoDaContaDoDigitoVerificadorDoCnpj(soma);
	}

	private static int realizaContaDoDigitoVerificadorDoCnpj(String CNPJ, int dig) {
		int soma = 0;
		int peso = 2;
		for (int i = dig - 2; i >= 0; i--) {
			int num = (int) (CNPJ.charAt(i) - 48);
			soma = soma + (num * peso);
			peso = peso + 1;
			if (peso == 10) {
				peso = 2;
			}
		}
		return soma;
	}

	private static char verificaResultadoDaContaDoDigitoVerificadorDoCnpj(int soma) {
		char dig;
		int resultado = soma % 11;
		if ((resultado == 0) || (resultado == 1)) {
			dig = '0';
		} else {
			dig = (char) ((11 - resultado) + 48);
		}
		return dig;
	}

	private static boolean verificaPosicaoDosDigitosDoCnpj(String CNPJ, char dig13, char dig14) {
		if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13))) {
			return true;
		} else {
			return false;
		}
	}
}