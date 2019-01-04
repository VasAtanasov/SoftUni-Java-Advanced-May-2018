package L11StringProcessingExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex11ReplaceTag {
    private static BufferedReader reader;
    private static StringBuilder text;
    private static Pattern pattern;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        text = new StringBuilder();
        pattern = Pattern.compile("<a\\s*href=\\s*.*\\s*>\\s*.*\\s*<\\/a>", Pattern.MULTILINE);
    }

    public static void main(String[] args) throws IOException {
        String input = reader.readLine();
        while (! "END".equals(input)) {
            text.append(input);
            text.append("\n");
            input = reader.readLine();
        }

        Matcher matcher = pattern.matcher(text);

        String edited = text.toString();

        while (matcher.find()) {
            String href = matcher.group();
            String replaced = href.replace("<a","[URL").replace("</a>","[/URL]").replace(">","]");
            edited = edited.replace(href, replaced);

        }

        System.out.println(edited);
    }
}
