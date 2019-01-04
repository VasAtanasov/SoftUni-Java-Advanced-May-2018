package Z_Exams.exam08May2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class Ex02RoyalNonStop01 {
    private static BufferedReader reader;
    private static final String END_COMMAND;
    private static BigDecimal lukankaPrice;
    private static BigDecimal rakiaPrice;
    private static BigDecimal earnings;
    private static int customerCount;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        earnings = BigDecimal.ZERO;
        END_COMMAND = "Royal Close";
        customerCount = 0;
    }

    public static void main(String[] args) throws IOException {
        readInput();

        String input;
        while (! END_COMMAND.equals(input = reader.readLine())) {
            String[] startPosition = input.split("\\s+");
            int startRow = Integer.parseInt(startPosition[0]);
            int startCol = Integer.parseInt(startPosition[1]);

            if (startRow < startCol) {
                goUpAndLeft(startRow, startCol);
            } else {
                goLeftAndUp(startRow, startCol);
            }
            customerCount++;
        }

        System.out.println(String.format("%.6f", earnings));
        System.out.println(customerCount);

    }

    private static void goLeftAndUp(int startRow, int startCol) {
        BigDecimal totalSpent = BigDecimal.ZERO;

        for (int col = startCol; col >= 0; col--) {
            BigDecimal productPrice = startRow % 2 == 0 ? lukankaPrice : rakiaPrice;
            BigDecimal rowValue = new BigDecimal(startRow + 1);
            BigDecimal colValue = new BigDecimal(col + 1);
            BigDecimal totalPrice = rowValue.multiply(colValue).multiply(productPrice);

            totalSpent = totalSpent.add(totalPrice);
        }

        for (int row = startRow - 1; row > 0; row--) {
            BigDecimal productPrice = row % 2 == 0 ? lukankaPrice : rakiaPrice;
            BigDecimal rowValue = new BigDecimal(row + 1);
            BigDecimal totalPrice = rowValue.multiply(productPrice);

            totalSpent = totalSpent.add(totalPrice);
        }
        earnings = earnings.add(totalSpent);

    }

    private static void goUpAndLeft(int startRow, int startCol) {
        BigDecimal totalSpent = BigDecimal.ZERO;
        for (int row = startRow; row >= 0; row--) {
            BigDecimal productPrice = row % 2 == 0 ? lukankaPrice : rakiaPrice;
            BigDecimal rowValue = new BigDecimal(row + 1);
            BigDecimal colValue = new BigDecimal(startCol + 1);
            BigDecimal totalPrice = rowValue.multiply(colValue).multiply(productPrice);

            totalSpent = totalSpent.add(totalPrice);
            startRow = row;
        }

        for (int col = startCol - 1; col > 0; col--) {
            BigDecimal productPrice = startRow % 2 == 0 ? lukankaPrice : rakiaPrice;
            BigDecimal colValue = new BigDecimal(col + 1);
            BigDecimal totalPrice = colValue.multiply(productPrice);
            totalSpent = totalSpent.add(totalPrice);
        }

        earnings = earnings.add(totalSpent);
    }

    private static void readInput() throws IOException {
        reader.readLine();
        String[] productsPrice = reader.readLine().split("\\s+");
        lukankaPrice = new BigDecimal(productsPrice[0]);
        rakiaPrice = new BigDecimal(productsPrice[1]);
    }
}
