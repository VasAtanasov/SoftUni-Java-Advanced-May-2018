package L04IntroToJavaExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex13BlurFilter {
    private static BufferedReader reader;
    private static long[][] matrix;
    private static long weight;
    private static int rows;
    private static int cols;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        readInput();
        addBlur();
        System.out.println(getMatrixString());
    }

    private static String getMatrixString() {
        StringBuilder output = new StringBuilder();
        for (int row = 0; row < rows; row++) {
            StringBuilder rowOutput = new StringBuilder();
            for (int col = 0; col < cols; col++) {
                rowOutput.append(matrix[row][col]).append(" ");
            }
            output.append(rowOutput.toString().trim()).append(System.lineSeparator());
        }
        return output.toString();
    }

    private static void addBlur() throws IOException {
        String[] coordinates = reader.readLine().split("\\s+");
        int targetRow = Integer.parseInt(coordinates[0]) - 1;
        int targetCol = Integer.parseInt(coordinates[1]) - 1;

        int rowStart = Math.max(0, targetRow);
        int rowEnd = Math.min(targetRow + 2, matrix.length - 1);
        int colStart = Math.max(0, targetCol);
        int colEnd = Math.min(targetCol + 2, cols - 1);
        for (int row = rowStart; row <= rowEnd; row++) {
            for (int col = colStart; col <= colEnd; col++) {
                matrix[row][col] += weight;
            }
        }
    }

    private static void readInput() throws IOException {
        weight = Long.parseLong(reader.readLine());
        String[] size = reader.readLine().split("\\s+");
        rows = Integer.parseInt(size[0]);
        cols = Integer.parseInt(size[1]);
        readMatrix();
    }

    private static void readMatrix() throws IOException {
        matrix = new long[rows][cols];
        for (int row = 0; row < rows; row++) {
            String[] inputRow = reader.readLine().split("\\s+");
            for (int col = 0, index = 0; index < cols && col < cols; col++, index++) {
                matrix[row][col] = Integer.parseInt(inputRow[index]);
            }
        }
    }
}
