package br.com.fillms.projeto_java_ia.utils;

import java.util.InputMismatchException;

public class CPFValidator {
    public static boolean isCPF(String CPF) {
        if (CPF == null || CPF.length() != 11 || CPF.matches("(\\d)\\1{10}")) {
            return false;
        }

        String dig10, dig11;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                num = CPF.charAt(i) - '0';
                sm += (num * peso);
                peso--;
            }

            r = 11 - (sm % 11);
            dig10 = (r == 10 || r == 11) ? "0" : String.valueOf(r);

            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = CPF.charAt(i) - '0';
                sm += (num * peso);
                peso--;
            }

            r = 11 - (sm % 11);
            dig11 = (r == 10 || r == 11) ? "0" : String.valueOf(r);

            return dig10.equals(String.valueOf(CPF.charAt(9))) &&
                   dig11.equals(String.valueOf(CPF.charAt(10)));

        } catch (InputMismatchException erro) {
            return false;
        }
    }
}
