package Practical_exam10Jun2018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex03Genome {
    private static BufferedReader reader;
    private static final String REGEX;
    private static Pattern pattern;
    private static Map<String, Organism> organismMap;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        REGEX = "^(?<name>[!@#$?a-z]+)=(?<length>[0-9]+)--(?<genes>[0-9]+)<<(?<organism>[a-z]+)$";
        pattern = Pattern.compile(REGEX);
        organismMap = new LinkedHashMap<>();
    }

    public static void main(String[] args) throws IOException {
        String input;
        while (! "Stop!".equals(input = reader.readLine())) {
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                String name = matcher.group("name").replaceAll("[!@#$?]", "");
                int length = Integer.parseInt(matcher.group("length"));
                if (name.length() == length) {
                    String organism = matcher.group("organism");
                    int genes = Integer.parseInt(matcher.group("genes"));
                    organismMap.putIfAbsent(organism, new Organism(organism));
                    organismMap.get(organism).add(genes);
                }
            }
        }


        organismMap.values()
                .stream()
                .sorted()
                .forEach(System.out::println);

    }
}

class Organism implements Comparable<Organism> {
    private String name;
    private int count;

    Organism(String name) {
        this.name = name;
        this.count = 0;
    }

    String getName() {
        return this.name;
    }

    int getCount() {
        return this.count;
    }

    void add(int count) {
        this.count += count;
    }

    @Override
    public int compareTo(Organism other) {
        return Integer.compare(other.getCount(), this.getCount());
    }

    @Override
    public String toString() {
        return String.format("%s has genome size of %d", this.getName(), this.getCount());
    }
}
