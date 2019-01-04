package Z_Exams.exam19Feb2017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Ex04HighScore {
    private static BufferedReader reader;
    private static Map<String, Player> players;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        players = new LinkedHashMap<>();
    }

    public static void main(String[] args) throws IOException {
        String input;
        while (! "osu!".equals(input = reader.readLine())) {
            String[] tokens = Arrays.stream(input.split("[-><\\s]+"))
                    .filter(s -> ! s.isEmpty())
                    .toArray(String[]::new);

            String playerOne = tokens[1];
            addPlayer(playerOne);
            long playerOneScore = Long.parseLong(tokens[0]);

            String playerTwo = tokens[2];
            addPlayer(playerTwo);
            long playerTwoScore = Long.parseLong(tokens[3]);


            if (playerOneScore != playerTwoScore) {
                addScore(playerOne, playerTwo, playerOneScore - playerTwoScore);
                addScore(playerTwo, playerOne, playerTwoScore - playerOneScore);
            }
        }


        printResult();

    }

    private static void addScore(String player, String opponent, long score) {
        players.get(player).addDuel(opponent, score);
    }

    private static void addPlayer(String playerName) {
        players.putIfAbsent(playerName, new Player(playerName));
    }

    private static void printResult() {
        players.values().stream()
                .sorted()
                .forEach(System.out::print);
    }
}

class Player implements Comparable<Player> {
    private String name;
    private long totalScore;
    private List<String> duels;

    Player(String name) {
        this.name = name;
        this.totalScore = 0L;
        this.duels = new ArrayList<>();
    }

    private String getName() {
        return this.name;
    }

    private long getTotalScore() {
        return this.totalScore;
    }

    private List<String> getDuels() {
        return this.duels;
    }

    void addDuel(String opponentName, long score) {
        this.totalScore += score;
        this.duels.add(String.format("*   %s <-> %d%n", opponentName, score));
    }

    @Override
    public int compareTo(Player other) {
        return Long.compare(other.getTotalScore(), this.getTotalScore());
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(String.format("%s - (%d)%n", this.getName(), this.getTotalScore()));
        this.getDuels().forEach(output::append);
        return output.toString();
    }
}
