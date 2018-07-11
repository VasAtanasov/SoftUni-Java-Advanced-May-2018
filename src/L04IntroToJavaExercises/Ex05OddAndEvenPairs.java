package L04IntroToJavaExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Ex05OddAndEvenPairs {
    private static BufferedReader reader;
    private static int[] numbers;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        String[] input = reader.readLine().split("\\s+");
        if (input.length % 2 != 0) {
            System.out.println("invalid length");
            return;
        }
        parseInput(input);

        for (int i = 0; i < numbers.length; i += 2) {
            int firstNumber = numbers[i];
            int secondNumber = numbers[i + 1];
            String output = "different";
            if (areEven(firstNumber, secondNumber)) {
                output = "both are even";
            } else if (areOdd(firstNumber, secondNumber)) {
                output = "both are odd";
            }

            System.out.println(String.format("%d, %d -> %s", firstNumber, secondNumber, output));
        }

    }

    private static boolean areOdd(int firstNumber, int secondNumber) {
        return firstNumber % 2 != 0 && secondNumber % 2 != 0;
    }

    private static boolean areEven(int firstNumber, int secondNumber) {
        return firstNumber % 2 == 0 && secondNumber % 2 == 0;
    }

    private static void parseInput(String[] input) throws IOException {
        numbers = Arrays.stream(input)
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
