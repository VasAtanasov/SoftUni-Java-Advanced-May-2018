package Z_Exams.exam03May2017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Ex04FootBallStats {
    private static BufferedReader reader;
    private static Map<String, Team> teams;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        teams = new HashMap<>();
    }

    public static void main(String[] args) throws IOException {
        String input;
        while (! "Season End".equals(input = reader.readLine())) {
            String[] tokens = input.split("\\s+");
            String firstTeam = tokens[0];
            int firstTeamScore = Integer.parseInt(tokens[4].split(":")[0]);

            String secondTeam = tokens[2];
            int secondTeamScore = Integer.parseInt(tokens[4].split(":")[1]);

            updateStats(firstTeam, firstTeamScore, secondTeam, secondTeamScore);
            updateStats(secondTeam, secondTeamScore, firstTeam, firstTeamScore);
        }

        String result = printResult();
        System.out.println(result);
    }

    private static String printResult() throws IOException {
        StringBuilder output = new StringBuilder();
        String[] printTeams = reader.readLine().split(", ");
        for (String team : printTeams) {
            if (teams.containsKey(team)) {
                output.append(teams.get(team).getOpponentsString());
            }
        }
        return output.toString();
    }

    private static void updateStats(String team, int teamScore, String opponent, int opponentScore) {
        teams.putIfAbsent(team, new Team(team));
        teams.get(team).addOpponent(opponent, teamScore, opponentScore);
    }
}

class Team {
    private String name;
    private Map<String, List<String>> opponents;

    Team(String name) {
        this.name = name;
        this.opponents = new TreeMap<>();
    }

    String getName() {
        return this.name;
    }

    String getOpponentsString() {
        StringBuilder output = new StringBuilder();
        this.opponents
                .forEach((opponent, scoreList) -> scoreList.forEach(score -> output.append(String.format("%s - %s -> %s%n", this.getName(), opponent, score))));
        return output.toString();
    }

    void addOpponent(String teamName, int thisScore, int otherScore) {
        opponents.putIfAbsent(teamName, new ArrayList<>());
        opponents.get(teamName).add(String.format("%d:%d", thisScore, otherScore));
    }
}