package Practical_exam10Jun2018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class Ex02Snake {
    private static BufferedReader reader;
    private static int size;
    private static Deque<String> commands = new ArrayDeque<>();
    private static Snake snake;
    private static Set<String> enemies;
    private static Set<String> foods;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        enemies = new HashSet<>();
        foods = new HashSet<>();
    }

    public static void main(String[] args) throws IOException {
        getSizeAndCommands();
        getGameParameters();

        while (true) {
            if (foods.isEmpty()) {
                System.out.println(String.format("You win! Final snake length is %d", snake.getLength()));
                return;
            } else if (commands.isEmpty()) {
                System.out.println(String.format("You lose! There is still %d food to be eaten.", foods.size()));
                return;
            }
            String command = commands.removeFirst();
            int snakeRow = snake.getRow();
            int snakeCol = snake.getCol();

            switch (command) {
                case "up":
                    moveUp(snakeRow - 1, snakeCol);
                    break;
                case "down":
                    moveDown(snakeRow + 1, snakeCol);
                    break;
                case "left":
                    moveLeft(snakeRow, snakeCol - 1);
                    break;
                case "right":
                    moveRight(snakeRow, snakeCol + 1);
                    break;
            }

            if (snake.isDead()) {
                System.out.println("You lose! Killed by an enemy!");
                return;
            }
        }
    }

    private static void moveRight(int row, int col) {
        String cell;
        if (isInRange(col)) {
            cell = String.format("%d%d", row, col);
            snake.setCol(col);
        } else {
            cell = String.format("%d%d", row, 0);
            snake.setCol(0);
        }

        processCell(cell);
    }

    private static void moveLeft(int row, int col) {
        String cell;
        if (isInRange(col)) {
            cell = String.format("%d%d", row, col);
            snake.setCol(col);
        } else {
            cell = String.format("%d%d", row, size - 1);
            snake.setCol(size - 1);
        }

        processCell(cell);
    }


    private static void moveDown(int row, int col) {
        String cell;
        if (isInRange(row)) {
            cell = String.format("%d%d", row, col);
            snake.setRow(row);
        } else {
            cell = String.format("%d%d", 0, col);
            snake.setRow(0);
        }

        processCell(cell);
    }

    private static void moveUp(int row, int col) {
        String cell;
        if (isInRange(row)) {
            cell = String.format("%d%d", row, col);
            snake.setRow(row);
        } else {
            cell = String.format("%d%d", size - 1, col);
            snake.setRow(size - 1);
        }

        processCell(cell);
    }

    private static void processCell(String cell) {
        if (enemies.contains(cell)) {
            snake.dies();
        } else if (foods.contains(cell)) {
            snake.eatFood();
            foods.remove(cell);
        }
    }

    private static boolean isInRange(int value) {
        return value >= 0 && value < size;
    }

    private static void getGameParameters() throws IOException {
        for (int row = 0; row < size; row++) {
            String[] inputRow = reader.readLine().toLowerCase().split("\\s+");
            for (int col = 0; col < size; col++) {
                switch (inputRow[col]) {
                    case "s":
                        snake = new Snake(row, col);
                        break;
                    case "f":
                        foods.add(String.format("%d%d", row, col));
                        break;
                    case "e":
                        enemies.add(String.format("%d%d", row, col));
                        break;
                }
            }
        }
    }

    private static void getSizeAndCommands() throws IOException {
        size = Integer.parseInt(reader.readLine());
        String[] tokens = reader.readLine().toLowerCase().split(", ");
        for (String token : tokens) {
            commands.addLast(token.trim());
        }
    }
}

class Snake {
    private int row;
    private int col;
    private int length;
    private boolean isDead = false;

    Snake(int row, int col) {
        this.row = row;
        this.col = col;
        this.length = 1;
        this.isDead = false;
    }

    int getRow() {
        return this.row;
    }

    int getCol() {
        return this.col;
    }

    int getLength() {
        return this.length;
    }

    void setRow(int row) {
        this.row = row;
    }

    void setCol(int col) {
        this.col = col;
    }

    void eatFood() {
        this.length++;
    }

    void dies() {
        this.isDead = true;
    }

    boolean isDead() {
        return this.isDead;
    }
}

