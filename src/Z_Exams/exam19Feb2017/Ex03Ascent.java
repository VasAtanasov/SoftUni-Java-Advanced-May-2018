package Z_Exams.exam19Feb2017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex03Ascent {
    private static final String REGEX;
    private static BufferedReader reader;
    private static Pattern pattern;
    private static Map<String, Word> words;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        REGEX = "(?<code>,|_)(?<text>[A-Za-z]+)(?<digit>[0-9])";
        pattern = Pattern.compile(REGEX);
        words = new LinkedHashMap<>();
    }

    public static void main(String[] args) throws IOException {
        String message;
        while (! "Ascend".equals(message = reader.readLine())) {
            for (Word word : words.values()) {
                message = message.replaceAll(word.getEncryption(), word.getDecryption());
            }

            Matcher matcher = pattern.matcher(message);
            while (matcher.find()) {
                String code = matcher.group("code");
                String text = matcher.group("text");
                int digit = Integer.parseInt(matcher.group("digit"));
                String replace = decryptWord(code, text, digit);
                message = message.replaceAll(matcher.group(), replace);
                words.putIfAbsent(matcher.group(), new Word(matcher.group(), replace));
            }

            System.out.println(message);
        }
    }

    private static String decryptWord(String code, String text, int digit) {
        StringBuilder newWord = new StringBuilder();
        if (code.equals("_")) {
            digit *= - 1;
        }
        for (Character ch : text.toCharArray()) {
            newWord.append(String.format("%c", ch + digit));
        }
        return newWord.toString();
    }
}

class Word {
    private String encryption;
    private String decryption;

    Word(String encryption, String decryption) {
        this.encryption = encryption;
        this.decryption = decryption;
    }

    String getEncryption() {
        return this.encryption;
    }

    String getDecryption() {
        return this.decryption;
    }
}
