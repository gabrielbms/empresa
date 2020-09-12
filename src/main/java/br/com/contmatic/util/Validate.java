package br.com.contmatic.util;

import java.util.InputMismatchException;

public class Validate {

	public static boolean isCPF(String CPF) {
		return validaCpf(CPF);
	}

	public static boolean isCNPJ(String CNPJ) {
		return validaCnpj(CNPJ);
	}
	
	private static boolean validaCpf(String CPF) {
		if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222")
				|| CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555")
				|| CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888")
				|| CPF.equals("99999999999") || (CPF.length() != 11)) {
			return false;
		}
		return realizaCalculoDoDigitoVerificadorDoCpf(CPF);
	}

	private static boolean realizaCalculoDoDigitoVerificadorDoCpf(String CPF) {
		char dig10, dig11;
		int soma, i, resultado, num, peso;

		try {
			soma = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				num = (int) (CPF.charAt(i) - 48);
				soma = soma + (num * peso);
				peso = peso - 1;
			}

			resultado = 11 - (soma % 11);
			if ((resultado == 10) || (resultado == 11))
				dig10 = '0';
			else
				dig10 = (char) (resultado + 48);

			soma = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (CPF.charAt(i) - 48);
				soma = soma + (num * peso);
				peso = peso - 1;
			}

			resultado = 11 - (soma % 11);
			if ((resultado == 10) || (resultado == 11))
				dig11 = '0';
			else
				dig11 = (char) (resultado + 48);

			if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
				return (true);
			else
				return (false);
		} catch (InputMismatchException erro) {
			return (false);
		}
	}

	private static boolean validaCnpj(String CNPJ) {
		if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111") || CNPJ.equals("22222222222222")
				|| CNPJ.equals("33333333333333") || CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555")
				|| CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777") || CNPJ.equals("88888888888888")
				|| CNPJ.equals("99999999999999") || (CNPJ.length() != 14)) {
			return false;
		}
		return realizaCalculoDoDigitoVerificadorDoCnpj(CNPJ);
	}

	private static boolean realizaCalculoDoDigitoVerificadorDoCnpj(String CNPJ) {
		char dig13, dig14;
		int soma, i, resultado, num, peso;

		try {
			soma = 0;
			peso = 2;
			for (i = 11; i >= 0; i--) {
				num = (int) (CNPJ.charAt(i) - 48);
				soma = soma + (num * peso);
				peso = peso + 1;
				if (peso == 10)
					peso = 2;
			}

			resultado = soma % 11;
			if ((resultado == 0) || (resultado == 1))
				dig13 = '0';
			else
				dig13 = (char) ((11 - resultado) + 48);

			soma = 0;
			peso = 2;
			for (i = 12; i >= 0; i--) {
				num = (int) (CNPJ.charAt(i) - 48);
				soma = soma + (num * peso);
				peso = peso + 1;
				if (peso == 10)
					peso = 2;
			}

			resultado = soma % 11;
			if ((resultado == 0) || (resultado == 1))
				dig14 = '0';
			else
				dig14 = (char) ((11 - resultado) + 48);

			if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13)))
				return (true);
			else
				return (false);
		} catch (InputMismatchException erro) {
			return (false);
		}
	}

}
