package L11StringProcessingExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex10MatchPhoneNumber {
    private static BufferedReader reader;
    private static final String REGEX = "^\\+359( |-)2\\1[0-9]{3}\\1[0-9]{4}$";

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        String number;
        while (!"end".equals(number = reader.readLine())) {
            if (number.matches(REGEX)) {
                System.out.println(number);
            }
        }

    }
}
