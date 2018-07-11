package L11StringProcessingExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex04UnicodeCharacters {
    private static BufferedReader reader;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        String text = reader.readLine();
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            output.append(String.format("\\u00%s", Integer.toHexString(text.charAt(i))));
        }

        System.out.println(output.toString());
    }
}
