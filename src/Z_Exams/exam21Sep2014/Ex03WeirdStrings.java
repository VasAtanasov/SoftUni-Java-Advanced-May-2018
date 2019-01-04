package Z_Exams.exam21Sep2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex03WeirdStrings {
    private static BufferedReader reader;
    private static List<Word> words = new ArrayList<>();
    private static int firstWordIndex;
    private static int secondWordIndex;
    private static int maxWight;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        firstWordIndex = 0;
        secondWordIndex = 1;
        maxWight = Integer.MIN_VALUE;
    }

    public static void main(String[] args) throws IOException {
        String input = reader.readLine().replaceAll("[\\/()\\|\\s\\\\]+", "");
        Matcher matcher = Pattern.compile("[A-Za-z]+").matcher(input);
        while (matcher.find()) {
            words.add(new Word(matcher.group()));
        }

        for (int i = 0; i < words.size() - 1; i++) {
            Word firstWord = words.get(i);
            Word secondWord = words.get(i+1);
            int sum = firstWord.getWeight() + secondWord.getWeight();
            if (sum >= maxWight) {
                maxWight = sum;
                firstWordIndex = i;
                secondWordIndex = i+1;
            }
        }
        System.out.println(words.get(firstWordIndex));
        System.out.println(words.get(secondWordIndex));

    }
}

class Word {
    private String word;
    private int weight;

    Word(String word) {
        this.word = word;
        this.setWeight(word.toLowerCase());
    }

    private void setWeight(String word) {
        this.weight = word.chars().map(c -> c - 96).sum();
    }

    int getWeight() {
        return this.weight;
    }

    @Override
    public String toString() {
        return this.word;
    }
}