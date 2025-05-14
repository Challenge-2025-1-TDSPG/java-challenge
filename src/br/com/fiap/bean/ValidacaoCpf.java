package br.com.fiap.bean;

public class ValidacaoCpf{
    public static boolean validarCPF(String cpf) {
        int soma,resto;

        if (cpf == null || cpf.length() != 11) return false;
        try {
            String dec1, dec2;

            // calcular primeiro dígito
            soma = 0;
            for (int i = 0; i < 9; i++) {
                int numero = Integer.parseInt(cpf.substring(i, i + 1));
                soma += numero * (10 - i);
            }
            resto = soma % 11;
            if(resto < 2){
                dec1="0";
            }else {
                dec1 = String.valueOf(11-resto);
            }

            // calcular segundo dígito
            soma = 0;
            for (int i = 0; i < 9; i++) {
                int numero = Integer.parseInt(cpf.substring(i, i + 1));
                soma += numero * (11 - i);
            }
            soma += Integer.parseInt(dec1) * 2;
            resto = soma % 11;
            if (resto < 2){
                dec2="0";
            }else {
                dec2= String.valueOf(11-resto);
            }

            // monta cpf
            String cpfCalculado = cpf.substring(0, 9) + dec1 + dec2;
            return cpf.equals(cpfCalculado);

        } catch (Exception e) {
            return false;
        }
    }


}



