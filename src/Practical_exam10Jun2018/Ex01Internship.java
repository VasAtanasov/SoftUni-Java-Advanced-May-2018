package Practical_exam10Jun2018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Ex01Internship {
    private static BufferedReader reader;
    private static Deque<String> problems;
    private static Deque<String> candidates;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        problems = new ArrayDeque<>();
        candidates = new ArrayDeque<>();
    }

    public static void main(String[] args) throws IOException {
        readInput();

        while (true) {
            if (problems.isEmpty()) {
                System.out.println(candidates.toString().replaceAll("[\\[\\]]", ""));
                return;
            } else if (candidates.size() == 1) {
                System.out.println(String.format("%s gets the job!", candidates.peekFirst()));
                return;
            }

            String problem = problems.removeFirst();
            String candidate = candidates.removeFirst();

            int problemSum = problem.chars().sum();
            int candidateSum = candidate.chars().sum();

            if (candidateSum > problemSum) {
                candidates.addLast(candidate);
                System.out.println(String.format("%s solved %s.", candidate, problem));
            } else {
                problems.addLast(problem);
                System.out.println(String.format("%s failed %s.", candidate, problem));
            }
        }


    }

    private static void readInput() throws IOException {
        int problemCount = Integer.parseInt(reader.readLine());
        int peopleCount = Integer.parseInt(reader.readLine());

        for (int i = 0; i < problemCount; i++) {
            problems.addFirst(reader.readLine());
        }

        for (int i = 0; i < peopleCount; i++) {
            String name = reader.readLine();
            if (name.matches("[A-Z][a-z]+ [A-Z][a-z]+")) {
                candidates.addLast(name);
            }
        }
    }
}
