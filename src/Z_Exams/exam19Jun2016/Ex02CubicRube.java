package Z_Exams.exam19Jun2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Ex02CubicRube {
    private static BufferedReader reader;
    private static int size, intactCellsCount;
    private static long sum;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        sum = 0;
    }

    public static void main(String[] args) throws IOException {
        getSizeAndCellCount();

        String input;
        while (! "Analyze".equals(input = reader.readLine())) {
            int[] parameters = Arrays.stream(input.split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int x = parameters[0];
            int y = parameters[1];
            int z = parameters[2];
            long value = parameters[3];

            if (validCell(x, y, z) && value != 0) {
                sum += value;
                intactCellsCount--;
            }
        }

        System.out.println(sum);
        System.out.println(intactCellsCount);
    }

    private static boolean validCell(int x, int y, int z) {
        return x >= 0 && x < size &&
                y >= 0 && y < size &&
                z >= 0 && z < size;
    }

    private static void getSizeAndCellCount() throws IOException {
        size = Integer.parseInt(reader.readLine());
        intactCellsCount = size * size * size;
    }
}
