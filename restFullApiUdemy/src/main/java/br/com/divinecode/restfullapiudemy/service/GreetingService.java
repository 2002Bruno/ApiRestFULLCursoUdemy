package br.com.divinecode.restfullapiudemy.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    //Converte os valores para Double
    public Double convertToDouble(String strNumber) {
        if (strNumber == null) return 0D;
        // BR 10,25 US 10,25
        String number = strNumber.replaceAll(",", ".");
        if (isNumeric(number)) return Double.parseDouble(number);
        return 0D;
    }

    //Faz a verificação dos valores para ver se é numpérico
    public boolean isNumeric(String strNumber) {
        if (strNumber == null) return false;
        String number = strNumber.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

    public double getRaiz(Double num1) {
        double raiz = Math.sqrt(num1);
        return raiz;
    }

    public Double getAverage(String numberOne, String numberTwo, String numberThree, String numberFour) {
        // Converte os valores para Double
        Double num1 = convertToDouble(numberOne);
        Double num2 = convertToDouble(numberTwo);
        Double num3 = convertToDouble(numberThree);
        Double num4 = convertToDouble(numberFour);

        // Calcula a soma
        Double sum = num1 + num2 + num3 + num4;

        // Calcula a média
        Double average = sum / 4;
        return average;
    }
}
