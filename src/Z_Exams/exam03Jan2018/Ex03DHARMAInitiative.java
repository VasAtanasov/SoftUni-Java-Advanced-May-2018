package Z_Exams.exam03Jan2018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class Ex03DHARMAInitiative {
    private static BufferedReader reader;
    private static Map<String, Station> stations;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        stations = new LinkedHashMap<String, Station>() {{
            put("Hydra", new Station("Hydra", "Zoological Research."));
            put("Arrow", new Station("Arrow", "Development of defensive strategies, and Intelligence gathering."));
            put("Flame", new Station("Flame", "Communication."));
            put("Pearl", new Station("Pearl", "Psychological Research and/or Observation."));
            put("Orchid", new Station("Orchid", "Space-time manipulation research, disguised as a Botanical station."));
        }};
    }

    public static void main(String[] args) throws IOException {
        registerRecruitsToStations();

        String command = reader.readLine();
        if ("DHARMA Initiative".equals(command)) {
            stations.values().stream()
                    .sorted()
                    .forEach(station -> System.out.println(station.getRecruitCountReport()));
        } else if (stations.containsKey(command)) {
            System.out.println(stations.get(command));
        } else {
            System.out.println("DHARMA Initiative does not have such a station!");
        }


    }

    private static void registerRecruitsToStations() throws IOException {
        String input;
        while (! "Recruit".equals(input = reader.readLine())) {
            String[] tokens = input.split(":");
            String name = tokens[0];
            long id = Long.parseLong(tokens[1]);
            String station = tokens[2];
            if (! stations.containsKey(station)) {
                continue;
            }
            stations.get(station).addRecruit(name, id);
        }
    }
}

class Station implements Comparable<Station> {
    private String purpose;
    private String name;
    private Map<String, Recruit> recruits;

    Station(String name, String purpose) {
        this.name = name;
        this.purpose = purpose;
        this.recruits = new LinkedHashMap<>();
    }

    private String getPurpose() {
        return this.purpose;
    }

    private String getName() {
        return this.name;
    }

    private Map<String, Recruit> getRecruits() {
        return this.recruits;
    }

    void addRecruit(String name, long id) {
        this.getRecruits().putIfAbsent(name, new Recruit(name, id));
    }

    @Override
    public int compareTo(Station other) {
        int index = Integer.compare(other.getRecruits().size(), this.getRecruits().size());
        return index != 0 ? index : this.getName().compareTo(other.getName());
    }

    String getRecruitCountReport() {
        return String.format("The %s has %d DHARMA recruits in it.", this.getName(), this.getRecruits().size());
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(String.format("The %s station: %s%n", this.getName(), this.getPurpose()));
        if (this.getRecruits().size() == 0) {
            output.append("No recruits.");
            return output.toString();
        }

        this.getRecruits().values().stream()
                .sorted()
                .forEach(r -> output.append(String.format("%s%n", r)));
        return output.toString();
    }
}

class Recruit implements Comparable<Recruit> {
    private String name;
    private long id;

    Recruit(String name, long id) {
        this.name = name;
        this.id = id;
    }

    private String getName() {
        return this.name;
    }

    private long getId() {
        return this.id;
    }

    @Override
    public int compareTo(Recruit other) {
        return Long.compare(other.getId(), this.getId());
    }

    @Override
    public String toString() {
        return String.format("###%s - %d", this.getName(), this.getId());
    }
}