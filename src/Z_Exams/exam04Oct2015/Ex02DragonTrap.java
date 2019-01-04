package Z_Exams.exam04Oct2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ex02DragonTrap {
    private static final String GET = "get";
    private static final String SET = "set";
    private static int rows;
    private static BufferedReader reader;
    private static char[][] matrix;
    private static char[][] originalMatrix;
    private static List<Character> characters;
    private static int index;
    private static int symbolsChanged;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        characters = new ArrayList<>();
        index = 0;
        symbolsChanged = 0;
    }

    public static void main(String[] args) throws IOException {
        readMatrix();

        String input;
        while (! "End".equals(input = reader.readLine())) {
            int[] tokens = Arrays.stream(input.split("[()\\s]+"))
                    .filter(s -> ! s.isEmpty())
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int targetRow = tokens[0];
            int targetCol = tokens[1];
            int radius = tokens[2];
            int times = tokens[3];

            int rowStart = targetRow - radius;
            int rowEnd = targetRow + radius;
            int colStart = targetCol - radius;
            int colEnd = targetCol + radius;

            getUpperRow(rowStart, colStart, colEnd, GET);
            getRightCol(colEnd, rowStart, rowEnd, GET);
            getLowerRow(rowEnd, colStart, colEnd, GET);
            getLeftCol(colStart, rowStart, rowEnd, GET);

            if (characters.size() > 0) {
                if (times >= 0) {
                    shiftRight(times % characters.size());
                } else {
                    shiftLeft(Math.abs(times) % characters.size());
                }

                getUpperRow(rowStart, colStart, colEnd, SET);
                getRightCol(colEnd, rowStart, rowEnd, SET);
                getLowerRow(rowEnd, colStart, colEnd, SET);
                getLeftCol(colStart, rowStart, rowEnd, SET);

                characters = new ArrayList<>();
                index = 0;
            }
        }

        printResult();
    }

    private static void printResult() {
        for (int row = 0; row < rows; row++) {
            StringBuilder output = new StringBuilder();
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] != originalMatrix[row][col]) {
                    symbolsChanged++;
                }
                output.append(matrix[row][col]);
            }
            System.out.println(output.toString());
        }
        System.out.println(String.format("Symbols changed: %d", symbolsChanged));
    }

    private static void shiftLeft(int times) {
        while (times-- > 0) {
            char firstCharacter = characters.get(0);
            for (int i = 0; i < characters.size() - 1; i++) {
                char ch = characters.get(i + 1);
                characters.set(i, ch);
            }
            characters.set(characters.size() - 1, firstCharacter);
        }
    }

    private static void shiftRight(int times) {
        while (times-- > 0) {
            char lastElement = characters.get(characters.size() - 1);
            for (int i = characters.size() - 1; i > 0; i--) {
                char ch = characters.get(i - 1);
                characters.set(i, ch);
            }
            characters.set(0, lastElement);
        }
    }

    private static void getLeftCol(int col, int rowStart, int rowEnd, String command) {
        for (int row = rowEnd - 1; row > rowStart; row--) {
            if (isInRange(row, col)) {
                processElement(row, command, col);
            }
        }
    }

    private static void getLowerRow(int row, int colStart, int colEnd, String command) {
        for (int col = colEnd; col >= colStart; col--) {
            if (isInRange(row, col)) {
                processElement(row, command, col);
            }
        }
    }

    private static void getRightCol(int col, int rowStart, int rowEnd, String command) {
        for (int row = rowStart + 1; row < rowEnd; row++) {
            if (isInRange(row, col)) {
                processElement(row, command, col);
            }
        }
    }

    private static void getUpperRow(int row, int colStart, int colEnd, String command) {
        for (int col = colStart; col <= colEnd; col++) {
            if (isInRange(row, col)) {
                processElement(row, command, col);
            }
        }
    }

    private static void processElement(int row, String command, int col) {
        if (GET.equals(command)) {
            characters.add(matrix[row][col]);
        } else {
            matrix[row][col] = characters.get(index++);
        }
    }

    private static boolean isInRange(int row, int col) {
        return row >= 0 && row < rows &&
                col >= 0 && col < matrix[row].length;
    }

    private static void readMatrix() throws IOException {
        rows = Integer.parseInt(reader.readLine());
        matrix = new char[rows][];
        initializeMatrix();
        initializeOriginalMatrix();
    }

    private static void initializeOriginalMatrix() {
        originalMatrix = new char[rows][];
        for (int row = 0; row < rows; row++) {
            originalMatrix[row] = new char[matrix[row].length];
            System.arraycopy(matrix[row], 0, originalMatrix[row], 0, originalMatrix[row].length);
        }
    }

    private static void initializeMatrix() throws IOException {
        for (int row = 0; row < rows; row++) {
            char[] inputRow = reader.readLine().toCharArray();
            matrix[row] = new char[inputRow.length];
            System.arraycopy(inputRow, 0, matrix[row], 0, inputRow.length);
        }
    }
}
