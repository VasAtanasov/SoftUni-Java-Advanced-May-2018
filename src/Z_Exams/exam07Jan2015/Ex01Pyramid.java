package Z_Exams.exam07Jan2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Ex01Pyramid {
    private static BufferedReader reader;
    private static int rows;
    private static int[][] pyramid;
    private static List<Integer> elements = new ArrayList<>();
    private static int searchingFor;
    private static int closestNumber;
    private static int smallestDifference;
    private static boolean isNumber = false;


    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        smallestDifference = Integer.MAX_VALUE;
    }

    public static void main(String[] args) throws IOException {
        getRows();
        getPyramid();
        elements.add(pyramid[0][0]);
        searchingFor = pyramid[0][0];

        for (int row = 1; row < rows; row++) {
            for (int col = 0; col < pyramid[row].length; col++) {
                int element = pyramid[row][col];
                int diff = Math.abs(element - searchingFor);

                if (element > searchingFor && diff < smallestDifference) {
                    smallestDifference = diff;
                    closestNumber = element;
                    isNumber = true;
                }
            }
            if (isNumber) {
                elements.add(closestNumber);
                searchingFor = closestNumber;
                smallestDifference = Integer.MAX_VALUE;
                isNumber = false;
            } else {
                searchingFor++;
            }
        }

        System.out.println(elements.toString().replaceAll("[\\[\\]]",""));

    }

    private static void getPyramid() throws IOException {
        for (int row = 0; row < rows; row++) {
            String[] inputRow = reader.readLine().trim().split("\\s+");
            pyramid[row] = new int[inputRow.length];
            for (int col = 0; col < inputRow.length; col++) {
                pyramid[row][col] = Integer.parseInt(inputRow[col]);
            }
        }
    }

    private static void getRows() throws IOException {
        rows = Integer.parseInt(reader.readLine());
        pyramid = new int[rows][];
    }
}
