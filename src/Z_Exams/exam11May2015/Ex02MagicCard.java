package Z_Exams.exam11May2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Ex02MagicCard {
    private static BufferedReader reader;
    private static int totalPower;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        totalPower = 0;
    }

    public static void main(String[] args) throws IOException {
        String[] tokens = reader.readLine().split("\\s+");
        int position = "odd".equals(reader.readLine()) ? 1 : 0;
        Card magicCard = getCard.apply(reader.readLine());

        for (int i = position; i < tokens.length; i += 2) {
            Card card = getCard.apply(tokens[i]);
            int power = getPower.apply(magicCard, card);
            totalPower += power;
        }

        System.out.println(totalPower);
    }

    private static BiFunction<Card, Card, Integer> getPower = (magicCard, ourCard) -> {
        int power = ourCard.getPower();
        if (magicCard.getSuit().equals(ourCard.getSuit())) {
            power *= 2;
        } else if (magicCard.getPower() == ourCard.getPower()) {
            power *= 3;
        }
        return power;
    };

    private static Function<String, Card> getCard = s -> {
        String suit = s.substring(s.length() - 1);
        int power = Card.powers.indexOf(s.substring(0, s.length() - 1)) * 10;
        return new Card(suit, power);
    };
}

class Card {
    static List<String> powers;

    static {
        powers = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "J", "Q", "K", "A");
    }

    private String suit;
    private int power;

    Card(String suit, int power) {
        this.suit = suit;
        this.power = power;
    }

    String getSuit() {
        return this.suit;
    }

    int getPower() {
        return this.power;
    }
}