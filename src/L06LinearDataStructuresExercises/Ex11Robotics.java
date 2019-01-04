package L06LinearDataStructuresExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Ex11Robotics { // time limit 80/100
    private static BufferedReader reader;
    private static long startTime;
    private static List<Robot> robots;
    private static Deque<String> products;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        robots = new ArrayList<>();
        products = new ArrayDeque<>();
    }

    public static void main(String[] args) throws IOException {
        readInput();

        while (! products.isEmpty()) {
            String product = products.removeFirst();
            startTime += 1;
            robots.forEach(Robot::process);
            boolean allBusy = true;

            for (Robot robot : robots) {
                if (robot.isIdle()) {
                    allBusy = false;
                    robot.startProcessing();
                    System.out.println(String.format("%s - %s [%s]", robot, product, calculateTime(startTime)));
                    break;
                }
            }

            if (allBusy) {
                products.addLast(product);
            }
        }
    }

    private static String calculateTime(long seconds) {
        long sec = seconds % 60;
        long minutes = seconds % 3600 / 60;
        long hours = seconds % 86400 / 3600;
        DecimalFormat df = new DecimalFormat("00");
        return String.format("%s:%s:%s", df.format(hours), df.format(minutes), df.format(sec));
    }

    private static void readInput() throws IOException {
        String[] tokens = reader.readLine().split(";");
        for (String token : tokens) {
            String name = token.split("-")[0];
            int seconds = Integer.parseInt(token.split("-")[1]);
            robots.add(new Robot(name, seconds));
        }

        getStarTime();
        getProducts();
    }

    private static void getStarTime() throws IOException {
        String[] tokens = reader.readLine().split(":");
        int hours = Integer.parseInt(tokens[0]);
        int minutes = Integer.parseInt(tokens[1]);
        int seconds = Integer.parseInt(tokens[2]);
        seconds += (minutes * 60);
        seconds += (hours * 3600);
        startTime = seconds;
    }

    private static void getProducts() throws IOException {
        String input;
        while (! "End".equals(input = reader.readLine())) {
            products.addLast(input);
        }
    }
}

class Robot {
    private String name;
    private int seconds;
    private int secondsBusy;

    Robot(String name, int seconds) {
        this.name = name;
        this.seconds = seconds;
    }

    boolean isIdle() {
        return secondsBusy == 0;
    }

    void startProcessing() {
        this.secondsBusy = this.seconds;
    }

    void process() {
        if (! this.isIdle()) {
            this.secondsBusy -= 1;
        }
    }

    @Override
    public String toString() {
        return this.name;
    }
}


