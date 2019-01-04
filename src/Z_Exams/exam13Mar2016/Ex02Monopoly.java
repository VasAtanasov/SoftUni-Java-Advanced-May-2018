package Z_Exams.exam13Mar2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex02Monopoly {
    private static BufferedReader reader;
    private static int rows, cols;
    private static char[][] gameBoard;
    private static MonopolyPlayer player;
    private static StringBuilder output;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        player = new MonopolyPlayer();
        output = new StringBuilder();
    }

    public static void main(String[] args) throws IOException {
        getDimension();
        initializeGameBoard();
        playTheGame();

        System.out.print(output.toString());
        System.out.println(player);

    }

    private static void playTheGame() {
        for (int row = 0; row < rows; row++) {
            if (row % 2 == 0) {
                rightToLeft(row);
            } else {
                leftToRight(row);
            }
        }
    }

    private static void leftToRight(int row) {
        for (int col = cols - 1; col >= 0; col--) {
            processTurn(row, col);
        }
    }

    private static void rightToLeft(int row) {
        for (int col = 0; col < cols; col++) {
            processTurn(row, col);
        }
    }

    private static void processTurn(int row, int col) {
        switch (gameBoard[row][col]) {
            case 'H':
                output.append(player.buyHotel());
                output.append(System.lineSeparator());
                break;
            case 'S':
                output.append(player.shop(row, col));
                output.append(System.lineSeparator());
                break;
            case 'J':
                output.append(player.jail());
                output.append(System.lineSeparator());
                break;
        }
        player.turn();
    }

    private static void initializeGameBoard() throws IOException {
        gameBoard = new char[rows][cols];
        for (int row = 0; row < rows; row++) {
            char[] inputRow = reader.readLine().toCharArray();
            System.arraycopy(inputRow, 0, gameBoard[row], 0, cols);
        }
    }

    private static void getDimension() throws IOException {
        String[] size = reader.readLine().split("\\s+");
        rows = Integer.parseInt(size[0]);
        cols = Integer.parseInt(size[1]);
    }
}

class MonopolyPlayer {
    private int money;
    private int turns;
    private int hotelsCount;

    MonopolyPlayer() {
        this.money = 50;
        this.turns = 0;
        this.hotelsCount = 0;
    }

    private int getMoney() {
        return this.money;
    }

    private int getTurns() {
        return this.turns;
    }

    private int getHotelsCount() {
        return this.hotelsCount;
    }

    String buyHotel() {
        this.hotelsCount += 1;
        String message = String.format("Bought a hotel for %d. Total hotels: %d.", this.getMoney(), this.getHotelsCount());
        this.money = 0;
        return message;
    }

    String shop(int row, int col) {
        int value = (row + 1) * (col + 1);
        if (this.money <= value) {
            int spent = this.money;
            this.money = 0;
            return String.format("Spent %d money at the shop.", spent);
        } else {
            this.money = this.money - value;
            return String.format("Spent %d money at the shop.", value);
        }
    }

    private void income() {
        this.money += (hotelsCount * 10);
    }

    String jail() {
        String message = String.format("Gone to jail at turn %d.", this.getTurns());
        this.turn();
        this.turn();
        return message;
    }

    void turn() {
        this.turns += 1;
        this.income();
    }

    @Override
    public String toString() {
        return String.format("Turns %d%nMoney %d", this.getTurns(), this.getMoney());
    }
}
