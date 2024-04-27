package com.consignadocliente.util;

import java.math.BigInteger;

import com.consignadocliente.exception.IllegalArgumentException;

public class CpfUtil {
	
	final static String CPF_FORMAT = "000.000.000-00";
	final static String MSG_ERROR = "Formato de CPF esperado: " + CPF_FORMAT;

	public static void validaCpf(String cpf) throws IllegalArgumentException {
		
		boolean isValid = true;
		
		isValid = (cpf != null && !cpf.isEmpty()) ? true : false;
		isValid = (cpf.length() == 14 && isValid) ? true : false;
		
		if (isValid) {
			isValid = (isValid && cpf.charAt(3) == '.' && cpf.charAt(7) == '.' && cpf.charAt(11) == '-') ? true : false;
			
			cpf = (cpf.substring(0,3) + cpf.substring(4,7) + cpf.substring(8,11) + cpf.substring(12,14));
			
			try {
				new BigInteger(cpf);
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException(MSG_ERROR,null);
			}
		}
		
		if (!isValid) {
			throw new IllegalArgumentException(MSG_ERROR,null);
		}
		
	}
	
}
