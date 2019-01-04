package Z_Exams.exam08Feb2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Ex04UserLog {
    private static BufferedReader reader;
    private static final String REGEX;
    private static Pattern pattern;
    private static Map<String, UserLog> users;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        REGEX = "IP=(?<ip>.*)\\s+message=.*\\s+user=(?<user>.*)";
        pattern = Pattern.compile(REGEX);
        users = new TreeMap<>();
    }

    public static void main(String[] args) throws IOException {
        String input;
        while (! "end".equals(input = reader.readLine())) {
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                String userName = matcher.group("user");
                String ip = matcher.group("ip");

                users.putIfAbsent(userName, new UserLog(userName));
                users.get(userName).updateLog(ip);
            }
        }

        users.values().forEach(System.out::println);
    }
}

class UserLog {
    private String userName;
    private LinkedHashMap<String, Integer> log;

    UserLog(String userName) {
        this.userName = userName;
        this.log = new LinkedHashMap<>();
    }

    void updateLog(String ip) {
        this.log.putIfAbsent(ip, 0);
        this.log.put(ip, this.log.get(ip) + 1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s:%n", this.userName));
        sb.append(log.entrySet().stream()
                .map(e -> String.format("%s => %d", e.getKey(), e.getValue()))
                .collect(Collectors.joining(", ")));
        sb.append(".");
        return sb.toString();
    }
}