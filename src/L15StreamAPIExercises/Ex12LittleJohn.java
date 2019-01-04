package L15StreamAPIExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex12LittleJohn {
    private static final String REGEX = "(?<small>>-{5}>)|(?<medium>>{2}-{5}>)|(>{3}-{5}>{2})";
    private static BufferedReader reader;
    private static StringBuilder text = new StringBuilder();
    private static int small, medium, large;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        small = 0;
        medium = 0;
        large = 0;
    }

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 4; i++) {
            text.append(reader.readLine()).append(" ");
        }

        Matcher matcher = Pattern.compile(REGEX).matcher(text);
        while (matcher.find()) {
            if (matcher.group("small") != null) {
                small++;
            } else if (matcher.group("medium") != null) {
                medium++;
            } else {
                large++;
            }
        }

        int appended = Integer.parseInt(String.format("%d%d%d", small, medium, large));
        String binary = Integer.toBinaryString(appended);
        binary = binary + new StringBuilder(binary).reverse().toString();
        System.out.println(Integer.parseInt(binary, 2));
    }

}
