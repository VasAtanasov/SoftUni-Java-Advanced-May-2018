package L04IntroToJavaExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Ex10XBits01 {
    private static final int COLS;
    private static final int ROWS;
    private static final int[][] x;
    private static BufferedReader reader;
    private static int[][] binary;
    private static int count;

    static {
        COLS = 32;
        ROWS = 8;
        binary = new int[ROWS][COLS];
        reader = new BufferedReader(new InputStreamReader(System.in));
        x = new int[][] {{1, 0, 1}, {0, 1, 0}, {1, 0, 1}};
        count = 0;
    }

    public static void main(String[] args) throws IOException {
        initializeBinaryMatrix();
        countX();
        System.out.println(count);
    }

    private static void countX() {
        for (int row = 0; row < ROWS - 2; row++) {
            for (int col = 0; col < COLS - 2; col++) {
                int[][] makeX = new int[3][3];
                makeX[0][0] = binary[row][col];
                makeX[0][1] = binary[row][col + 1];
                makeX[0][2] = binary[row][col + 2];

                makeX[1][0] = binary[row + 1][col];
                makeX[1][1] = binary[row + 1][col + 1];
                makeX[1][2] = binary[row + 1][col + 2];

                makeX[2][0] = binary[row + 2][col];
                makeX[2][1] = binary[row + 2][col + 1];
                makeX[2][2] = binary[row + 2][col + 2];

                if (Arrays.deepEquals(x, makeX)) {
                    count++;
                }
            }
        }
    }

    private static void initializeBinaryMatrix() throws IOException {
        binary = new int[ROWS][COLS];
        for (int row = 0; row < ROWS; row++) {
            char[] bit = Integer.toBinaryString(Integer.valueOf(reader.readLine())).toCharArray();
            for (int col = COLS - 1, bitIndex = bit.length - 1; col >= 0 && bitIndex >= 0; col--, bitIndex--) {
                binary[row][col] = bit[bitIndex] - 48;
            }
        }
    }

}

