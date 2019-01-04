package L11StringProcessingExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Ex02SumBigNumbers01 {
    private static BufferedReader reader;
    private static int[] sum;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        int[] numberOne = Arrays.stream(reader.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        int[] numberTwo = Arrays.stream(reader.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        int maxLength = Math.max(numberOne.length, numberTwo.length) + 1;
        sum = new int[maxLength];

        int remainder = 0;
        for (int i = 0; i < maxLength; i++) {
            int digitOne = i < numberOne.length ? numberOne[numberOne.length - 1 - i] : 0;
            int digitTwo = i < numberTwo.length ? numberTwo[numberTwo.length - 1 - i] : 0;
            int sum = digitOne + digitTwo + remainder;
            remainder = sum / 10;
            Ex02SumBigNumbers01.sum[maxLength - 1 - i] = (sum % 10);
        }


        String number = removeLeadingZeros(Arrays.toString(sum).replaceAll("[\\[\\],\\s]", ""));
        System.out.println(number);

    }

    private static String removeLeadingZeros(String sum) {
        StringBuilder remove = new StringBuilder(sum);

        while (remove.charAt(0) == '0') {
            remove.delete(0,1);
        }
        return remove.toString();
    }
}

