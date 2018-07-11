package Z_Exams.exam21Sep2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex04Nuts {
    private static BufferedReader reader;
    private static final String REGEX;
    private static Pattern pattern;
    private static Map<String, Company> companies;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        REGEX = "(?<company>[A-Za-z]+)\\s+(?<nut>[A-Za-z]+)\\s+(?<amount>[0-9]+)";
        pattern = Pattern.compile(REGEX);
        companies = new TreeMap<>();
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            String input = reader.readLine();
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                String company = matcher.group("company");
                String type = matcher.group("nut");
                int amount = Integer.parseInt(matcher.group("amount"));

                companies.putIfAbsent(company, new Company(company));
                companies.get(company).add(type, amount);
            }
        }


        companies.values()
                .forEach(System.out::println);
    }
}

class Company {
    private String name;
    private Map<String, Nuts> nuts;

    Company(String name) {
        this.name = name;
        this.nuts = new LinkedHashMap<>();
    }

    void add(String type, int amount) {
        this.nuts.putIfAbsent(type, new Nuts(type));
        this.nuts.get(type).add(amount);
    }

    @Override
    public String toString() {
        return String.format("%s: %s", this.name, this.nuts.values().toString().replaceAll("[\\[\\]]", ""));
    }
}

class Nuts {
    private String type;
    private int amount;

    Nuts(String type) {
        this.type = type;
        this.amount = 0;
    }

    void add(int amount) {
        this.amount += amount;
    }

    @Override
    public String toString() {
        return String.format("%s-%dkg", this.type, this.amount);
    }
}
