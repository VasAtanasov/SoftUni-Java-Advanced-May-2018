package L06LinearDataStructuresExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Ex07ReverseNumbersWithStack {
    private static BufferedReader reader;
    private static Deque<Integer> stack;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        stack = new ArrayDeque<>();
    }

    public static void main(String[] args) throws IOException {
        readInput();

        StringBuilder output = new StringBuilder();
        while (! stack.isEmpty()) {
            output.append(stack.removeLast()).append(" ");
        }
        System.out.println(output.toString());

    }

    private static void readInput() throws IOException {
        String[] tokens = reader.readLine().split("\\s+");

        for (String token : tokens) {
            stack.addLast(Integer.parseInt(token));
        }
    }
}
