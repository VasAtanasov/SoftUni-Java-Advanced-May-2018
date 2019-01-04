package L04IntroToJavaExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Ex08GetFirstOddOrEvenElements01 {
    private static BufferedReader reader;
    private static int[] numbers;
    private static int count;
    private static int type;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        getInput();

        System.out.println(Arrays.stream(numbers)
                .filter(e -> Math.abs(e) % 2 == type)
                .limit(count)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" ")));
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
