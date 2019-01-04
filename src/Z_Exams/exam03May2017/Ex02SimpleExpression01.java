package Z_Exams.exam03May2017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex02SimpleExpression01 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<BigDecimal> numbers = new ArrayList<>();
        Pattern pattern = Pattern.compile("(?<number>[-+]?\\d+)");
        String input = reader.readLine();

        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String match = matcher.group("number");

            int index = input.indexOf(match);
            String symbols = "";

            if (index != 0) {
                symbols = input.substring(0, index);
                input = input.substring(index + match.length());
            } else {
                input = input.substring(index + match.length());
            }

            BigDecimal number = new BigDecimal(match);
            int multiplier = symbols.length() % 2 == 0 ? 1 : - 1;
            numbers.add(number.multiply(BigDecimal.valueOf(multiplier)));

        }

        DecimalFormat df = new DecimalFormat("0.#######");
        System.out.println(df.format(numbers.stream().reduce(BigDecimal.ZERO, BigDecimal::add)));

    }
}

