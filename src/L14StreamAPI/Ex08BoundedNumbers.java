package L14StreamAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Ex08BoundedNumbers {
    private static BufferedReader reader;
    private static int lowerBound, upperBound;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        getBounds();

        System.out.println(Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .filter(value -> value >= lowerBound && value <= upperBound)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" ")));
    }

    private static void getBounds() throws IOException {
        int[] bound = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();
        lowerBound = bound[0];
        upperBound = bound[1];
    }
}
