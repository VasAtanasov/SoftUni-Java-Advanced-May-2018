package L03IntroToJava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex05TransportPrice {
    private static BufferedReader reader;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        double distance = Double.parseDouble(reader.readLine());
        String timeOfDay = reader.readLine();

        System.out.println(TravelCalculator.getTravelExpenses(distance,timeOfDay));

    }
}

class TravelCalculator {
    private static final double TAXI_INITIAL_TAX = 0.7;
    private static final double TAXI_DAYTIME_COST = 0.79;
    private static final double TAXI_NIGHTTIME_COST = 0.90;
    private static final double BUS_COST = 0.09;
    private static final double TRAIN_COST = 0.06;

    private TravelCalculator() {

    }

    static String getTravelExpenses(double distance, String timeOfDay) {
        double initial_tax = 0;
        double pricePerKm = 0;
        if (distance > 0 && distance < 20) {
            switch (timeOfDay) {
                case "day":
                    pricePerKm = TAXI_DAYTIME_COST;
                    break;
                case "night":
                    pricePerKm = TAXI_NIGHTTIME_COST;
                    break;
            }
            initial_tax = TAXI_INITIAL_TAX;
        } else if (distance < 100) {
            pricePerKm = BUS_COST;
        } else {
            pricePerKm = TRAIN_COST;
        }
        return String.format("%.2f",((distance * pricePerKm) + initial_tax));
    }
}
