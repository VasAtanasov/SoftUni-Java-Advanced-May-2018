package Z_Exams.exam11May2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Ex03LabyrinthDash {
    private static BufferedReader reader;
    private static int rows;
    private static char[][] labyrinth;
    private static Player player;
    private static final List<Character> WALLS = Arrays.asList('_', '|');
    private static final List<Character> OBSTACLES = Arrays.asList('@', '#', '*');
    private static final char LIFE = '$';
    private static final char WHITE_SPACE = ' ';
    private static final char DOT = '.';
    private static int totalMoves;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        player = new Player();
        totalMoves = 0;
    }

    public static void main(String[] args) throws IOException {
        receiveInput();

        char[] directions = reader.readLine().toCharArray();
        for (char direction : directions) {
            int row = player.getRow();
            int col = player.getCol();
            switch (direction) {
                case '^':
                    row -= 1;
                    break;
                case 'v':
                    row += 1;
                    break;
                case '>':
                    col += 1;
                    break;
                case '<':
                    col -= 1;
                    break;
            }

            boolean madeMove;
            if (isInRange(row, col)) {
                madeMove = checkMove(labyrinth[row][col]);
                if (labyrinth[row][col] == LIFE) {
                    labyrinth[row][col] = DOT;
                }
            } else {
                madeMove = checkMove(WHITE_SPACE);
            }

            if (madeMove) {
                totalMoves++;
                player.setNewPosition(row, col);
            }

            if (player.isDead()) {
                System.out.println(String.format("Total moves made: %d", totalMoves));
                return;
            }

        }

        System.out.println(String.format("Total moves made: %d", totalMoves));
    }

    private static boolean checkMove(char ch) {
        if (WALLS.contains(ch)) {
            System.out.println("Bumped a wall.");
            return false;
        } else if (OBSTACLES.contains(ch)) {
            player.loseLife();
            System.out.println(String.format("Ouch! That hurt! Lives left: %d", player.getLife()));
            if (player.isDead()) {
                System.out.println("No lives left! Game Over!");
            }
            return true;
        } else if (ch == LIFE) {
            player.gainLife();
            System.out.println(String.format("Awesome! Lives left: %d", player.getLife()));
            return true;
        } else if (ch == WHITE_SPACE) {
            System.out.println("Fell off a cliff! Game Over!");
            player.dies();
            return true;
        } else if (ch == DOT) {
            System.out.println("Made a move!");
            return true;
        }
        return false;
    }

    private static boolean isInRange(int row, int col) {
        return row >= 0 && row < rows &&
                col >= 0 && col < labyrinth[row].length;
    }

    private static void receiveInput() throws IOException {
        rows = Integer.parseInt(reader.readLine());
        labyrinth = new char[rows][];
        for (int row = 0; row < rows; row++) {
            labyrinth[row] = reader.readLine().toCharArray();
        }
    }
}

class Player {
    private int row;
    private int col;
    private int life;

    Player() {
        this.row = 0;
        this.col = 0;
        this.life = 3;
    }

    void setNewPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    int getRow() {
        return this.row;
    }

    int getCol() {
        return this.col;
    }

    boolean isDead() {
        return this.life <= 0;
    }

    int getLife() {
        return this.life;
    }

    void loseLife() {
        this.life -= 1;
    }

    void gainLife() {
        this.life += 1;
    }

    void dies() {
        this.life = 0;
    }
}