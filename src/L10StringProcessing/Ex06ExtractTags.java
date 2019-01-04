package L10StringProcessing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex06ExtractTags {
    private static BufferedReader reader;
    private static Pattern pattern;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        pattern = Pattern.compile("<.*?>");
    }

    public static void main(String[] args) throws IOException {
        String input = reader.readLine();

        while (! "END".equals(input)) {
            Matcher matcher = pattern.matcher(input);
            while (matcher.find()) {
                System.out.println(matcher.group());
            }
            input = reader.readLine();
        }
    }
}
