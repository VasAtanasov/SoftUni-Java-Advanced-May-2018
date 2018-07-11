package Z_Exams.exam03May2017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex02SimpleExpression {
    private static final String NUMBER_REGEX = "(?<number>[-+]?\\d+)";
    private static final Pattern pattern;
    private static BufferedReader reader;
    private static String text;
    private static Deque<BiFunction<BigDecimal, BigDecimal, BigDecimal>> actions;
    private static Deque<BigDecimal> numbers;
    private static BigDecimal sum;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        actions = new ArrayDeque<>();
        numbers = new ArrayDeque<>();
        sum = BigDecimal.ZERO;
        pattern = Pattern.compile(NUMBER_REGEX);
    }

    public static void main(String[] args) throws IOException {
        addNumbers();
        addActions();

        while (numbers.size() != 1) {
            BigDecimal firstNumber = numbers.removeFirst();
            BigDecimal secondNumber = numbers.removeFirst();
            BiFunction<BigDecimal, BigDecimal, BigDecimal> action = actions.removeFirst();
            numbers.addFirst(action.apply(firstNumber, secondNumber));
        }

        DecimalFormat df = new DecimalFormat("0.#######");
        System.out.println(String.format("%s", df.format(numbers.peekFirst())));

    }


    private static void addActions() {
        String[] tokens = Arrays.stream(text.split(NUMBER_REGEX))
                .filter(e -> ! e.isEmpty())
                .toArray(String[]::new);
        for (String token : tokens) {
            if (token.length() % 2 == 0) {
                actions.addLast(add());
            } else {
                actions.addLast(subtract());
            }
        }
    }

    private static void addNumbers() throws IOException {
        text = reader.readLine();
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            numbers.addLast(new BigDecimal(matcher.group()));
        }
    }

    private static BiFunction<BigDecimal, BigDecimal, BigDecimal> add() {
        return BigDecimal::add;
    }

    private static BiFunction<BigDecimal, BigDecimal, BigDecimal> subtract() {
        return BigDecimal::subtract;
    }
}
