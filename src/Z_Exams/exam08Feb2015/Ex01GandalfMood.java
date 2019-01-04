package Z_Exams.exam08Feb2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class Ex01GandalfMood {
    private static BufferedReader reader;
    private static Map<String, Integer> foodPoints;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        foodPoints = new LinkedHashMap<String, Integer>() {{
            put("cram", 2);
            put("lembas", 3);
            put("apple", 1);
            put("melon", 1);
            put("honeycake", 5);
            put("mushrooms", - 10);
        }};
    }

    public static void main(String[] args) throws IOException {
        Person person = new Person(Integer.parseInt(reader.readLine()));
        String[] foods = Arrays.stream(reader.readLine().toLowerCase().split("[^A-Za-z]+"))
                .filter(s -> ! s.isEmpty())
                .toArray(String[]::new);

        for (String food : foods) {
            person.add(foodPoints.getOrDefault(food, - 1));
        }

        System.out.println(person);
    }
}

class Person {
    private int mood;

    Person(int mood) {
        this.mood = mood;
    }

    void add(int mood) {
        this.mood += mood;
    }

    private int getMood() {
        return this.mood;
    }

    private String getMoodString() {
        if (this.getMood() < - 5) {
            return "Angry";
        } else if (this.getMood() < 0) {
            return "Sad";
        } else if (this.getMood() < 15) {
            return "Happy";
        } else {
            return "Special JavaScript mood";
        }
    }

    @Override
    public String toString() {
        return String.format("%d%n%s", this.getMood(), this.getMoodString());
    }
}







