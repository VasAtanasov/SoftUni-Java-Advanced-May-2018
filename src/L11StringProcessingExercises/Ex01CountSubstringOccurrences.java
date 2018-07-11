package L11StringProcessingExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex01CountSubstringOccurrences {
    private static BufferedReader reader;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        int count = 0;
        String text = reader.readLine().toLowerCase();
        String substringSearched = reader.readLine().toLowerCase();

        while (text.length() > 0) {
            if (text.startsWith(substringSearched)) {
                count++;
            }
            text = text.substring(1);
        }
        System.out.println(count);
    }
}
