package Z_Exams.exam29Oct2017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Collectors;

public class Ex02LittleAlchemy {
    private static BufferedReader reader;
    private static Deque<Integer> stones;
    private static int storage;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        stones = new ArrayDeque<>();
        storage = 0;
    }

    public static void main(String[] args) throws IOException {
        laiStonesOnTable();

        String input;
        while (! "Revision".equals(input = reader.readLine())) {
            String[] tokens = input.split("\\s+");
            String command = tokens[0];
            int acid = Integer.parseInt(tokens[2]);

            switch (command) {
                case "Apply":
                    applyAcid(acid);
                    break;
                case "Air":
                    airLeak(acid);
                    break;
            }
        }


        System.out.println(stones.toString().replaceAll("[\\[\\],]",""));
        System.out.println(storage);
    }

    private static void airLeak(int acid) {
        if (storage > 0) {
            stones.addLast(acid);
            storage--;
        }
    }

    private static void applyAcid(int acid) {
        while (! stones.isEmpty() && acid-- > 0) {
            int stone = stones.removeFirst() - 1;

            if (stone == 0) {
                storage++;
            } else {
                stones.addLast(stone);
            }

        }
    }

    private static void laiStonesOnTable() throws IOException {
        stones = Arrays.stream(reader.readLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));
    }


}
