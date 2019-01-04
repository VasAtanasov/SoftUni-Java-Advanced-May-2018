package Z_Exams.exam15Nov2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex02SoftUniDefenceSystem {
    private static BufferedReader reader;
    private static final String REGEX;
    private static Pattern pattern;
    private static double totalQuantity;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        REGEX = "(?<name>[A-Z][a-z]+)[^\\n]*?(?<alcohol>[A-Z][a-z]*[A-Z])[^\\n]*?(?<quantity>[0-9]+)L";
        pattern = Pattern.compile(REGEX);
    }

    public static void main(String[] args) throws IOException {
        String input;
        while (! "OK KoftiShans".equals(input = reader.readLine())) {
            Matcher matcher = pattern.matcher(input);
            while (matcher.find()) {
                String name = matcher.group("name");
                String alcohol = matcher.group("alcohol").toLowerCase();
                int quantity = Integer.parseInt(matcher.group("quantity"));
                totalQuantity += quantity;

                System.out.println(String.format("%s brought %d liters of %s!", name, quantity, alcohol));
            }
        }


        System.out.println(String.format("%.3f softuni liters", totalQuantity * 0.001));
    }
}
