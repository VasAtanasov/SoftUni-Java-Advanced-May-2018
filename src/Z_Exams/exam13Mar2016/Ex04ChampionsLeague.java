package Z_Exams.exam13Mar2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Ex04ChampionsLeague {
    private static BufferedReader reader;
    private static Map<String, Team> teamMap;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        teamMap = new LinkedHashMap<>();
    }

    public static void main(String[] args) throws IOException {
        String input;
        while (! "stop".equals(input = reader.readLine())) {
            String[] tokens = input.split(" \\| ");
            String teamOneName = tokens[0];
            String teamTwoName = tokens[1];

            teamMap.putIfAbsent(teamOneName, new Team(teamOneName));
            teamMap.putIfAbsent(teamTwoName, new Team(teamTwoName));

            Team teamOne = teamMap.get(teamOneName);
            Team teamTwo = teamMap.get(teamTwoName);
            teamOne.add(teamTwoName);
            teamTwo.add(teamOneName);

            int[] firstMatchScore = getIntArray(tokens[2]);
            int[] secondMatchScore = getIntArray(tokens[3]);
            int teamOneScore = firstMatchScore[0] + secondMatchScore[1];
            int teamTwoScore = firstMatchScore[1] + secondMatchScore[0];

            if (teamOneScore > teamTwoScore) {
                teamOne.addWin();
            } else if (teamTwoScore > teamOneScore) {
                teamTwo.addWin();
            } else if (firstMatchScore[1] < secondMatchScore[1]){
                teamOne.addWin();
            } else if (firstMatchScore[1] > secondMatchScore[1]) {
                teamTwo.addWin();
            }
        }


        teamMap.values()
                .stream()
                .sorted()
                .forEach(System.out::println);

    }

    private static int[] getIntArray(String token) {
        return Arrays.stream(token.split(":"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

}

class Team implements Comparable<Team> {
    private String name;
    private Set<String> opponents;
    private int wins;

    Team(String name) {
        this.name = name;
        this.opponents = new TreeSet<>();
        this.wins = 0;
    }

    void add(String opponent) {
        this.opponents.add(opponent);
    }

    private String getName() {
        return this.name;
    }

    private int getWins() {
        return this.wins;
    }

    private String getTeamsString() {
        return this.opponents.toString().replaceAll("[\\[\\]]", "");
    }

    void addWin() {
        this.wins += 1;
    }

    @Override
    public int compareTo(Team other) {
        int index = Integer.compare(other.getWins(), this.getWins());
        return index != 0 ? index : this.getName().compareTo(other.getName());
    }

    @Override
    public String toString() {
        return this.getName() +
                System.lineSeparator() +
                String.format("- Wins: %d", this.getWins()) +
                System.lineSeparator() +
                String.format("- Opponents: %s", this.getTeamsString());
    }
}