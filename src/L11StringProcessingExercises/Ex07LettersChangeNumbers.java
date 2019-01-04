package L11StringProcessingExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex07LettersChangeNumbers {
    private static BufferedReader reader;
    private static double sum;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        sum = 0;
    }

    public static void main(String[] args) throws IOException {
        String[] words = reader.readLine().split("\\s+");

        for (String word : words) {
            char firstChar = word.charAt(0);
            char lastChar = word.charAt(word.length() - 1);
            double number = Double.parseDouble(word.substring(1, word.length() - 1));
            number = getFirstLetterResult(firstChar, number);
            number = getLastLetterResult(lastChar, number);
            sum += number;
        }

        System.out.println(String.format("%.2f", sum));
    }

    private static double getLastLetterResult(char lastChar, double number) {
        int lastLetterPosition;
        if (Character.isUpperCase(lastChar)) {
            lastLetterPosition = lastChar - 64;
            number -= lastLetterPosition;
        } else {
            lastLetterPosition = lastChar - 96;
            number += lastLetterPosition;
        }
        return number;
    }

    private static double getFirstLetterResult(char firstChar, double number) {
        int firstLetterPosition;
        if (Character.isUpperCase(firstChar)) {
            firstLetterPosition = firstChar - 64;
            number /= firstLetterPosition;
        } else {
            firstLetterPosition = firstChar - 96;
            number *= firstLetterPosition;
        }
        return number;
    }
}
