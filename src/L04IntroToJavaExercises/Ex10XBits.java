package L04IntroToJavaExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex10XBits {
    private static BufferedReader reader;
    private static int[] numbers;
    private static int count;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        numbers = new int[8];
        count = 0;
    }

    public static void main(String[] args) throws IOException {
        readInput();

        for (int i = 0; i < numbers.length - 2; i++) {
            int firstNumber = numbers[i];
            int secondNumber = numbers[i + 1];
            int thirdNumber = numbers[i + 2];


            findX(firstNumber, secondNumber, thirdNumber);
        }

        System.out.println(count);

    }

    private static void findX(int firstNumber, int secondNumber, int thirdNumber) {
        for (int i = 28; i >= 0; i--) {
            int firstShift = firstNumber >> i;
            int secondShift = secondNumber >> i;
            int thirdShift = thirdNumber >> i;

            if (isTopOrBottomMatch(firstShift) && isMiddleMatch(secondShift) && isTopOrBottomMatch(thirdShift)) {
                count++;
            }
        }
    }

    private static boolean isMiddleMatch(int number) {
        int firstBit = number & 1;
        int mask = number >> 1;
        int secondBit = mask & 1;
        mask = number >> 2;
        int thirdBit = mask & 1;
        return firstBit == 0 && secondBit == 1 && thirdBit == 0;
    }

    private static boolean isTopOrBottomMatch(int number) {
        int firstBit = number & 1;
        int mask = number >> 1;
        int secondBit = mask & 1;
        mask = number >> 2;
        int thirdBit = mask & 1;
        return firstBit == 1 && secondBit == 0 && thirdBit == 1;
    }

    private static void readInput() throws IOException {
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(reader.readLine());
        }
    }
}
