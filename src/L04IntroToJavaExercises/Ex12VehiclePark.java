package L04IntroToJavaExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Ex12VehiclePark {
    private static BufferedReader reader;
    private static final String REGEX;
    private static Pattern pattern;
    private static List<Vehicle> vehicles;
    private static int sold;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        REGEX = "(?<type>[bcv])(?<seatCount>[0-9]+)";
        pattern = Pattern.compile(REGEX);
        sold = 0;
    }

    public static void main(String[] args) throws IOException {
        registerVehicles();

        String input;
        while (! "End of customers!".equals(input = reader.readLine())) {
            String[] tokens = input.split("\\s+");
            String searchedType = tokens[0];
            int searchedSeatCount = Integer.parseInt(tokens[2]);
            boolean foundMatch = false;

            for (int i = 0; i < vehicles.size(); i++) {
                String type = vehicles.get(i).getType();
                int seatCount = vehicles.get(i).getSeatCount();

                if (searchedType.equals(type) && searchedSeatCount == seatCount) {
                    System.out.println(String.format("Yes, sold for %d$", vehicles.get(i).getPrice()));
                    vehicles.remove(i);
                    foundMatch = true;
                    sold++;
                    break;
                }
            }

            if (! foundMatch) {
                System.out.println("No");
            }

        }

        String outputVehicles = vehicles.stream().map(String::valueOf).collect(Collectors.joining(", "));
        System.out.println(String.format("Vehicles left: %s",outputVehicles));
        System.out.println(String.format("Vehicles sold: %d",sold));
    }

    private static void registerVehicles() throws IOException {
        vehicles = Arrays.stream(reader.readLine().split("\\s+"))
                .map(s -> {
                    Matcher matcher = pattern.matcher(s);
                    if (matcher.find()) {
                        String type = matcher.group("type");
                        int seatCount = Integer.parseInt(matcher.group("seatCount"));
                        return new Vehicle(type, seatCount);
                    }
                    return null;
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }
}


class Vehicle {
    private String type;
    private int seatCount;
    private int price;
    private String code;

    Vehicle(String type, int seatCount) {
        this.code = type + seatCount;
        this.setType(type);
        this.seatCount = seatCount;
        this.setPrice(type);
    }

    private void setPrice(String type) {
        this.price = type.charAt(0) * this.seatCount;
    }

    private void setType(String type) {
        switch (type) {
            case "c":
                this.type = "Car";
                break;
            case "b":
                this.type = "Bus";
                break;
            case "v":
                this.type = "Van";
                break;
        }
    }

    String getType() {
        return this.type;
    }

    int getSeatCount() {
        return this.seatCount;
    }

    int getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return this.code;
    }
}
