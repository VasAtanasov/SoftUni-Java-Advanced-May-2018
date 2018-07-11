package Z_Exams.exam08May2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex04RoyalAccounting {
    private static final String REGEX;
    private static BufferedReader reader;
    private static Pattern pattern;
    private static Set<String> employeeNames;
    private static Map<String, Team> teamMap;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        REGEX = "^(?<employee>[A-Za-z]+);(?<hours>(?:-|\\+)*\\d+);(?<payment>(?:-|\\+)*\\d+(?:\\.\\d+)?);(?<team>[A-Za-z]+)$";
        pattern = Pattern.compile(REGEX);
        employeeNames = new HashSet<>();
        teamMap = new LinkedHashMap<>();
    }

    public static void main(String[] args) throws IOException {
        String input;
        while (! "Pishi kuf i da si hodim".equals(input = reader.readLine())) {
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                String employeeName = matcher.group("employee");
                if (! employeeNames.contains(employeeName)) {
                    employeeNames.add(employeeName);
                    String team = matcher.group("team");
                    int hours = Integer.parseInt(matcher.group("hours"));
                    double payment = Double.parseDouble(matcher.group("payment"));

                    teamMap.putIfAbsent(team, new Team(team));
                    teamMap.get(team).add(new Employee(employeeName, hours, payment));
                }
            }
        }

        teamMap.values().stream()
                .sorted()
                .forEach(System.out::print);

    }
}

class Team implements Comparable<Team> {
    private String name;
    private List<Employee> employees;

    Team(String name) {
        this.name = name;
        this.employees = new ArrayList<>();

    }

    private String getName() {
        return this.name;
    }

    private List<Employee> getEmployees() {
        return this.employees;
    }

    void add(Employee employee) {
        this.getEmployees().add(employee);
    }

    private double getMoneyGathered() {
        return this.getEmployees().stream()
                .map(Employee::getMonthlyIncome)
                .reduce(0.0, Double::sum);
    }

    private String getEmployeeString() {
        StringBuilder employees = new StringBuilder();
        this.getEmployees().stream()
                .sorted()
                .forEach(employee -> employees.append(employee).append(System.lineSeparator()));
        return employees.toString();
    }

    @Override
    public int compareTo(Team other) {
        return Double.compare(other.getMoneyGathered(), this.getMoneyGathered());
    }

    @Override
    public String toString() {
        return String.format("Team - %s%n%s", this.getName(), this.getEmployeeString());
    }
}

class Employee implements Comparable<Employee> {
    private static final double WORKING_DAYS = 30.0;
    private static final double TWENTY_FOUR_HOURS = 24.0;

    private String name;
    private int workHours;
    private double dailyPayment;

    Employee(String name, int workHours, double dailyPayment) {
        this.name = name;
        this.workHours = workHours;
        this.dailyPayment = dailyPayment;
    }

    private String getName() {
        return this.name;
    }

    private int getWorkHours() {
        return this.workHours;
    }

    private double getDailyPayment() {
        return this.dailyPayment;
    }

    private double getDailyIncome() {
        return (this.getDailyPayment() * this.getWorkHours()) / TWENTY_FOUR_HOURS;
    }

    double getMonthlyIncome() {
        return ((this.getDailyPayment() * this.getWorkHours()) / TWENTY_FOUR_HOURS) * WORKING_DAYS;
    }

    @Override
    public int compareTo(Employee other) {
        int index = Integer.compare(other.getWorkHours(), this.getWorkHours());
        index = index != 0 ? index : Double.compare(other.getDailyIncome(), this.getDailyIncome());
        return index != 0 ? index : this.getName().compareTo(other.getName());
    }

    @Override
    public String toString() {
        return String.format("$$$%s - %s - %.6f", this.getName(), this.getWorkHours(), this.getDailyIncome());
    }
}
