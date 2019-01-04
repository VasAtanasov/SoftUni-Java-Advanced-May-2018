package Z_Exams.exam08Feb2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Ex02LettersChangeNumbers01 {
    private static BufferedReader reader;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        System.out.println(String.format("%.2f", Arrays.stream(reader.readLine().split("\\s+"))
                .mapToDouble(Ex02LettersChangeNumbers01::mapToNumber)
                .reduce(0.0, (a, b) -> a + b)));
    }

    private static double mapToNumber(String string) {
        char frontLetter = string.substring(0, 1).charAt(0);
        char endLetter = string.substring(string.length() - 1).charAt(0);
        double number = Double.valueOf(string.substring(1, string.length() - 1));

        if (Character.isUpperCase(frontLetter)) {
            number /= (frontLetter - 64);
        } else if (Character.isLowerCase(frontLetter)) {
            number *= (frontLetter - 96);
        }

        if (Character.isUpperCase(endLetter)) {
            number -= (endLetter - 64);
        } else if (Character.isLowerCase(endLetter)) {
            number += (endLetter - 96);
        }
        return number;
    }
}
