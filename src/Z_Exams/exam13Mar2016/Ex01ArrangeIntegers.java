package Z_Exams.exam13Mar2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Ex01ArrangeIntegers {
    private static BufferedReader reader;
    private static List<Number> numbers;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        numbers = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {
        getNumbers();

        System.out.println(numbers.stream()
                .sorted()
                .map(Number::toString)
                .collect(Collectors.joining(", ")));
    }

    private static void getNumbers() throws IOException {
        String[] tokens = reader.readLine().split(", ");
        for (String token : tokens) {
            numbers.add(new Number(token));
        }
    }
}

class Number implements Comparable<Number> {
    private static final List<String> numberNames;

    static {
        numberNames = Arrays.asList("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
    }

    private int value;
    private String name;

    Number(String value) {
        this.setValue(value);
        this.setName(value);
    }

    int getValue() {
        return this.value;
    }

    String getName() {
        return this.name;
    }

    private void setValue(String value) {
        this.value = Integer.parseInt(value);
    }

    private void setName(String value) {
        String[] digits = value.split("");
        this.name = Arrays.stream(digits)
                .map(Integer::parseInt)
                .map(numberNames::get)
                .collect(Collectors.joining("-"));
    }

    @Override
    public int compareTo(Number other) {
        int index = this.getName().compareTo(other.getName());
        return index != 0 ? index : Integer.compare(this.getValue(), other.getValue());
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}