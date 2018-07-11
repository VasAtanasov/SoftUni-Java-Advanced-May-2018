package Z_Exams.exam08Feb2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex02LettersChangeNumbers {
    private static BufferedReader reader;
    private static String[] words;
    private static double totalSum;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        totalSum = 0;
    }

    public static void main(String[] args) throws IOException {
        getInput();

        for (String word : words) {
            char[] chars = word.toCharArray();
            char firstChar = chars[0];
            char lastChar = chars[chars.length - 1];
            double number = Double.parseDouble(word.substring(1, word.length() - 1));

            if (Character.isUpperCase(firstChar)) {
                number /= (firstChar - 64);
            } else if (Character.isLowerCase(firstChar)) {
                number *= (firstChar - 96);
            }

            if (Character.isUpperCase(lastChar)) {
                number -= (lastChar - 64);
            } else if (Character.isLowerCase(lastChar)) {
                number += (lastChar - 96);
            }

            totalSum += number;
        }

        System.out.println(String.format("%.2f",totalSum));

    }

    private static void getInput() throws IOException {
        words = reader.readLine().split("\\s+");
    }
}
