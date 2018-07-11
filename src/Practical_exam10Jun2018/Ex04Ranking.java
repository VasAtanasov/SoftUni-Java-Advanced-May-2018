package Practical_exam10Jun2018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class Ex04Ranking {
    private static BufferedReader reader;
    private static Map<String, String> contests;
    private static Map<String, User> users;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        contests = new HashMap<>();
        users = new LinkedHashMap<>();
    }

    public static void main(String[] args) throws IOException {
        readContests();

        String input;
        while (! "end of submissions".equals(input = reader.readLine())) {
            String[] tokens = input.split("=>");
            String contest = tokens[0];
            String password = tokens[1];
            String userName = tokens[2];
            int points = Integer.parseInt(tokens[3]);

            if (contests.containsKey(contest) && contests.get(contest).equals(password)) {
                users.putIfAbsent(userName, new User(userName));
                users.get(userName).add(contest, points);
            }
        }

        Optional<User> best = users.values()
                .stream()
                .sorted((a, b) -> Integer.compare(b.getTotalPoints(), a.getTotalPoints()))
                .findFirst();
        if (best.isPresent()) {
            User bestUser = best.get();
            System.out.println(String.format("Best candidate is %s with total %d points.",bestUser.getName(),bestUser.getTotalPoints()));
        }
        System.out.println("Ranking:");
        users.values()
                .stream()
                .sorted()
                .forEach(System.out::print);

    }

    private static void readContests() throws IOException {
        String input;
        while (! "end of contests".equals(input = reader.readLine())) {
            String[] tokens = input.split(":");
            String contest = tokens[0];
            String password = tokens[1];
            contests.put(contest, password);
        }
    }
}

class User implements Comparable<User> {
    private String name;
    private Map<String, Integer> contests;

    User(String name) {
        this.name = name;
        this.contests = new LinkedHashMap<>();
    }

    String getName() {
        return this.name;
    }

    void add(String contest, int points) {
        this.contests.putIfAbsent(contest, 0);
        if (this.contests.get(contest) < points) {
            this.contests.put(contest, points);
        }
    }

    private String getContests() {
        StringBuilder sb = new StringBuilder();
        contests.entrySet().stream()
                .sorted((a, b) -> Integer.compare(b.getValue(), a.getValue()))
                .forEach(c -> sb.append(String.format("#  %s -> %d%n", c.getKey(), c.getValue())));
        return sb.toString();
    }

    int getTotalPoints() {
        return this.contests
                .values()
                .stream()
                .reduce(0, (a, b) -> a + b);
    }

    @Override
    public int compareTo(User other) {
        return this.getName().compareTo(other.getName());
    }

    @Override
    public String toString() {
        return String.format("%s%n%s", this.getName(), this.getContests());
    }
}