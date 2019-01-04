package L11StringProcessingExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Ex02SumBigNumbers {
    private static BufferedReader reader;
    private static Deque<Integer> digits;
    private static int remainder;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        digits = new ArrayDeque<>();
        remainder = 0;
    }

    public static void main(String[] args) throws IOException {
        String firstNumber = reader.readLine();
        String secondNumber = reader.readLine();
        int maxLength = Math.max(firstNumber.length(), secondNumber.length());

        for (int i = 0; i < maxLength; i++) {
            int firstNumberDigit = i < firstNumber.length() ? firstNumber.charAt(firstNumber.length() - 1 - i) - 48 : 0;
            int secondNumberDigit = i < secondNumber.length() ? secondNumber.charAt(secondNumber.length() - 1 - i) - 48 : 0;

            int sum = firstNumberDigit + secondNumberDigit + remainder;
            remainder = sum / 10;
            sum = sum % 10;

            digits.addFirst(sum);
        }

        if (remainder > 0) {
            digits.addFirst(remainder);
        }

        while (digits.peekFirst() == 0) {
            digits.removeFirst();
        }

        System.out.println(digits.toString().replaceAll("[\\[\\],\\s]", ""));
    }
}
