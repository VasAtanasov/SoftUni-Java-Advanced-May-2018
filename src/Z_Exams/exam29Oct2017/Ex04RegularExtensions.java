package Z_Exams.exam29Oct2017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex04RegularExtensions {
    private static final String REGEX;
    private static BufferedReader reader;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        REGEX = "[^\\s]*";
    }

    public static void main(String[] args) throws IOException {
        String text = reader.readLine();

        String input;
        while (! "Print".equals(input = reader.readLine())) {
            if (input.contains("%")) {
                input = input.replace("%", REGEX);
                input = input.replace(".", "\\.");
                Pattern pattern = Pattern.compile(input);
                Matcher matcher = pattern.matcher(text);
                while (matcher.find()) {
                    String word = matcher.group();
                    String reversed = getString(word);
                    text = text.replace(word, reversed);
                }
            } else {
                String reversed = getString(input);
                text = text.replace(input, reversed);
            }
        }

        System.out.println(text);

    }

    private static String getString(String word) {
        return new StringBuilder(word).reverse().toString();
    }
}
