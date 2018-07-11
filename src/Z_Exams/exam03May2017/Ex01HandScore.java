package Z_Exams.exam03May2017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Ex01HandScore {
    private static final List<String> powers = Arrays.asList("", "", "2", "3", "4", "5", "6", "7", "8", "9", "10", "", "J", "Q", "K", "A");
    private static final List<String> suits = Arrays.asList("", "C", "D", "H", "S");
    private static BufferedReader reader;
    private static int[][] matrix;
    private static int sum;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        sum = 0;
    }

    public static void main(String[] args) throws IOException {
        handOutCards();

        int currentSum = matrix[0][0];
        int multiplier = 1;

        for (int col = 1; col < matrix[0].length; col++) {
            if (matrix[1][col] == matrix[1][col - 1]) {
                currentSum += matrix[0][col];
                multiplier++;
            } else {
                currentSum *= multiplier;
                sum += currentSum;
                multiplier = 1;
                currentSum = matrix[0][col];
            }

            if (col == matrix[0].length - 1) {
                currentSum *= multiplier;
                sum += currentSum;
            }
        }

        System.out.println(sum);

    }

    private static void handOutCards() throws IOException {
        String[] cards = reader.readLine().split("\\s+");
        matrix = new int[2][cards.length];
        for (int col = 0; col < cards.length; col++) {
            String card = cards[col];
            int power = powers.indexOf(card.substring(0, card.length() - 1));
            int suit = suits.indexOf(card.substring(card.length() - 1));
            matrix[0][col] = power;
            matrix[1][col] = suit;
        }
    }
}
