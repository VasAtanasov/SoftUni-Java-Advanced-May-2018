package Z_Exams.exam08Feb2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex03LegoBlocks {
    private static BufferedReader reader;
    private static String[][] firstMatrix;
    private static String[][] secondMatrix;
    private static int maxLength;
    private static int elementsCount;
    private static int rows;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        maxLength = 0;
        elementsCount = 0;
    }

    public static void main(String[] args) throws IOException {
        getRows();
        getFirstMatrix();
        getSecondMatrix();

        maxLength = firstMatrix[0].length + secondMatrix[0].length;
        for (int row = 1; row < rows; row++) {
            int currentLength = firstMatrix[row].length + secondMatrix[row].length;
            if (currentLength != maxLength) {
                System.out.println(String.format("The total number of cells is: %d", elementsCount));
                return;
            }
        }

        for (int row = 0; row < rows; row++) {
            StringBuilder output = new StringBuilder();
            output.append("[");
            for (int col = 0; col < firstMatrix[row].length; col++) {
                output.append(firstMatrix[row][col]);
                output.append(", ");
            }
            for (int col = 0; col < secondMatrix[row].length; col++) {
                output.append(secondMatrix[row][col]);
                if (col < secondMatrix[row].length - 1) {
                    output.append(", ");
                }
            }
            output.append("]");
            System.out.println(output.toString());
        }

    }

    private static void getFirstMatrix() throws IOException {
        for (int row = 0; row < rows; row++) {
            String[] inputRow = reader.readLine().trim().split("\\s+");
            firstMatrix[row] = new String[inputRow.length];
            for (int col = 0; col < inputRow.length; col++) {
                firstMatrix[row][col] = inputRow[col];
                elementsCount++;
            }
        }
    }

    private static void getSecondMatrix() throws IOException {
        for (int row = 0; row < rows; row++) {
            String[] inputRow = reader.readLine().trim().split("\\s+");
            secondMatrix[row] = new String[inputRow.length];
            for (int col = 0, index = inputRow.length - 1; col < inputRow.length && index >= 0; col++, index--) {
                secondMatrix[row][col] = inputRow[index];
                elementsCount++;
            }
        }
    }

    private static void getRows() throws IOException {
        rows = Integer.parseInt(reader.readLine());
        firstMatrix = new String[rows][];
        secondMatrix = new String[rows][];
    }
}
