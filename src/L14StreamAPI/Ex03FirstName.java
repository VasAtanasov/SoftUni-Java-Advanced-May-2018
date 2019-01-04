package L14StreamAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Ex03FirstName {
    private static BufferedReader reader;
    private static List<String> names;
    private static List<String> letters;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        names = new ArrayList<>();
        letters = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {
        readInput();

        Optional<String> optional = names.stream()
                .filter(nameFilter())
                .sorted(String::compareToIgnoreCase)
                .findFirst();

        if (optional.isPresent()) {
            System.out.println(optional.get());
        } else {
            System.out.println("No match");
        }

    }

    private static Predicate<String> nameFilter() {
        return name -> {
            for (String letter : letters) {
                if (name.startsWith(letter)) {
                    return true;
                }
            }
            return false;
        };
    }

    private static void readInput() throws IOException {
        names = Arrays.stream(reader.readLine().split("\\s+"))
                .collect(Collectors.toCollection(ArrayList::new));

        letters = Arrays.stream(reader.readLine().toUpperCase().split("\\s+"))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
