package Z_Exams.exam22Aug2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex04AshesOfRoses {
    private static BufferedReader reader;
    private static final String REGEX;
    private static Pattern pattern;
    private static Map<String, Region> regions;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        REGEX = "^Grow <(?<region>[A-Z][a-z]*)> <(?<color>[A-Za-z0-9]+)> (?<amount>[0-9]+)$";
        pattern = Pattern.compile(REGEX);
        regions = new LinkedHashMap<>();
    }

    public static void main(String[] args) throws IOException {
        String input;
        while (! "Icarus, Ignite!".equals(input = reader.readLine())) {
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                String regionName = matcher.group("region");
                String color = matcher.group("color");
                long amount = Long.parseLong(matcher.group("amount"));

                regions.putIfAbsent(regionName, new Region(regionName));
                regions.get(regionName).add(color, amount);
            }
        }


        regions.values().stream()
                .sorted()
                .forEach(System.out::print);
    }
}

class Region implements Comparable<Region> {
    private String name;
    private Map<String, Rose> roses;

    Region(String name) {
        this.name = name;
        this.roses = new LinkedHashMap<>();
    }

    void add(String color, long amount) {
        this.roses.putIfAbsent(color, new Rose(color));
        this.roses.get(color).add(amount);
    }

    public String getName() {
        return this.name;
    }

    private String getRoses() {
        StringBuilder rosesString = new StringBuilder();
        this.roses.values().stream()
                .sorted()
                .forEach(rose -> rosesString.append(rose).append(System.lineSeparator()));
        return rosesString.toString();
    }

    private long getTotalAmount() {
        return this.roses.values().stream()
                .map(Rose::getAmount)
                .reduce(0L, (a, b) -> a + b);
    }

    @Override
    public int compareTo(Region other) {
        int index = Long.compare(other.getTotalAmount(), this.getTotalAmount());
        return index != 0 ? index : this.getName().compareTo(other.getName());
    }

    @Override
    public String toString() {
        return String.format("%s%n%s", this.getName(), this.getRoses());
    }
}

class Rose implements Comparable<Rose> {
    private String color;
    private long amount;

    Rose(String color) {
        this.color = color;
        this.amount = 0L;
    }

    void add(long amount) {
        this.amount += amount;
    }

    long getAmount() {
        return this.amount;
    }

    private String getColor() {
        return this.color;
    }

    @Override
    public int compareTo(Rose other) {
        int index = Long.compare(this.getAmount(), other.getAmount());
        return index != 0 ? index : this.getColor().compareTo(other.getColor());
    }

    @Override
    public String toString() {
        return String.format("*--%s | %d", this.getColor(), this.getAmount());
    }
}