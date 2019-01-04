package L10StringProcessing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex04SeriesOfLetters {
    private static BufferedReader reader;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        StringBuilder letters = new StringBuilder(reader.readLine());
        StringBuilder distinct = new StringBuilder();
        for (int i = 0; i < letters.length() - 1; i++) {
            if (! (letters.charAt(i) == letters.charAt(i + 1))) {
                distinct.append(letters.charAt(i));
                if (i == letters.length() - 2) {
                    distinct.append(letters.charAt(letters.length() - 1));
                }
            } else if (i == letters.length() - 2) {
                distinct.append(letters.charAt(letters.length() - 1));
            }
        }

        System.out.println(distinct.toString());
    }
}

