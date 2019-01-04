package L14StreamAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;

public class Ex06FindAndSumIntegers {
    private static BufferedReader reader;
    private static IntSummaryStatistics statistics;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        statistics = Arrays.stream(reader.readLine().split("\\s+"))
                .filter(s -> ! s.isEmpty())
                .filter(s -> s.matches("[-+]?[0-9]+"))
                .collect(Collectors.summarizingInt(Integer::parseInt));

        if (statistics.getCount() == 0) {
            System.out.println("No match");
        } else {
            System.out.println(statistics.getSum());
        }

    }
}
