package L11StringProcessingExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex13SentenceExtractor {
    private static BufferedReader reader;
    private static final String REGEX;
    private static Pattern pattern;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        REGEX = "[A-Z].*?[.!?]";
        pattern = Pattern.compile(REGEX);
    }

    public static void main(String[] args) throws IOException {
        String keyWord = String.format(" %s ", reader.readLine());
        String text = reader.readLine();

        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            if (matcher.group().contains(keyWord)) {
                System.out.println(matcher.group());
            }
        }
    }
}
