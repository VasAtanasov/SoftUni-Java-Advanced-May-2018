package L07SetsAndMaps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Ex04CountSameValuesInArray {
    private static BufferedReader reader;
    private static Map<String, Integer> values;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        values = new HashMap<>();
    }

    public static void main(String[] args) throws IOException {
        Arrays.stream(reader.readLine().split("\\s+"))
                .forEach(number -> {
                    values.putIfAbsent(number, 0);
                    values.put(number, values.get(number) + 1);
                });

        values.forEach((v, c) -> System.out.println(String.format("%s - %s times", v, c)));
    }
}
