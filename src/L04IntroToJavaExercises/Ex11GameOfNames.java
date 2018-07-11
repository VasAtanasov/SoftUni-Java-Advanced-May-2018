package L04IntroToJavaExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex11GameOfNames {
    private static BufferedReader reader;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        int count = Integer.parseInt(reader.readLine());

        String bestPlayer = "";
        int bestScore = Integer.MIN_VALUE;

        for (int i = 0; i < count; i++) {
            String playerName = reader.readLine();
            int playerScore = Integer.parseInt(reader.readLine());
            int nameScore = playerName.chars()
                    .map(n -> n % 2 == 0 ? n : n * - 1)
                    .reduce(0, (a, b) -> a + b);

            playerScore += nameScore;

            if (playerScore > bestScore) {
                bestPlayer = playerName;
                bestScore = playerScore;
            }
        }

        System.out.println(String.format("The winner is %s - %d points", bestPlayer, bestScore));
    }
}
