package L04IntroToJavaExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Ex12VehiclePark01 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> autoHouse = Arrays.stream(reader.readLine().toLowerCase().split("\\s+"))
                .collect(Collectors.toCollection(ArrayList::new));

        int soldCount = 0;

        String input = reader.readLine();
        while (! "End of customers!".equals(input)) {
            String[] tokens = input.split("\\s+");
            int seats = Integer.valueOf(tokens[2]);
            String vehicle = String.format("%s%s", tokens[0].charAt(0), seats).toLowerCase();

            if (autoHouse.contains(vehicle)) {
                int price = vehicle.charAt(0) * seats;
                System.out.println(String.format("Yes, sold for %d$", price));
                autoHouse.remove(autoHouse.indexOf(vehicle));
                soldCount++;
            } else {
                System.out.println("No");
            }
            input = reader.readLine();
        }

        System.out.println(String.format("Vehicles left: %s",
                autoHouse.stream().collect(Collectors.joining(", "))));
        System.out.println(String.format("Vehicles sold: %d", soldCount));
    }
}