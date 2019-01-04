package Z_Exams.exam13Mar2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex02BasicMarkUpLanguage {
    private static BufferedReader reader;
    private static final String INVERSE;
    private static final String REVERSE;
    private static final String REPEAT;
    private static List<String> contents = new ArrayList<>();

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        INVERSE = "\\s*<\\s*inverse\\s*content\\s*=\\s*\"(?<content>[^\"]+)\"\\s*\\/\\s*>";
        REVERSE = "\\s*<\\s*reverse\\s*content\\s*=\\s*\"(?<content>[^\"]+)\"\\s*\\/\\s*>";
        REPEAT = "\\s*<\\s*repeat\\s*value\\s*=\\s*\"(?<value>[0-9]+)\\s*\"\\s*content\\s*=\\s*\"(?<content>[^\"]+)\"\\s*\\/\\s*>";
    }

    public static void main(String[] args) throws IOException {
        String input;
        while (! "<stop/>".equals(input = reader.readLine())) {
            Matcher inverseMatcher = Pattern.compile(INVERSE).matcher(input);
            if (inverseMatcher.find()) {
                contents.add(reverseCase(inverseMatcher.group("content")));
                continue;
            }

            Matcher reverseMatcher = Pattern.compile(REVERSE).matcher(input);
            if (reverseMatcher.find()) {
                contents.add(new StringBuilder(reverseMatcher.group("content")).reverse().toString());
                continue;
            }

            Matcher repeatMatcher = Pattern.compile(REPEAT).matcher(input);
            if (repeatMatcher.find()) {
                int count = Integer.parseInt(repeatMatcher.group("value"));
                for (int i = 0; i < count; i++) {
                    contents.add(repeatMatcher.group("content"));
                }
            }
        }

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < contents.size(); i++) {
            output.append(String.format("%d. %s", i + 1, contents.get(i)));
            output.append(System.lineSeparator());
        }

        System.out.println(output.toString());

    }

    private static String reverseCase(String text) {
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (Character.isUpperCase(c)) {
                chars[i] = Character.toLowerCase(c);
            } else if (Character.isLowerCase(c)) {
                chars[i] = Character.toUpperCase(c);
            }
        }
        return new String(chars);
    }
}
