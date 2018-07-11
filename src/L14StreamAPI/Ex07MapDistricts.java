package L14StreamAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Ex07MapDistricts {
    private static BufferedReader reader;
    private static Map<String, City> cities;
    private static int filterFactor;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        cities = new LinkedHashMap<>();
    }

    public static void main(String[] args) throws IOException {
        getCities();
        filterFactor = Integer.parseInt(reader.readLine());

        cities.values().stream()
                .filter(city -> city.getTotalPopulation() > filterFactor)
                .sorted()
                .forEach(System.out::println);
    }

    private static void getCities() throws IOException {
        String[] input = reader.readLine().split("\\s+");

        for (String string : input) {
            String[] tokens = string.split(":");
            String city = tokens[0];
            int district = Integer.parseInt(tokens[1]);
            cities.putIfAbsent(city, new City(city));
            cities.get(city).add(district);
        }
    }
}

class City implements Comparable<City> {
    private String name;
    private List<Integer> districts;

    City(String name) {
        this.name = name;
        this.districts = new ArrayList<>();
    }

    void add(int district) {
        this.districts.add(district);
    }

    String getName() {
        return this.name;
    }

    private String getDistricts() {
        return this.districts
                .stream()
                .sorted((a, b) -> Integer.compare(b, a))
                .limit(5)
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
    }

    int getTotalPopulation() {
        return this.districts.stream()
                .reduce(0, (a, b) -> a + b);
    }

    @Override
    public int compareTo(City other) {
        return Integer.compare(other.getTotalPopulation(), this.getTotalPopulation());
    }

    @Override
    public String toString() {
        return String.format("%s: %s", this.getName(), this.getDistricts());
    }
}