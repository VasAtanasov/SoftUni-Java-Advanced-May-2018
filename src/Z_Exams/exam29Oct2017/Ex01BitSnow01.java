package Z_Exams.exam29Oct2017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex01BitSnow01 {
    private static final int BITS = 16;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] input = reader.readLine().split(",\\s+");
            int[] numbers = new int[input.length];
            for (int j = 0; j < input.length; j++) {
                numbers[j] = Integer.parseInt(input[j]);
            }

            int[] masks = new int[BITS];
            int[] countBits = new int[BITS];
            for (int bit = 0; bit < BITS; bit++) {
                masks[bit] = 1 << bit;
            }

            for (int number1 : numbers) {
                for (int bit = 0; bit < BITS; bit++) {
                    if ((number1 & masks[bit]) != 0) {
                        countBits[bit]++;
                    }
                }
            }

            for (int num = numbers.length - 1; num >= 0; num--) {
                int number = 0;
                for (int bit = 0; bit < BITS; bit++) {
                    if (countBits[bit] > 0) {
                        number |= masks[bit];
                        countBits[bit]--;
                    }
                }
                numbers[num] = number;
            }

            StringBuilder sb = new StringBuilder();
            for (int number : numbers) {
                sb.append(number).append(", ");
            }
            sb.delete(sb.length() - 2, sb.length());
            System.out.println(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}