package L11StringProcessingExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex15ValidUserName {
    private static final String REGEX = "(?<word>[A-Za-z0-9_]+)";
    private static Pattern pattern = Pattern.compile(REGEX);

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        Matcher matcher = pattern.matcher(input);
        List<String> words = new ArrayList<>();

        while (matcher.find()) {
            String word = matcher.group("word");
            if (Character.isLetter(word.charAt(0)) && word.length() >= 3 && word.length() <= 25) {
                words.add(word);
            }
        }

        int maxLength = Integer.MIN_VALUE;
        String output = "";
        for (int i = 0; i < words.size() - 1; i++) {
            int length = words.get(i).length();
            int nextLength = words.get(i + 1).length();

            if (length + nextLength > maxLength) {
                maxLength = length + nextLength;
                output = String.format("%s%n%s", words.get(i), words.get(i + 1));
            }
        }

        System.out.println(output);

    }
}
