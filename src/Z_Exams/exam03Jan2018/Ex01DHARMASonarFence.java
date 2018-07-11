package Z_Exams.exam03Jan2018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Ex01DHARMASonarFence {
    private static BufferedReader reader;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        String input;
        while (!"Reprogram".equals(input = reader.readLine())) {
            int number = Integer.parseInt(input);
            String binaryString = String.format("%32s", Integer.toBinaryString(number)).replace(" ", "0");
            int[] binary = Arrays.stream(binaryString.split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            for (int i = 0; i < binary.length - 1; i++) {
                int firstBit = binary[i];
                int secondBit = binary[i + 1];

                if (firstBit == secondBit) {
                    firstBit = firstBit == 1 ? 0 : 1;
                    secondBit = secondBit == 1 ? 0 : 1;

                    binary[i] = firstBit;
                    binary[i + 1] = secondBit;
                    i++;
                }
            }

            System.out.println(new BigInteger(
                    Arrays.stream(binary)
                            .mapToObj(String::valueOf)
                            .collect(Collectors.joining("")),
                    2));
        }

    }
}
