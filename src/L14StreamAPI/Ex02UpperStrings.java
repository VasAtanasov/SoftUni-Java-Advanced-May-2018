package L14StreamAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Ex02UpperStrings {
    private static BufferedReader reader;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        System.out.println(Arrays.stream(reader.readLine().split("\\s+"))
                .map(String::toUpperCase)
                .collect(Collectors.joining(" ")));
    }
}
