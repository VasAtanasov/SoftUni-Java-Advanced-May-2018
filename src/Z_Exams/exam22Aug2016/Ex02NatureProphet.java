package Z_Exams.exam22Aug2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Ex02NatureProphet {
    private static BufferedReader reader;
    private static int[][] matrix;
    private static int rows, cols;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        gerDimension();

        String input;
        while (! "Bloom Bloom Plow".equals(input = reader.readLine())) {
            String[] tokens = input.split("\\s+");
            int flowerRow = Integer.parseInt(tokens[0]);
            int flowerCol = Integer.parseInt(tokens[1]);

            for (int col = 0; col < cols; col++) {
                matrix[flowerRow][col]++;
            }

            for (int row = 0; row < rows; row++) {
                matrix[row][flowerCol]++;
            }

            matrix[flowerRow][flowerCol]--;
        }

        System.out.println(getResultString());
    }

    private static String getResultString() {
        StringBuilder output = new StringBuilder();
        for (int[] row : matrix) {
            output.append(Arrays.toString(row).replaceAll("[\\[\\],]", ""))
                    .append(System.lineSeparator());
        }
        return output.toString();
    }

    private static void gerDimension() throws IOException {
        String[] dimension = reader.readLine().split("\\s+");
        rows = Integer.parseInt(dimension[0]);
        cols = Integer.parseInt(dimension[1]);
        matrix = new int[rows][cols];
    }
}
