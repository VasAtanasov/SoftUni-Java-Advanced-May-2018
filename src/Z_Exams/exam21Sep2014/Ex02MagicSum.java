package Z_Exams.exam21Sep2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Ex02MagicSum {
    private static BufferedReader reader;
    private static List<Integer> numbers;
    private static String magicSum;
    private static int maxSum;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        numbers = new ArrayList<>();
        maxSum = Integer.MIN_VALUE;
        magicSum = "No";
    }

    public static void main(String[] args) throws IOException {
        int D = Integer.parseInt(reader.readLine());
        getNumbers();

        for (int numOne = 0; numOne < numbers.size(); numOne++) {
            for (int numTwo = numOne + 1; numTwo < numbers.size(); numTwo++) {
                for (int numThree = numTwo + 1; numThree < numbers.size(); numThree++) {
                    int sum = numbers.get(numOne) + numbers.get(numTwo) + numbers.get(numThree);
                    if (sum % D == 0 && sum > maxSum) {
                        maxSum = sum;
                        magicSum = String.format("(%d + %d + %d) %% %d = 0",
                                numbers.get(numOne), numbers.get(numTwo), numbers.get(numThree), D);
                    }
                }
            }
        }

        System.out.println(magicSum);
    }

    private static void getNumbers() throws IOException {
        String input;
        while (! "End".equals(input = reader.readLine())) {
            numbers.add(Integer.parseInt(input));
        }
    }

}
