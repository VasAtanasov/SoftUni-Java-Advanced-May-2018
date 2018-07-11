package L10StringProcessing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Ex01StudentResult {
    private static BufferedReader reader;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        String[] tokens = Arrays.stream(reader.readLine().split("[-,\\s]+"))
                .filter(s -> ! s.isEmpty())
                .toArray(String[]::new);

        String student = tokens[0];
        double javaAdvanced = Double.parseDouble(tokens[1]);
        double javaOOP = Double.parseDouble(tokens[2]);
        double advancedOOp = Double.parseDouble(tokens[3]);
        double average = (javaAdvanced + javaOOP + advancedOOp) / 3;

        String header = String.format("%-10s|%7s|%7s|%7s|%7s|", "Name", "JAdv", "JavaOOP", "AdvOOP", "Average");
        String studentInfo = String.format("%-10s|%7.2f|%7.2f|%7.2f|%7.4f|",student, javaAdvanced, javaOOP, advancedOOp, average);

        System.out.println(header);
        System.out.println(studentInfo);
    }
}

