package Z_Exams.exam07Jan2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Ex03FireTheArrows {
    private static BufferedReader reader;
    private static char[][] matrix;
    private static int rows;
    private static List<Character> moves = Arrays.asList('^', 'v', '>', '<');
    private static boolean isMove = true;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        getRows();
        getMatrix();

        while (isMove) {
            isMove = false;
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < matrix[row].length; col++) {
                    char element = matrix[row][col];
                    int nextRow = row;
                    int nextCol = col;
                    switch (element) {
                        case '^':
                            nextRow -= 1;
                            break;
                        case 'v':
                            nextRow += 1;
                            break;
                        case '<':
                            nextCol -= 1;
                            break;
                        case '>':
                            nextCol += 1;
                            break;
                        default:
                            continue;
                    }

                    if (isPossibleMove(nextRow, nextCol)) {
                        matrix[row][col] = matrix[nextRow][nextCol];
                        matrix[nextRow][nextCol] = element;
                        isMove = true;
                    }
                }
            }
        }

        for (int row = 0; row < rows; row++) {
            System.out.println(Arrays.toString(matrix[row]).replaceAll("[\\[\\],\\s]", ""));
        }


    }

    private static boolean isPossibleMove(int nextRow, int nextCol) {
        return nextRow >= 0 && nextRow < rows &&
                nextCol >= 0 && nextCol < matrix[nextRow].length &&
                ! moves.contains(matrix[nextRow][nextCol]);
    }

    private static void getMatrix() throws IOException {
        for (int row = 0; row < rows; row++) {
            matrix[row] = reader.readLine().toCharArray();
        }
    }

    private static void getRows() throws IOException {
        rows = Integer.parseInt(reader.readLine());
        matrix = new char[rows][];
    }
}
