package L11StringProcessingExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex06MagicExchangeableWords {
    private static BufferedReader reader;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        String[] inputArray = reader.readLine().split("\\s+");
        long fartsWordCount = inputArray[0].chars().distinct().count();
        long secondWordCount = inputArray[1].chars().distinct().count();

        System.out.println(fartsWordCount == secondWordCount);
    }
}
