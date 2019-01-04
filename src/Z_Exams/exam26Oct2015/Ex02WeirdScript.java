package Z_Exams.exam26Oct2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex02WeirdScript {
    private static BufferedReader reader;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        int key = Integer.parseInt(reader.readLine()) % 52;

        String keyString = getKeyString(key);
        Pattern pattern = Pattern.compile(getRegex(keyString));

        String textString = getTextString();

        StringBuilder output = new StringBuilder();
        Matcher matcher = pattern.matcher(textString);
        while (matcher.find()) {
            output.append(matcher.group("string"))
                    .append(System.lineSeparator());
        }

        System.out.println(output.toString());
    }

    private static String getTextString() throws IOException {
        StringBuilder text = new StringBuilder();
        String input;
        while (! "End".equals(input = reader.readLine())) {
            text.append(input);
        }
        return text.toString();
    }

    private static String getRegex(String keyString) {
        return keyString + "(?<string>[^\\n]*?)" + keyString;
    }

    private static String getKeyString(int key) {
        if (key > 0 && key < 27) {
            int letter = 96 + key;
            return String.format("%c%c", letter, letter);
        } else if (key == 0) {
            return "ZZ";
        } else {
            int letter = 38 + key;
            return String.format("%c%c", letter, letter);
        }
    }
}
