package Z_Exams.exam07Jan2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex02TerroristsWin {
    private static BufferedReader reader;
    private static Pattern pattern = Pattern.compile("\\|(?<text>.*?)\\|");

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        String input = reader.readLine();
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            String bomb = matcher.group();
            int power = matcher.group("text").chars().sum() % 10;

            int startIndex = Math.max(0, input.indexOf(bomb) - power);
            int endIndex = Math.min(input.indexOf(bomb) + bomb.length() + power, input.length());

            StringBuilder replace = new StringBuilder(input);
            for (int i = startIndex; i < endIndex; i++) {
                replace.setCharAt(i, '.');
            }

            input = replace.toString();
            matcher = pattern.matcher(input);
        }

        System.out.println(input);
    }
}
