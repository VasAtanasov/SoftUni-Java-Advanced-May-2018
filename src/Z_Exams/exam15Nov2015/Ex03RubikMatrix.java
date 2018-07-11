package Z_Exams.exam15Nov2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex03RubikMatrix {
    private static BufferedReader reader;
    private static int rows, cols;
    private static int[][] matrix;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        getRubikSize();
        fillMatrix();
        shuffleMatrix();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int searchedElement = (row * cols) + col + 1;
                if (searchedElement == matrix[row][col]) {
                    System.out.println("No swap required");
                } else {
                    findElement(row, col, searchedElement);
                }
            }
        }

    }

    private static void findElement(int row, int col, int searchedElement) {
        for (int nestRow = row; nestRow < rows; nestRow++) {
            for (int nextCol = 0; nextCol < cols; nextCol++) {
                if (matrix[nestRow][nextCol] == searchedElement) {
                    matrix[nestRow][nextCol] = matrix[row][col];
                    matrix[row][col] = searchedElement;
                    System.out.println(String.format("Swap (%d, %d) with (%d, %d)", row, col, nestRow, nextCol));
                    break;
                }
            }
        }
    }

    private static void shuffleMatrix() throws IOException {
        int count = Integer.parseInt(reader.readLine());
        for (int i = 0; i < count; i++) {
            String[] tokens = reader.readLine().split("\\s+");
            int rowCol = Integer.parseInt(tokens[0]);
            String direction = tokens[1];
            int times = Integer.parseInt(tokens[2]);

            switch (direction) {
                case "left":
                    shiftRowLeft(rowCol, times % cols);
                    break;
                case "right":
                    shiftRowRight(rowCol, times % cols);
                    break;
                case "up":
                    shiftColUp(rowCol, times % rows);
                    break;
                case "down":
                    shiftColDown(rowCol, times % rows);
                    break;
            }

        }
    }

    private static void shiftColDown(int col, int times) {
        while (times-- > 0) {
            int lastElement = matrix[rows - 1][col];
            for (int row = rows - 1; row >= 1; row--) {
                matrix[row][col] = matrix[row - 1][col];
            }
            matrix[0][col] = lastElement;
        }
    }

    private static void shiftColUp(int col, int times) {
        while (times-- > 0) {
            int firstElement = matrix[0][col];
            for (int row = 0; row < rows - 1; row++) {
                matrix[row][col] = matrix[row + 1][col];
            }
            matrix[rows - 1][col] = firstElement;
        }
    }

    private static void shiftRowRight(int row, int times) {
        while (times-- > 0) {
            int lastElement = matrix[row][cols - 1];
            for (int col = cols - 1; col >= 1; col--) {
                matrix[row][col] = matrix[row][col - 1];
            }
            matrix[row][0] = lastElement;
        }
    }

    private static void shiftRowLeft(int row, int times) {
        while (times-- > 0) {
            int firstElement = matrix[row][0];
            for (int col = 0; col < cols - 1; col++) {
                matrix[row][col] = matrix[row][col + 1];
            }
            matrix[row][cols - 1] = firstElement;
        }
    }

    private static void getRubikSize() throws IOException {
        String[] size = reader.readLine().split("\\s+");
        rows = Integer.parseInt(size[0]);
        cols = Integer.parseInt(size[1]);
    }

    private static void fillMatrix() {
        matrix = new int[rows][cols];
        int filler = 1;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = filler++;
            }
        }
    }

}
