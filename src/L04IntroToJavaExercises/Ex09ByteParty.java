package L04IntroToJavaExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Ex09ByteParty {
    private static BufferedReader reader;
    private static int[] numbers;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        getInput();

        String input;
        while (! "party over".equals(input = reader.readLine())) {
            int[] tokens = Arrays.stream(input.split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int command = tokens[0];
            int position = tokens[1];

            switch (command) {
                case - 1:
                    flipBits(position);
                    break;
                case 0:
                    unsetBits(position);
                    break;
                case 1:
                    setBits(position);
                    break;
            }

        }

        Arrays.stream(numbers).forEach(System.out::println);
    }

    private static void setBits(int position) {
        for (int i = 0; i < numbers.length; i++) {
            set(position, i);
        }
    }

    private static void unsetBits(int position) {
        for (int i = 0; i < numbers.length; i++) {
            unset(position, i);
        }
    }

    private static void flipBits(int position) {
        for (int i = 0; i < numbers.length; i++) {
            int shiftedNumber = numbers[i] >> position;
            int bit = shiftedNumber & 1;
            if (bit == 0) {
                set(position, i);
            } else {
                unset(position, i);
            }
        }
    }

    private static void set(int position, int i) {
        int mask = 1 << position;
        numbers[i] = numbers[i] | mask;
    }

    private static void unset(int position, int i) {
        int mask = ~ (1 << position);
        numbers[i] = numbers[i] & mask;
    }

    private static void getInput() throws IOException {
        int count = Integer.parseInt(reader.readLine());
        numbers = new int[count];
        for (int i = 0; i < count; i++) {
            numbers[i] = Integer.parseInt(reader.readLine());
        }
    }
}
