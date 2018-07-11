package L11StringProcessingExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex09MatchFullName {
    private static BufferedReader reader;
    private static final String REGEX;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        REGEX = "^[A-Z][a-z]+ [A-Z][a-z]+$";
    }

    public static void main(String[] args) throws IOException {
        String name;
        while (! "end".equals(name = reader.readLine())) {
            if (name.matches(REGEX)) {
                System.out.println(name);
            }
        }
    }
}
