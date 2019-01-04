package Z_Exams.exam15Nov2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex04LogParser {
    private static BufferedReader reader;
    private static final String REGEX;
    private static Pattern pattern;
    private static Map<String, Project> projects;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        REGEX = "^\\{\"Project\":\\s*\\[\"(?<project>[^\\n]+?)\"],\\s*\"Type\":\\s*\\[\"(?<type>Critical|Warning)\"],\\s*\"Message\":\\s*\\[\"(?<message>[^\\n]+?)\"]}$";
        pattern = Pattern.compile(REGEX);
        projects = new LinkedHashMap<>();
    }

    public static void main(String[] args) throws IOException {
        registerErrors();

        projects.values()
                .stream()
                .sorted()
                .forEach(System.out::println);

    }

    private static void registerErrors() throws IOException {
        String input;
        while (! "END".equals(input = reader.readLine())) {
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                String project = matcher.group("project");
                String errorType = matcher.group("type");
                String message = matcher.group("message");
                projects.putIfAbsent(project, new Project(project));
                projects.get(project).add(errorType, message);

            }
        }
    }
}

class Project implements Comparable<Project> {
    private String name;
    private List<String> criticalMessages;
    private List<String> warningMessages;

    Project(String name) {
        this.name = name;
        this.criticalMessages = new ArrayList<>();
        this.warningMessages = new ArrayList<>();
    }

    void add(String errorType, String message) {
        switch (errorType) {
            case "Critical":
                criticalMessages.add(message);
                break;
            case "Warning":
                warningMessages.add(message);
                break;
        }
    }

    private String getName() {
        return this.name;
    }

    private int getTotalNumberOfMessages() {
        return this.criticalMessages.size() + this.warningMessages.size();
    }

    private String getCriticalMessagesReport(List<String> messages) {
        StringBuilder report = new StringBuilder();
        if (messages.size() == 0) {
            report.append("--->None").append(System.lineSeparator());
        } else {
            messages.stream()
                    .sorted((firstMessage, secondMessage) -> {
                        int index = Integer.compare(firstMessage.length(), secondMessage.length());
                        return index != 0 ? index : firstMessage.compareTo(secondMessage);
                    })
                    .forEach(message -> report.append(String.format("--->%s%n", message)));
        }
        return report.toString();
    }

    @Override
    public int compareTo(Project other) {
        int index = Integer.compare(other.getTotalNumberOfMessages(), this.getTotalNumberOfMessages());
        return index != 0 ? index : this.getName().compareTo(other.getName());
    }

    @Override
    public String toString() {
        return String.format("%s:", this.getName()) +
                System.lineSeparator() +
                String.format("Total Errors: %d", this.getTotalNumberOfMessages()) +
                System.lineSeparator() +
                String.format("Critical: %d", this.criticalMessages.size()) +
                System.lineSeparator() +
                String.format("Warnings: %d", this.warningMessages.size()) +
                System.lineSeparator() +
                "Critical Messages:" +
                System.lineSeparator() +
                this.getCriticalMessagesReport(this.criticalMessages) +
                "Warning Messages:" +
                System.lineSeparator() +
                this.getCriticalMessagesReport(this.warningMessages);
    }
}