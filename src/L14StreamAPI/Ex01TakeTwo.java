package L14StreamAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Ex01TakeTwo {
    private static BufferedReader reader;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        System.out.println(Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .filter(n -> n >= 10 && n <= 20)
                .distinct()
                .limit(2)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" ")));
    }
}
