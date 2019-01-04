package Z_Exams.exam19Feb2017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Ex01ShockWave {
    private static BufferedReader reader;
    private static int[][] matrix;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        getRoomDimensions();

        String input;
        while (! "Here We Go".equals(input = reader.readLine())) {
            String[] tokens = input.split("\\s+");
            int X1 = Integer.parseInt(tokens[0]);
            int Y1 = Integer.parseInt(tokens[1]);
            int X2 = Integer.parseInt(tokens[2]);
            int Y2 = Integer.parseInt(tokens[3]);

            for (int row = X1; row <= X2; row++) {
                for (int col = Y1; col <= Y2; col++) {
                    matrix[row][col]++;
                }
            }
        }

        System.out.print(result());
    }

    private static String result() {
        StringBuilder output = new StringBuilder();
        for (int[] row : matrix) {
            output.append(Arrays.toString(row).replaceAll("[\\[\\],]", ""));
            output.append(System.lineSeparator());
        }
        return output.toString();
    }

    private static void getRoomDimensions() throws IOException {
        String[] size = reader.readLine().split("\\s+");
        int rows = Integer.parseInt(size[0]);
        int cols = Integer.parseInt(size[1]);
        matrix = new int[rows][cols];
    }
}

