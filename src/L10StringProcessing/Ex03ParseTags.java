package L10StringProcessing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex03ParseTags {
    private static BufferedReader reader;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        String input = reader.readLine();

        while (input.contains("<upcase>")) {
            String leftText = input.substring(0,input.indexOf("<"));
            String textToUpcase = input.substring(input.indexOf(">") + 1);
            String rightText = textToUpcase.substring(textToUpcase.indexOf(">") + 1);
            textToUpcase = textToUpcase.substring(0, textToUpcase.indexOf("<"));

            input = leftText + textToUpcase.toUpperCase() + rightText;
        }

        System.out.println(input);

    }
}

