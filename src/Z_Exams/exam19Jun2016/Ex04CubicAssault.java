package Z_Exams.exam19Jun2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class Ex04CubicAssault {
    private static BufferedReader reader;
    private static Map<String, Region> regions;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        regions = new LinkedHashMap<>();
    }

    public static void main(String[] args) throws IOException {
        String input;
        while (! "Count em all".equals(input = reader.readLine())) {
            String[] tokens = input.split(" -> ");
            String region = tokens[0];
            String meteor = tokens[1];
            long count = Long.parseLong(tokens[2]);

            regions.putIfAbsent(region, new Region(region));
            regions.get(region).add(meteor, count);
        }

        regions.values().stream()
                .sorted()
                .forEach(System.out::print);
    }
}

class Region implements Comparable<Region> {
    private static final long MILLION = 1_000_000L;
    private static final String GREEN = "Green";
    private static final String RED = "Red";
    private static final String BLACK = "Black";

    private String name;
    private Map<String, Meteor> meteors;

    {
        this.meteors = new LinkedHashMap<String, Meteor>() {{
            put(GREEN, new Meteor(GREEN));
            put(RED, new Meteor(RED));
            put(BLACK, new Meteor(BLACK));
        }};
    }

    Region(String name) {
        this.name = name;
    }

    private long getBlackMeteors() {
        return this.meteors.get(BLACK).getCount();
    }

    private String getName() {
        return this.name;
    }

    private String getMeteorsString() {
        StringBuilder meteorsString = new StringBuilder();
        this.meteors.values().stream()
                .sorted()
                .forEach(meteor -> meteorsString.append(meteor).append(System.lineSeparator()));
        return meteorsString.toString();
    }

    void add(String meteor, long count) {
        this.meteors.get(meteor).add(count);

        long greenCount = this.meteors.get(GREEN).getCount();
        long redCount = this.meteors.get(RED).getCount();
        if (this.meteors.get(GREEN).getCount() >= MILLION) {
            this.meteors.get(RED).setCount(redCount + greenCount / MILLION);
            this.meteors.get(GREEN).setCount(greenCount % MILLION);
        }

        redCount = this.meteors.get(RED).getCount();
        long blackCount = this.meteors.get(BLACK).getCount();
        if (redCount >= MILLION) {
            this.meteors.get(BLACK).setCount(blackCount + redCount / MILLION);
            this.meteors.get(RED).setCount(redCount % MILLION);
        }
    }

    @Override
    public int compareTo(Region other) {
        int index = Long.compare(other.getBlackMeteors(), this.getBlackMeteors());
        index = index != 0 ? index : Integer.compare(this.getName().length(), other.getName().length());
        return index != 0 ? index : this.getName().compareTo(other.getName());
    }

    @Override
    public String toString() {
        return String.format("%s%n%s", this.getName(), this.getMeteorsString());
    }
}

class Meteor implements Comparable<Meteor> {
    private String type;
    private long count;

    Meteor(String type) {
        this.type = type;
        this.count = 0L;
    }

    private String getType() {
        return this.type;
    }

    void setCount(long count) {
        this.count = count;
    }

    long getCount() {
        return this.count;
    }

    void add(long count) {
        this.count += count;
    }

    @Override
    public int compareTo(Meteor other) {
        int index = Long.compare(other.getCount(), this.getCount());
        return index != 0 ? index : this.getType().compareTo(other.getType());
    }

    @Override
    public String toString() {
        return String.format("-> %s : %d", this.getType(), this.getCount());
    }
}