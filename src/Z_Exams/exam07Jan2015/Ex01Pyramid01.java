package Z_Exams.exam07Jan2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Ex01Pyramid01 {
    private static BufferedReader reader;
    private static List<Integer> sequence;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        sequence = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {
        int rows = Integer.valueOf(reader.readLine());
        int[] finalLastNumber =  {Integer.MIN_VALUE};
        for (int i = 0; i < rows; i++) {
            Optional<Integer> input = Arrays.stream(reader.readLine().split("\\s+"))
                    .filter(e ->!e.equals(""))
                    .map(Integer::valueOf).filter(e -> e > finalLastNumber[0])
                    .sorted().findFirst();
            if (input.isPresent()) {
                finalLastNumber[0] = input.get();
                sequence.add(finalLastNumber[0]);
            } else {
                finalLastNumber[0]++;
            }
        }

        System.out.println(sequence.toString().replace("[","").replace("]",""));
    }
}
