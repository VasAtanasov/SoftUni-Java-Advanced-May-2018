package Z_Exams.exam08May2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class Ex02RoyalNonStop {
    private static BufferedReader reader;
    private static int rows, cols;
    private static BigDecimal[] prices;
    private static BigDecimal totalMoney;
    private static int totalCustomers;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        totalMoney = BigDecimal.ZERO;
        totalCustomers = 0;
    }

    public static void main(String[] args) throws IOException {
        readInitialInput();

        String input;
        while (! "Royal Close".equals(input = reader.readLine())) {
            String[] coordinates = input.split("\\s+");
            int row = Integer.parseInt(coordinates[0]) + 1;
            int col = Integer.parseInt(coordinates[1]) + 1;

            if (row < col) {
                goUp(row, col);
                goLeft(1, col - 1);
            } else {
                goLeft(row, col);
                goUp(row - 1, 1);
            }
            totalCustomers++;
        }

        System.out.println(String.format("%.6f", totalMoney));
        System.out.println(totalCustomers);
    }

    private static void goLeft(int row, int col) {
        while (col >= 1) {
            if (row == 1 & col == 1) {
                break;
            }
            addToAccount(row, col);
            col--;
        }
    }

    private static void goUp(int row, int col) {
        while (row >= 1) {
            if (row == 1 & col == 1) {
                break;
            }
            addToAccount(row, col);
            row--;
        }
    }

    private static void addToAccount(int row, int col) {
        BigDecimal currentRow = new BigDecimal(row);
        BigDecimal currentCol = new BigDecimal(col);
        totalMoney = totalMoney.add(currentRow.multiply(currentCol).multiply(prices[row % 2]));
    }

    private static void readInitialInput() throws IOException {
        String[] dimension = reader.readLine().split("\\s+");
        rows = Integer.parseInt(dimension[0]);
        cols = Integer.parseInt(dimension[1]);
        String[] tokens = reader.readLine().split("\\s+");
        prices = new BigDecimal[tokens.length];
        prices[1] = new BigDecimal(tokens[0]);
        prices[0] = new BigDecimal(tokens[1]);
    }
}
