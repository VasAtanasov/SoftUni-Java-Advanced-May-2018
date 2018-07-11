package L11StringProcessingExercises;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex14SumOfAllValues {
    private static Scanner scanner;

    static {
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        String keyString = scanner.nextLine();
        String text = scanner.nextLine();

        String keysRegex = "(?<=^)([A-Za-z_]+)(?=\\d).*(?<=\\d)([A-Za-z_]+)(?=$)";
        Matcher keysMatcher = Pattern.compile(keysRegex).matcher(keyString);

        if (! keysMatcher.find()) {
            System.out.println("<p>A key is missing</p>");
        } else {
            String startKey = keysMatcher.group(1);
            String endKey = keysMatcher.group(2);

            String regex = Pattern.quote(startKey) + "(\\d*(?:\\.\\d+)?)" + Pattern.quote(endKey);
            Matcher matcher = Pattern.compile(regex).matcher(text);

            double sum = 0d;
            while (matcher.find() && ! matcher.group(1).isEmpty()) {
                sum += Double.parseDouble(matcher.group(1));
            }

            if (sum != 0d) {
                if (sum == (int) sum) {
                    System.out.printf("<p>The total value is: <em>%d</em></p>", (int) sum);
                } else {
                    System.out.printf("<p>The total value is: <em>%.2f</em></p>%n", sum);
                }
            } else {
                System.out.println("<p>The total value is: <em>nothing</em></p>");
            }
        }
    }
}
