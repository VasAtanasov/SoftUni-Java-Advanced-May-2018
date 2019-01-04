package L11StringProcessingExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class Ex05Palindromes {
    private static BufferedReader reader;
    private static Set<String> palindromes;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        palindromes = new TreeSet<>(String::compareToIgnoreCase);
    }

    public static void main(String[] args) throws IOException {
        String[] words = Arrays.stream(reader.readLine().split("[!?.,\\s]+"))
                .filter(s -> ! s.isEmpty())
                .toArray(String[]::new);

        for (String word : words) {
            if (word.equals(new StringBuilder(word).reverse().toString())) {
                palindromes.add(word);
            }
        }

        System.out.println(palindromes);
    }
}
