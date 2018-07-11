package L03IntroToJava;

import java.math.BigDecimal;
import java.util.Scanner;

public class Ex03EuroTrip {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double kg = scanner.nextDouble();
        BigDecimal inLeva = new BigDecimal(kg * 1.2);
        BigDecimal marks = new BigDecimal("4210500000000.0");

        System.out.println(String.format("%.2f marks",inLeva.multiply(marks)));
    }
}
