package L05LinearDataStructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex03SumMatrixElements {
    private static BufferedReader reader;
    private static int rows;
    private static int cols;
    private static int sum;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        sum = 0;
    }

    public static void main(String[] args) throws IOException {
        readMatrixData();
        System.out.println(rows);
        System.out.println(cols);
        System.out.println(sum);
    }

    private static void readMatrixData() throws IOException {
        String[] tokens = reader.readLine().split(", ");
        rows = Integer.parseInt(tokens[0]);
        cols = Integer.parseInt(tokens[1]);
        readMatrix();
    }

    private static void readMatrix() throws IOException {
        for (int row = 0; row < rows; row++) {
            String[] rowInput = reader.readLine().split(", ");
            for (int col = 0, i = 0; i < cols && col < cols; col++, i++) {
                int number = Integer.parseInt(rowInput[i]);
                sum += number;
            }
        }
    }
}
