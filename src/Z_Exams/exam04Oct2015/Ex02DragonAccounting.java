package Z_Exams.exam04Oct2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayDeque;
import java.util.Deque;

public class Ex02DragonAccounting {
    private static final int PAY_DAY = 30;
    private static BufferedReader reader;
    private static BigDecimal budget;
    private static int day;
    private static Deque<WorkGroup> workGroups;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        day = 1;
        workGroups = new ArrayDeque<>();
    }

    public static void main(String[] args) throws IOException {
        getBudget();
        String input;
        while (! "END".equals(input = reader.readLine())) {
            String[] tokens = input.split(";");
            long hired = Long.parseLong(tokens[0]);
            BigDecimal salary = new BigDecimal(tokens[2]);
            workGroups.addLast( new WorkGroup(hired, salary));

            workGroups.forEach(WorkGroup::giveRaise);

            giveSalaries();

            fireEmployees(tokens[1]);

            processEvents(tokens);

            if (budget.compareTo(BigDecimal.ZERO) <= 0) {
                System.out.println(String.format("BANKRUPTCY: %s", budget.setScale(4, RoundingMode.DOWN).abs()));
                return;
            }

            workGroups.forEach(WorkGroup::addWorkDay);
            day++;
        }

        long employeesCount = workGroups.stream()
                .map(WorkGroup::getPeopleCount)
                .reduce(0L, Long::sum);
        System.out.println(String.format("%d %s", employeesCount, budget.setScale(4, RoundingMode.DOWN)));
    }

    private static void processEvents(String[] tokens) {
        for (int i = 3; i < tokens.length; i++) {
            String[] events = tokens[i].split(":");
            String event = events[0];
            BigDecimal value = new BigDecimal(events[1]);

            switch (event) {
                case "Previous years deficit":
                case "Machines":
                case "Taxes":
                    budget = budget.subtract(value);
                    break;
                case "Product development":
                case "Unconditional funding":
                    budget = budget.add(value);
                    break;
            }
        }
    }

    private static void giveSalaries() {
        if (day % PAY_DAY == 0) {
            BigDecimal totalForSalaries = workGroups.stream()
                    .map(WorkGroup::getSalary)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            budget = budget.subtract(totalForSalaries);
        }
    }

    private static void fireEmployees(String token) {
        long toFire = Long.parseLong(token);
        while (toFire > 0 && ! workGroups.isEmpty()) {
            long peopleCount = workGroups.peekFirst().getPeopleCount();
            if (peopleCount - toFire > 0) {
                workGroups.peekFirst().setPeopleCount(peopleCount - toFire);
            } else {
                workGroups.removeFirst();
            }
            toFire -= peopleCount;
        }
    }

    private static void getBudget() throws IOException {
        budget = new BigDecimal(reader.readLine());
    }

}

class WorkGroup {
    private static final BigDecimal RAISE = new BigDecimal("1.006");
    private static final BigDecimal MONTH = new BigDecimal("30");

    private long peopleCount;
    private BigDecimal salary;
    private int totalWorkDays;
    private int currentMonthDays;

    WorkGroup(long peopleCount, BigDecimal salary) {
        this.peopleCount = peopleCount;
        this.salary = salary;
        this.totalWorkDays = 1;
        this.currentMonthDays = 1;
    }

    void addWorkDay() {
        this.totalWorkDays++;
        this.currentMonthDays++;
    }

    long getPeopleCount() {
        return this.peopleCount;
    }

    void setPeopleCount(long peopleCount) {
        this.peopleCount = peopleCount;
    }

    private void resetMonthDays() {
        this.currentMonthDays = 0;
    }

    void giveRaise() {
        if (this.totalWorkDays % 365 == 0) {
            this.salary = salary.multiply(RAISE);
        }
    }

    private BigDecimal getDailySalary() {
        return salary.divide(MONTH, 9, RoundingMode.UP)
                .setScale(7, RoundingMode.DOWN);
    }

    BigDecimal getSalary() {
        BigDecimal total = this.getDailySalary()
                .multiply(BigDecimal.valueOf(this.currentMonthDays))
                .multiply(BigDecimal.valueOf(this.peopleCount));
        this.resetMonthDays();
        return total;
    }

}
