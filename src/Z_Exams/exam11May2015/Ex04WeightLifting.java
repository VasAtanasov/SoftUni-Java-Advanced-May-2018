package Z_Exams.exam11May2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Ex04WeightLifting {
    private static BufferedReader reader;
    private static Map<String, Lifter> lifters;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        lifters = new LinkedHashMap<>();
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            String[] tokens = reader.readLine().split("\\s+");
            String name = tokens[0];
            String type = tokens[1];
            long weight = Long.parseLong(tokens[2]);

            lifters.putIfAbsent(name, new Lifter(name));
            lifters.get(name).add(type, weight);
        }

        lifters.values()
                .stream()
                .sorted()
                .forEach(System.out::println);

    }
}

class Lifter implements Comparable<Lifter> {
    private String name;
    private Map<String, Exercise> exercises;

    Lifter(String name) {
        this.name = name;
        this.exercises = new LinkedHashMap<>();
    }

    String getName() {
        return this.name;
    }

    void add(String type, long weight) {
        this.exercises.putIfAbsent(type, new Exercise(type));
        this.exercises.get(type).addWight(weight);
    }

    private String getExercisesString() {
        return this.exercises.values()
                .stream()
                .sorted()
                .map(Exercise::toString)
                .collect(Collectors.joining(", "));
    }

    @Override
    public int compareTo(Lifter other) {
        return this.getName().compareTo(other.getName());
    }

    @Override
    public String toString() {
        return String.format("%s : %s", this.getName(), this.getExercisesString());
    }
}

class Exercise implements Comparable<Exercise> {
    private String type;
    private long totalWeight;

    Exercise(String type) {
        this.type = type;
        this.totalWeight = 0L;
    }

    String getType() {
        return this.type;
    }

    private long getTotalWeight() {
        return this.totalWeight;
    }

    void addWight(long weight) {
        this.totalWeight += weight;
    }

    @Override
    public int compareTo(Exercise other) {
        return this.getType().compareTo(other.getType());
    }

    @Override
    public String toString() {
        return String.format("%s - %d kg", this.getType(), this.getTotalWeight());
    }
}