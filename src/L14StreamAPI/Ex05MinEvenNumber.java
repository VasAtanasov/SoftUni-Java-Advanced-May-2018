package L14StreamAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.OptionalDouble;

public class Ex05MinEvenNumber {
    private static BufferedReader reader;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        OptionalDouble min = Arrays.stream(reader.readLine().split("\\s+"))
                .filter(s -> ! s.isEmpty())
                .mapToDouble(Double::parseDouble)
                .filter(n -> n % 2 == 0)
                .min();

        if (min.isPresent()) {
            System.out.println(String.format("%.2f", min.getAsDouble()));
        } else {
            System.out.println("No match");
        }

    }
}
