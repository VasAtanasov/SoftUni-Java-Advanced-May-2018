package Z_Exams.exam22Aug2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Ex03NMS {
    private static BufferedReader reader;
    private static String[] array;
    private static String delimiter;
    private static List<String> sequences;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        sequences = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {
        readInput();
        findSequences();

        System.out.println(sequences.stream().collect(Collectors.joining(delimiter)));
    }

    private static void findSequences() {
        StringBuilder sequence = new StringBuilder(array[0]);
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1].compareToIgnoreCase(array[i]) <= 0) {
                sequence.append(array[i]);
            } else {
                sequences.add(sequence.toString());
                sequence = new StringBuilder(array[i]);
            }
        }
        sequences.add(sequence.toString());
    }

    private static void readInput() throws IOException {
        StringBuilder inputLines = new StringBuilder();
        String line;
        while (! "---NMS SEND---".equals(line = reader.readLine())) {
            inputLines.append(line);
        }
        array = inputLines.toString().split("");
        delimiter = reader.readLine();
    }
}
