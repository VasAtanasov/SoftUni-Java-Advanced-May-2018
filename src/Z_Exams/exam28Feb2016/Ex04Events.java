package Z_Exams.exam28Feb2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Ex04Events {
    private static BufferedReader reader;
    private static final String REGEX;
    private static Pattern pattern;
    private static Map<String, Event> events;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        REGEX = "^#(?<person>[A-Za-z]+):\\s*@(?<location>[A-Za-z]+)\\s*(?<time>(?:0*[0-9]|1[0-9]|2[0-3]):(?:[0-5]*[0-9]))$";
        pattern = Pattern.compile(REGEX);
        events = new TreeMap<>();
    }

    public static void main(String[] args) throws IOException {
        registerEvents();

        List<String> locations = Arrays.stream(reader.readLine().split(","))
                .collect(Collectors.toCollection(ArrayList::new));

        events.values()
                .stream()
                .filter(event -> locations.contains(event.getLocation()))
                .forEach(System.out::print);
    }

    private static void registerEvents() throws IOException {
        int count = Integer.parseInt(reader.readLine());
        for (int i = 0; i < count; i++) {
            Matcher matcher = pattern.matcher(reader.readLine());
            if (matcher.find()) {
                String personName = matcher.group("person");
                String location = matcher.group("location");
                String time = matcher.group("time");
                events.putIfAbsent(location, new Event(location));
                events.get(location).add(personName, time);
            }
        }
    }
}

class Event {
    private String location;
    private Map<String, Person> people;

    Event(String location) {
        this.location = location;
        this.people = new TreeMap<>();
    }

    void add(String personName, String time) {
        this.people.putIfAbsent(personName, new Person(personName));
        this.people.get(personName).add(time);
    }

    String getLocation() {
        return this.location;
    }

    private String getPeopleString() {
        StringBuilder output = new StringBuilder();
        int[] rank = {1};
        this.people
                .values()
                .forEach(person -> output.append(String.format("%d. %s%n", rank[0]++, person)));
        return output.toString();
    }

    @Override
    public String toString() {
        return String.format("%s:%n%s", this.getLocation(), this.getPeopleString());
    }
}

class Person {
    private String name;
    private List<String> times;

    Person(String name) {
        this.name = name;
        this.times = new ArrayList<>();
    }

    private String getName() {
        return this.name;
    }

    private List<String> getTimes() {
        return this.times.stream()
                .sorted()
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private String getTimesString() {
        return getTimes().toString().replaceAll("[\\[\\]]", "");
    }

    void add(String time) {
        this.times.add(time);
    }

    @Override
    public String toString() {
        return String.format("%s -> %s", this.getName(), this.getTimesString());
    }
}