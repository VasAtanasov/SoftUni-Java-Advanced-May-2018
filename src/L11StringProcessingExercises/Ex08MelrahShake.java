package L11StringProcessingExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex08MelrahShake {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        String pattern = reader.readLine();

        while (input.contains(pattern) && pattern.length() != 0) {
            int leftIndex = input.indexOf(pattern);
            int rightIndex = input.lastIndexOf(pattern);

            if (leftIndex < 0 || rightIndex < 0) {
                break;
            }

            String left = input.substring(0, leftIndex);
            String mid = input.substring(leftIndex + pattern.length(), rightIndex);
            String right = input.substring(rightIndex + pattern.length());

            input = left + mid + right;
            System.out.println("Shaked it.");
            pattern = makeNewPattern(pattern);
        }

        System.out.println("No shake.");
        System.out.println(input);
    }

    private static String makeNewPattern(String pattern) {
        int index = pattern.length() / 2;
        StringBuilder sb = new StringBuilder(pattern);
        sb.deleteCharAt(index);
        return sb.toString();
    }
}
