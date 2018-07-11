package L10StringProcessing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex07ValidUserNames {
    private static BufferedReader reader;
    private static Pattern pattern;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        pattern = Pattern.compile("^[A-Za-z0-9_-]{3,16}$");
    }

    public static void main(String[] args) throws IOException {
        StringBuilder result = new StringBuilder();
        boolean allowPrint = false;

        String input;
        while (! "END".equals(input = reader.readLine())) {
            if (input.equals("END")) {
                break;
            }

            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                result.append("valid\n");
                allowPrint = true;
            } else if (! "".equals(input)) {
                result.append("invalid\n");
            }
        }

        if (allowPrint) {
            System.out.println(result.toString());
        }
    }
}

