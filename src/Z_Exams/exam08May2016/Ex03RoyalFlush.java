package Z_Exams.exam08May2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex03RoyalFlush {
    private static BufferedReader reader;
    private static StringBuilder input;
    private static List<Card> cards;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        input = new StringBuilder();
        cards = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {
        getAllInputLines();
        mapAllCards();

        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getPower() == 10) {
                RoyalFlush royalFlush = new RoyalFlush(cards.get(i).getSuit(), 10);
                for (int j = i + 1; j < cards.size(); j++) {
                    if (! cards.get(j).getSuit().equals(royalFlush.getSuit())) {
                        continue;
                    }
                    if (! royalFlush.add(cards.get(j).getPower())) {
                        break;
                    }
                    if (royalFlush.isRoyalFlush()) {
                        System.out.println(royalFlush);
                        RoyalFlush.increment();
                        break;
                    }
                }
            }
        }

        System.out.println(String.format("Royal's Royal Flushes - %d.", RoyalFlush.getCOUNT()));
    }

    private static void mapAllCards() {
        List<String> suitsFullNames = Arrays.asList("Clubs", "Diamonds", "Hearts", "Spades");
        List<String> powers = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A");
        List<String> suits = Arrays.asList("c", "d", "h", "s");

        Matcher matcher = Pattern.compile("([0-9]+|[JQKA])([chds])").matcher(input.toString());
        while (matcher.find()) {
            int power = powers.indexOf(matcher.group(1));
            int suitIndex = suits.indexOf(matcher.group(2));
            String suit = suitsFullNames.get(suitIndex);
            cards.add(new Card(power, suit));
        }
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
        private int lastPower;

        RoyalFlush(String suit, int lastPower) {
            this.suit = suit;
            this.lastPower = lastPower;
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

        String getSuit() {
            return this.suit;
        }

        boolean isRoyalFlush() {
            return lastPower == 14;
        }

        boolean add(int power) {
            if (lastPower + 1 == power) {
                lastPower = power;
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

    static class Card {
        private int power;
        private String suit;

        Card(int power, String suit) {
            this.power = power;
            this.suit = suit;
        }

        int getPower() {
            return this.power;
        }

        String getSuit() {
            return this.suit;
        }

        @Override
        public String toString() {
            return String.format("%d %s", this.getPower(), this.getSuit());
        }
    }
}

