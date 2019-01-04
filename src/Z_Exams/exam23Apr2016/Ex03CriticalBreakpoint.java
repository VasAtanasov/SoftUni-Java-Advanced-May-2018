package Z_Exams.exam23Apr2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Ex03CriticalBreakpoint {
    private static BufferedReader reader;
    private static List<BigInteger> criticalRatios;
    private static List<BigInteger[]> lines;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        criticalRatios = new ArrayList<>();
        lines = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {
        String input;
        while (! "Break it.".equals(input = reader.readLine())) {
            BigInteger[] coordinates = Arrays.stream(input.split("\\s+"))
                    .map(BigInteger::new)
                    .toArray(BigInteger[]::new);
            BigInteger currentRatio = lineValue.apply(coordinates);
            if (currentRatio.compareTo(BigInteger.ZERO) != 0) {
                criticalRatios.add(currentRatio);
            }
            lines.add(coordinates);
        }

        if (criticalRatios.stream().distinct().count() == 1) {
            StringBuilder output = new StringBuilder();
            lines.forEach(line -> output.append(String.format("Line: %s%n", Arrays.toString(line))));
            BigInteger criticalBreakpoint = getCriticalPoint.apply(criticalRatios.get(0), lines.size());
            output.append(String.format("Critical Breakpoint: %d", criticalBreakpoint));
            System.out.println(output.toString());
        } else {
            System.out.println("Critical breakpoint does not exist.");
        }
    }

    private static Function<BigInteger[], BigInteger> lineValue = values -> ((values[0].add(values[1])).subtract(values[2].add(values[3]))).abs();
    private static BiFunction<BigInteger, Integer, BigInteger> getCriticalPoint = (ratio, linesCount) -> (ratio.pow(linesCount)).mod(BigInteger.valueOf(linesCount));
}
