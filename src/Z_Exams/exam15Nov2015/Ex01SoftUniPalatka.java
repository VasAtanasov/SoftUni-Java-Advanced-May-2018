package Z_Exams.exam15Nov2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex01SoftUniPalatka {
    private static BufferedReader reader;
    private static int beds, meals;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        int peopleCount = Integer.parseInt(reader.readLine());
        int lines = Integer.parseInt(reader.readLine());

        for (int i = 0; i < lines; i++) {
            String[] tokens = reader.readLine().split("\\s+");
            String service = tokens[0];
            int count = Integer.parseInt(tokens[1]);
            String type = tokens[2];

            switch (service) {
                case "tents":
                    countBeds(count, type);
                    break;
                case "food":
                    countMeals(count, type);
                    break;
                case "rooms":
                    countBeds(count, type);
                    break;
            }
        }


        if (peopleCount <= beds) {
            System.out.println(String.format("Everyone is happy and sleeping well. Beds left: %d", beds - peopleCount));
        } else {
            System.out.println(String.format("Some people are freezing cold. Beds needed: %d", peopleCount - beds));
        }

        if (peopleCount <= meals) {
            System.out.println(String.format("Nobody left hungry. Meals left: %d", meals - peopleCount));
        } else {
            System.out.println(String.format("People are starving. Meals needed: %d", peopleCount - meals));
        }
    }

    private static void countMeals(int count, String type) {
        int food = ("musaka".equals(type) ? 2 : 0) * count;
        meals += food;
    }

    private static void countBeds(int count, String type) {
        int compartmentBeds = 0;
        switch (type) {
            case "single":
                compartmentBeds = 1;
                break;
            case "double":
            case "normal":
                compartmentBeds = 2;
                break;
            case "firstClass":
            case "triple":
                compartmentBeds = 3;
                break;
        }
        beds += (compartmentBeds * count);
    }


}
