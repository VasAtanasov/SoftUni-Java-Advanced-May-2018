package Z_Exams.exam08May2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex03RoyalFlush01 {
    private static final List<String> suitsFullNames;
    private static final List<String> powers;
    private static final List<String> suits;
    private static BufferedReader reader;
    private static StringBuilder input;
    private static Map<String, RoyalFlush> flushMap;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        input = new StringBuilder();
        flushMap = new HashMap<>();
        suitsFullNames = Arrays.asList("Clubs", "Diamonds", "Hearts", "Spades");
        powers = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A");
        suits = Arrays.asList("c", "d", "h", "s");
    }

    public static void main(String[] args) throws IOException {
        getAllInputLines();

        Matcher matcher = Pattern.compile("([0-9]+|[JQKA])([chds])").matcher(input.toString());
        while (matcher.find()) {
            int power = powers.indexOf(matcher.group(1));
            int suitIndex = suits.indexOf(matcher.group(2));
            String suit = suitsFullNames.get(suitIndex);

            flushMap.putIfAbsent(suit, new RoyalFlush(suit));
            RoyalFlush flush = flushMap.get(suit);

            if (! flush.add(power)) {
                flush.reset();
                flush.add(power);
                continue;
            }

            if (flush.isRoyalFlush()) {
                System.out.println(flush);
                flush.reset();
                RoyalFlush.increment();
            }
        }

        System.out.println(String.format("Royal's Royal Flushes - %d.", RoyalFlush.getCOUNT()));
    }

    private static void getAllInputLines() throws IOException {
        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            input.append(reader.readLine());
        }
    }

    static class RoyalFlush {
        private static int COUNT;

        private String suit;
        private int currentPower;

        RoyalFlush(String suit) {
            this.suit = suit;
            this.currentPower = 9;
        }

        static {
            COUNT = 0;
        }

        static void increment() {
            COUNT = COUNT + 1;
        }

        static int getCOUNT() {
            return COUNT;
        }

        void reset() {
            this.currentPower = 9;
        }

        boolean isRoyalFlush() {
            return currentPower == 14;
        }

        boolean add(int power) {
            if (currentPower + 1 == power) {
                currentPower = power;
                return true;
            } else {
                return false;
            }
        }

        @Override
        public String toString() {
            return String.format("Royal Flush Found - %s", this.suit);
        }
    }
}


