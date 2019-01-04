package Z_Exams.exam29Oct2017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Ex01BitSnow {
    private static final int BITS = 16;
    private static BufferedReader reader;
    private static int[] numbers;
    private static int[][] matrix;
    private static int[] bitsCount;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        readNumbers();
        countSnowFlakes();

        for (int row = matrix.length - 1; row >= 0; row--) {
            for (int col = 0, bit = 0; col < matrix[row].length; col++, bit++) {
                if (bitsCount[bit] != 0) {
                    matrix[row][col] = 1;
                    bitsCount[bit]--;
                }
            }
        }

        convertToDecimal();
        System.out.println(Arrays.toString(numbers).replaceAll("[\\[\\]]", ""));
    }

    private static void convertToDecimal() {
        for (int row = 0; row < numbers.length; row++) {
            int newNumber = Integer.parseInt(getBinaryString(matrix[row]), 2);
            numbers[row] = newNumber;
        }
    }

    private static String getBinaryString(int[] row) {
        StringBuilder binaryString = new StringBuilder();
        for (int col : row) {
            binaryString.append(col);
        }
        return binaryString.toString();
    }

    private static void countSnowFlakes() {
        bitsCount = new int[BITS];
        for (int number : numbers) {
            String[] binary = getBinaryStringArray(number);
            for (int bit = bitsCount.length - 1, stringBit = binary.length - 1; stringBit >= 0; bit--, stringBit--) {
                if ("1".equals(binary[stringBit])) {
                    bitsCount[bit]++;
                }
            }
        }
    }

    private static String[] getBinaryStringArray(int number) {
        return Integer.toBinaryString(number).split("");
    }

    private static void readNumbers() throws IOException {
        String[] tokens = reader.readLine().split(", ");
        numbers = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            numbers[i] = Integer.parseInt(tokens[i]);
        }

        matrix = new int[numbers.length][BITS];
    }
}
