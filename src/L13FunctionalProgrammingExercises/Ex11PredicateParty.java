package L13FunctionalProgrammingExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public class Ex11PredicateParty {
    private static BufferedReader reader;
    private static Map<String, BiPredicate<String, String>> predicates;
    private static List<String> names;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        predicates = new HashMap<String, BiPredicate<String, String>>() {{
            put("StartsWith", String::startsWith);
            put("EndsWith", String::endsWith);
            put("Length", (name, criteria) -> name.length() == Integer.parseInt(criteria));
        }};
    }

    public static void main(String[] args) throws IOException {
        names = Arrays.stream(reader.readLine().split("\\s+"))
                .collect(Collectors.toCollection(ArrayList::new));

        String input;
        while (! "Party!".equals(input = reader.readLine())) {
            String[] tokens = input.split("\\s+");
            String command = tokens[0];
            String condition = tokens[1];
            String criteria = tokens[2];

            switch (command) {
                case "Remove":
                    removeNames(condition, criteria);
                    break;
                case "Double":
                    doubleNames(condition, criteria);
                    break;
            }
        }

        if (names.isEmpty()) {
            System.out.println("Nobody is going to the party!");
        } else {
            System.out.println(String.format("%s are going to the party!",names.toString().replaceAll("[\\[\\]]","")));
        }
    }

    private static void doubleNames(String condition, String criteria) {
        List<String> doubledNames = new ArrayList<>();
        BiPredicate<String, String> predicate = predicates.get(condition);
        for (String name : names) {
            if (predicate.test(name, criteria)) {
                doubledNames.add(name);
            }
            doubledNames.add(name);
        }
        names.clear();
        names.addAll(doubledNames);
    }

    private static void removeNames(String condition, String criteria) {
        names.removeIf(name -> predicates.get(condition).test(name, criteria));
    }
}
