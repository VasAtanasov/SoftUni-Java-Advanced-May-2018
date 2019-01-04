package Z_Exams.exam03May2017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Ex01HandScore01 {
    private static BufferedReader reader;
    private static Deque<String> suits;
    private static int totalSum;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        totalSum = 0;
    }

    public static void main(String[] args) throws IOException {
        handOutCards();

        while (! suits.isEmpty()) {
            String suit = suits.removeFirst();
            int seqSum = getValue().apply(suit);
            int count = 1;
            while (suits.peekFirst() != null &&
                    suits.peek().substring(suits.peek().length() - 1).equals(suit.substring(suit.length() - 1))) {
                count++;
                seqSum += getValue().apply(suits.removeFirst());
            }
            totalSum += seqSum * count;
        }

        System.out.println(totalSum);

    }

    private static void handOutCards() throws IOException {
        suits = Arrays.stream(reader.readLine().split("\\s+"))
                .collect(Collectors.toCollection(ArrayDeque::new));
    }

    private static Function<String, Integer> getValue() {
        return e -> {
            String stringValue = e.substring(0, e.length() - 1);
            switch (stringValue) {
                case "J":
                    return 12;
                case "Q":
                    return 13;
                case "K":
                    return 14;
                case "A":
                    return 15;
                default:
                    return Integer.valueOf(stringValue);
            }
        };
    }


}

