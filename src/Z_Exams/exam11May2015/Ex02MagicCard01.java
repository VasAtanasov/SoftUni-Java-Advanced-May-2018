package Z_Exams.exam11May2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Ex02MagicCard01 {
    static List<String> powers;
    private static BufferedReader reader;
    private static List<String> cards;
    private static int magicCardPower;
    private static String magicCardSuit;

    static {
        powers = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "J", "Q", "K", "A");
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        cards = Arrays.stream(reader.readLine().split("\\s+"))
                .collect(Collectors.toCollection(ArrayList::new));
        int position = "odd".equals(reader.readLine()) ? 1 : 0;
        String magicCard = reader.readLine();
        magicCardPower = powers.indexOf(magicCard.substring(0, magicCard.length() - 1)) * 10;
        magicCardSuit = magicCard.substring(magicCard.length() - 1);

        int handValue = 0;

        for (int i = position; i < cards.size(); i += 2) {
            handValue += getValue(cards.get(i));
        }

        System.out.println(handValue);
    }

    private static Integer getValue(String c) {
        int power = powers.indexOf(c.substring(0, c.length() - 1)) * 10;
        String suit = c.substring(c.length() - 1);

        if (power == magicCardPower) {
            power *= 3;
        } else if (suit.equals(magicCardSuit)) {
            power *= 2;
        }
        return power;
    }

}