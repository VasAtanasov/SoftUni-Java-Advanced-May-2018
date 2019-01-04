package Z_Exams.exam28Feb2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex03SoftUniNumerals01 {
    private static final String REGEX;
    private static Pattern pattern;
    private static BufferedReader reader;
    private static List<String> codes = Arrays.asList("aa", "aba", "bcc", "cc", "cdc");

    static {
        REGEX = "aa|aba|bcc|cc|cdc";
        pattern = Pattern.compile(REGEX);
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        String input = reader.readLine();
        StringBuilder sb = new StringBuilder();
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            sb.append(codes.indexOf(matcher.group()));
        }

        System.out.println(new BigInteger(sb.toString(),5));
    }
}
