package L04IntroToJavaExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Ex08GetFirstOddOrEvenElements {
    private static BufferedReader reader;
    private static int[] numbers;
    private static int count;
    private static int type;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        getInput();

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < numbers.length && count > 0; i++) {
            if (Math.abs(numbers[i]) % 2 == type) {
                output.append(numbers[i]).append(" ");
                count--;
            }
        }

        System.out.println(output.toString().trim());
    }

    private static void getInput() throws IOException {
        numbers = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        String[] tokens = reader.readLine().split("\\s+");
        count = Integer.parseInt(tokens[1]);
        type = "even".equals(tokens[2]) ? 0 : 1;
    }
}
