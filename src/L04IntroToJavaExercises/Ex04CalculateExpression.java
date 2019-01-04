package L04IntroToJavaExercises;

import java.util.Arrays;
import java.util.Scanner;

public class Ex04CalculateExpression {
    private static Scanner scanner;

    static {
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();

        double powerF1 = (a + b + c) / Math.sqrt(c);
        double expressionF1 = (powOfTwo(a) + powOfTwo(b)) / (powOfTwo(a) - powOfTwo(b));
        double f1 = getFunction(expressionF1, powerF1);

        double powerF2 = (a - b);
        double expressionF2 = (powOfTwo(a) + powOfTwo(b) - powOfThree(c));
        double f2 = getFunction(expressionF2, powerF2);

        double averageOfNumbers = average(a, b, c);
        double averageOfFunctions = average(f1, f2);
        double result = Math.abs(averageOfNumbers - averageOfFunctions);
        System.out.println(String.format("F1 result: %.2f; F2 result: %.2f; Diff: %.2f", f1, f2, result));

    }

    private static double average(double... numbers) {
        return Arrays.stream(numbers)
                .average()
                .orElse(0.0);
    }

    private static double getFunction(double expression, double power) {
        return Math.pow(expression, power);
    }

    private static double powOfTwo(double number) {
        return Math.pow(number, 2);
    }

    private static double powOfThree(double number) {
        return Math.pow(number, 3);
    }
}
