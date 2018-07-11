package L11StringProcessingExercises;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex16ExtractHyperlinks {
    private static Scanner scanner;

    static {
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        String line = scanner.nextLine();
        while (!"END".equals(line)) {
            sb.append(line).append(System.lineSeparator());
            line = scanner.nextLine();
        }

        String regex = "<a[^>]*href\\s*=\\s*('(:?[^>']+)'|\"(:?[^>\"]+)\"|\\s*(:?[^> ]+)\\s*)[^>]*>";
        Matcher matcher = Pattern.compile(regex).matcher(sb.toString());

        while (matcher.find()) {
            for (int groupId = 2; groupId <= matcher.groupCount(); groupId++) {
                if (matcher.group(groupId) != null) {
                    System.out.println(matcher.group(groupId));
                    break;
                }
            }
        }
    }
}
