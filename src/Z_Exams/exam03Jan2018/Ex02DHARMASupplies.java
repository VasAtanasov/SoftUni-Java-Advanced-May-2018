package Z_Exams.exam03Jan2018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Ex02DHARMASupplies {
    private static BufferedReader reader;
    private static int lineCount;
    private static int crateCount;
    private static int n;
    private static final String CRATE_REGEX;
    private static final String FOOD_DRINK_REGEX;
    private static Pattern foodDrinkPattern;
    private static Pattern cratePattern;
    private static List<Crate> drinkCrates;
    private static List<Crate> foodCrates;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        lineCount = 0;
        crateCount = 0;
        CRATE_REGEX = "\\[.*?]";
        FOOD_DRINK_REGEX = "(#(?<tag>[0-9a-z]+))(?<body>[A-Za-z0-9\\s]*)\\1";
        foodDrinkPattern = Pattern.compile(FOOD_DRINK_REGEX);
        cratePattern = Pattern.compile(CRATE_REGEX);
        drinkCrates = new ArrayList<>();
        foodCrates = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {
        collectCrates();
        filterCrates();

        int validCrates = drinkCrates.size() + foodCrates.size();
        int foodSum = getFoodSum();
        int drinkSum = getDrinkSum();


        if (validCrates > 0) {
            System.out.println(String.format("Number of supply crates: %d", validCrates));
            System.out.println(String.format("Amount of food collected: %d", foodSum));
            System.out.println(String.format("Amount of drinks collected: %d", drinkSum));
        } else {
            System.out.println("No supplies found!");
        }


    }

    private static int getDrinkSum() {
        return drinkCrates.stream()
                .mapToInt(crate -> {
                    int bodySum = crate.getBody().chars().sum();
                    int tagSum = crate.getTag().chars().sum();
                    return bodySum * tagSum;
                })
                .sum();
    }

    private static int getFoodSum() {
        return foodCrates.stream()
                .mapToInt(crate -> {
                    int bodySum = crate.getBody().chars().distinct().sum();
                    return bodySum * crate.getTagLength();
                })
                .sum();
    }

    private static void filterCrates() {
        n = crateCount / lineCount;
        drinkCrates = drinkCrates.stream()
                .filter(crate -> crate.getTagLength() == n)
                .collect(Collectors.toCollection(ArrayList::new));
        foodCrates = foodCrates.stream()
                .filter(crate -> crate.getTagLength() == n)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private static void collectCrates() throws IOException {
        String input;
        while (! "Collect".equals(input = reader.readLine())) {
            Matcher crateMatcher = cratePattern.matcher(input);
            while (crateMatcher.find()) {
                crateCount++;
            }

            Matcher matcher = foodDrinkPattern.matcher(input);
            while (matcher.find()) {
                String tag = matcher.group("tag");
                String body = matcher.group("body");

                if (tag.matches("[0-9]+")) {
                    foodCrates.add(new Crate(tag, body));
                } else if (tag.matches("[a-z]+")) {
                    drinkCrates.add(new Crate(tag, body));
                }
            }
            lineCount++;
        }
    }

}

class Crate {
    private String tag;
    private String body;

    Crate(String tag, String body) {
        this.tag = tag;
        this.body = body;
    }

    int getTagLength() {
        return tag.length();
    }

    String getTag() {
        return this.tag;
    }

    String getBody() {
        return this.body;
    }
}