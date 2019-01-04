package L10StringProcessing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex05VowelCount {
    private static BufferedReader reader;
    private static Pattern pattern;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        pattern = Pattern.compile("[AEIOUYaeiouy]");
    }

    public static void main(String[] args) throws IOException {
        String input = reader.readLine();
        Matcher matcher = pattern.matcher(input);

        int count = 0;
        while (matcher.find()) {
            count++;
        }

        System.out.println(String.format("Vowels: %d", count));
    }
}

