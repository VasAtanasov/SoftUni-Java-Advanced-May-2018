package Z_Exams.exam03May2017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Ex03ChessKnight {
    private static final int BOARD_SIZE = 8;
    private static BufferedReader reader;
    private static Map<String, Character> chessPieces;
    private static List<Character> piecesTaken;
    private static int invalidMoves;
    private static int boardOutMoves;
    private static int currentRow;
    private static int currentCol;
    private static int nextRow;
    private static int nextCol;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        chessPieces = new LinkedHashMap<>();
        piecesTaken = new ArrayList<>();
        invalidMoves = 0;
        boardOutMoves = 0;
    }

    public static void main(String[] args) throws IOException {
        getChessPieces();
        String currentPosition = reader.readLine();
        currentRow = currentPosition.charAt(0) - 48;
        currentCol = currentPosition.charAt(1) - 48;

        String input;
        while (! "END".equals(input = reader.readLine())) {
            String[] tokens = input.split(" -> ");
            String nextPosition = tokens[1];
            nextRow = nextPosition.charAt(0) - 48;
            nextCol = nextPosition.charAt(1) - 48;

            if (! isPossibleMove()) {
                invalidMoves++;
                continue;
            }

            if (! isInRange(nextRow, nextCol)) {
                boardOutMoves++;
                continue;
            }

            currentRow = nextRow;
            currentCol = nextCol;
            if (chessPieces.containsKey(nextPosition)) {
                piecesTaken.add(chessPieces.remove(nextPosition));
            }
        }

        printResult();
    }

    private static boolean isPossibleMove() {
        int X[] = {2, 1, - 1, - 2, - 2, - 1, 1, 2};
        int Y[] = {1, 2, 2, 1, - 1, - 2, - 2, - 1};
        for (int i = 0; i < 8; i++) {
            int x = currentRow + X[i];
            int y = currentCol + Y[i];
            if (x == nextRow && y == nextCol) {
                return true;
            }
        }
        return false;
    }

    private static void printResult() {
        System.out.println(String.format("Pieces take: %s", piecesTaken.toString().replaceAll("[\\[\\]]", "")));
        System.out.println(String.format("Invalid moves: %d", invalidMoves));
        System.out.println(String.format("Board out moves: %d", boardOutMoves));
    }

    private static boolean isInRange(int nextRow, int nextCol) {
        return nextRow >= 0 && nextRow < BOARD_SIZE &&
                nextCol >= 0 && nextCol < BOARD_SIZE;
    }

    private static void getChessPieces() throws IOException {
        for (int row = 0; row < 8; row++) {
            int[] chars = reader.readLine().chars()
                    .filter(c -> c != '|')
                    .toArray();
            for (int col = 0; col < chars.length; col++) {
                if (chars[col] != 32) {
                    chessPieces.putIfAbsent(String.format("%d%d", row, col), (char) chars[col]);
                }
            }
        }
    }
}