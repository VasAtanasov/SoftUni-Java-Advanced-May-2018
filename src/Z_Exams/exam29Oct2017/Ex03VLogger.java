package Z_Exams.exam29Oct2017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Ex03VLogger {
    private static BufferedReader reader;
    private static Map<String, User> website;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        website = new LinkedHashMap<>();
    }

    public static void main(String[] args) throws IOException {
        userActivities();

        System.out.println(String.format("The V-Logger has a total of %d vloggers in its logs.", website.size()));
        if (! website.isEmpty()) {
            int[] rank = {1};
            StringBuilder output = new StringBuilder();
            website.values().stream()
                    .sorted()
                    .forEach(getUserConsumer(rank, output));
            System.out.println(output.toString());
        }
    }

    private static Consumer<User> getUserConsumer(int[] rank, StringBuilder output) {
        return user -> {
            output.append(String.format("%d. %s%n", rank[0], user));
            if (rank[0] == 1) {
                output.append(user.getFollowersString());
            }
            rank[0]++;
        };
    }

    private static void userActivities() throws IOException {
        String input;
        while (! "Statistics".equals(input = reader.readLine())) {
            String tokens[] = input.split("\\s+");
            String command = tokens[1];

            switch (command) {
                case "joined":
                    String vLoggerName = tokens[0];
                    website.putIfAbsent(vLoggerName, new User(vLoggerName));
                    break;
                case "followed":
                    following(tokens);
                    break;
            }
        }
    }

    private static void following(String[] tokens) {
        String firstVLoggerName = tokens[0];
        String secondVLoggerName = tokens[2];
        if (website.containsKey(firstVLoggerName) && website.containsKey(secondVLoggerName)) {
            User firstUser = website.get(firstVLoggerName);
            User secondUser = website.get(secondVLoggerName);
            firstUser.follow(secondUser);
            secondUser.addFollower(firstUser);
        }
    }
}

class User implements Comparable<User> {
    private String name;
    private Map<String, User> followers;
    private Map<String, User> following;

    User(String name) {
        this.name = name;
        this.followers = new LinkedHashMap<>();
        this.following = new LinkedHashMap<>();
    }

    String getName() {
        return this.name;
    }

    private Map<String, User> getFollowers() {
        return this.followers;
    }

    String getFollowersString() {
        StringBuilder output = new StringBuilder();
        followers.keySet().stream()
                .sorted(String::compareToIgnoreCase)
                .forEach(user -> output.append(String.format("*  %s%n", user)));
        return output.toString();
    }

    private Map<String, User> getFollowing() {
        return this.following;
    }

    private int getFollowersCount() {
        return this.getFollowers().size();
    }

    private int getFollowingCount() {
        return this.getFollowing().size();
    }

    void follow(User user) {
        if (notSelfFollowing(user)) {
            following.putIfAbsent(user.getName(), user);
        }
    }

    void addFollower(User user) {
        if (notSelfFollowing(user)) {
            followers.putIfAbsent(user.getName(), user);
        }
    }

    private boolean notSelfFollowing(User user) {
        return ! user.getName().equals(this.getName());
    }

    @Override
    public int compareTo(User other) {
        int index = Integer.compare(other.getFollowersCount(), this.getFollowersCount());
        return index != 0 ? index : Integer.compare(this.getFollowingCount(), other.getFollowingCount());
    }

    @Override
    public String toString() {
        return String.format("%s : %d followers, %d following", this.getName(), this.getFollowersCount(), this.getFollowingCount());
    }
}

