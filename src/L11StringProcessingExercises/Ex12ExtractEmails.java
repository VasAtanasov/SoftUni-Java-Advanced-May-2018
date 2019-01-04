package L11StringProcessingExercises;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex12ExtractEmails {
    private static final String REGEX;
    private static List<String> matches;
    private static Pattern pattern;
    private static BufferedReader reader;

    static {
        REGEX = "\\b(?<![-._])[A-Za-z][A-Z-a-z0-9._-]*[A-Za-z]@[A-Za-z-]+(?:\\.[a-z]+)+\\b";
        matches = new ArrayList<>();
        pattern = Pattern.compile(REGEX);
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        String input = reader.readLine();
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
