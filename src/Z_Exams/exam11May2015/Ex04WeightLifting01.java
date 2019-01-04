package Z_Exams.exam11May2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Ex04WeightLifting01 {
    private static BufferedReader reader;
    private static Map<String, TreeMap<String, Long>> lifters;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        lifters = new TreeMap<>();
    }

    public static void main(String[] args) throws IOException {
        int lines = Integer.valueOf(reader.readLine());
        for (int i = 0; i < lines; i++) {
            String[] tokens = reader.readLine().split("\\s+");
            String lifter = tokens[0];
            String exercise = tokens[1];
            long weight = Long.parseLong(tokens[2]);

            lifters.putIfAbsent(lifter, new TreeMap<>());
            lifters.get(lifter).putIfAbsent(exercise, 0L);
            long updateLoad = lifters.get(lifter).get(exercise) + weight;
            lifters.get(lifter).put(exercise, updateLoad);
        }

        lifters.forEach((lifter, exercises) -> {
            System.out.print(String.format("%s : ", lifter));
            System.out.println(exercises.entrySet().stream()
                    .map(ex -> String.format("%s - %d kg", ex.getKey(), ex.getValue()))
                    .collect(Collectors.joining(", ")));
        });
    }
}
