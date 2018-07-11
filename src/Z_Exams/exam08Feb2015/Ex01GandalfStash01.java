package Z_Exams.exam08Feb2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Ex01GandalfStash01 {
    private static BufferedReader reader;
    private static final Map<String, Integer> food;

    static {
        food = new HashMap<String, Integer>() {{
            put("cram", 2);
            put("lembas", 3);
            put("apple", 1);
            put("melon", 1);
            put("honeycake", 5);
            put("mushrooms", - 10);
        }};
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        int mood = Integer.valueOf(reader.readLine());
        String[] words = reader.readLine().toLowerCase().split("[^a-zA-Z]+");

        for (String word : words) {
            int gainedMood = food.getOrDefault(word, - 1);
            mood += gainedMood;
        }

        String moodString;
        if (mood < - 5) {
            moodString = "Angry";
        } else if (mood < 0) {
            moodString = "Sad";
        } else if (mood < 15) {
            moodString = "Happy";
        } else {
            moodString = "Special JavaScript mood";
        }

        System.out.println(mood);
        System.out.println(moodString);

    }
}

