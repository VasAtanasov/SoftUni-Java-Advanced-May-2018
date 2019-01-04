package Z_Exams.exam03Jan2018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Ex04TheSwanStation {
    private static BufferedReader reader;
    private static Deque<Integer> theNumbers;
    private static Deque<Integer> numbers;
    private static List<Integer> savedNumbers;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        savedNumbers = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {
        readInput();
        while (! theNumbers.isEmpty()) {
            int theNumber = theNumbers.peekFirst();
            int number = numbers.removeFirst();

            if (number % theNumber == 0) {
                savedNumbers.add(number);
                theNumbers.removeFirst();
            } else {
                numbers.addLast(number + 1);
            }
        }

        System.out.println(savedNumbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ")));

    }

    private static void readInput() throws IOException {
        theNumbers = Arrays.stream(reader.readLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        numbers = Arrays.stream(reader.readLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));
    }
}
