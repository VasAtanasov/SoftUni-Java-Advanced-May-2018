package L11StringProcessingExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex03TextFilter {
    private static BufferedReader reader;
    private static String[] bannedWords;
    private static String[] bannedWordsReplacements;
    private static String text;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        getInput();

        for (int i = 0; i < bannedWords.length; i++) {
            text = text.replaceAll(bannedWords[i], bannedWordsReplacements[i]);
        }

        System.out.println(text);

    }

    private static void getInput() throws IOException {
        bannedWords = reader.readLine().split(", ");
        bannedWordsReplacements = new String[bannedWords.length];
        generateBannedWordsReplacements();
        text = reader.readLine();
    }

    private static void generateBannedWordsReplacements() {
        for (int i = 0; i < bannedWords.length; i++) {
            bannedWordsReplacements[i] = generateFrom("*", bannedWords[i].length());
        }
    }

    private static String generateFrom(String input, int times) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < times; i++) {
            builder.append(input);
        }
        return builder.toString();
    }
}
