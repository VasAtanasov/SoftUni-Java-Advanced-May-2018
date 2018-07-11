package Z_Exams.exam19Feb2017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Ex02Earthquake {
    private static BufferedReader reader;
    private static List<Integer> seismicFound;
    private static Deque<Deque<Integer>> activities;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        seismicFound = new ArrayList<>();
        activities = new ArrayDeque<>();
    }

    public static void main(String[] args) throws IOException {
        getActivities();

        while (! activities.isEmpty()) {
            Deque<Integer> activity = activities.removeFirst();

            int firstElement = activity.removeFirst();
            while (true) {
                if (activity.isEmpty()) {
                    break;
                }

                if (firstElement >= activity.peekFirst()) {
                    activity.removeFirst();
                } else {
                    seismicFound.add(firstElement);
                    break;
                }
            }

            if (! activity.isEmpty()) {
                activities.addLast(activity);
            } else {
                seismicFound.add(firstElement);
            }
        }

        System.out.println(seismicFound.size());
        System.out.println(seismicFound.toString().replaceAll("[\\[\\],]",""));

    }

    private static void getActivities() throws IOException {
        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            Deque<Integer> activity = Arrays.stream(reader.readLine().split("\\s+"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toCollection(ArrayDeque::new));
            activities.addLast(activity);
        }
    }
}
