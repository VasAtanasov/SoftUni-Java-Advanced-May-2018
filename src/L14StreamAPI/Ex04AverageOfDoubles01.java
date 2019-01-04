package L14StreamAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.stream.Collectors;

public class Ex04AverageOfDoubles01 {
    private static BufferedReader reader;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        DoubleSummaryStatistics statistic = Arrays.stream(reader.readLine().split("\\s+"))
                .filter(s -> !s.isEmpty())
                .collect(Collectors.summarizingDouble(Double::parseDouble));

        if (statistic.getCount() != 0) {
            System.out.println(String.format("%.2f",statistic.getAverage()));
        } else {
            System.out.println("No match");
        }
    }
}
