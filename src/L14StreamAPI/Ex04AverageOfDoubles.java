package L14StreamAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.OptionalDouble;

public class Ex04AverageOfDoubles {
    private static BufferedReader reader;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        OptionalDouble average = Arrays.stream(reader.readLine().split("\\s+"))
                .filter(s -> !s.isEmpty())
                .mapToDouble(Double::parseDouble)
                .average();

        if (average.isPresent()) {
            System.out.println(String.format("%.2f",average.getAsDouble()));
        } else {
            System.out.println("No match");
        }
    }

}
