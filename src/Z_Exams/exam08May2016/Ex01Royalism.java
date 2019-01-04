package Z_Exams.exam08May2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Ex01Royalism {
    private static BufferedReader reader;
    private static List<String> royalist;
    private static List<String> non_royalist;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        royalist = new ArrayList<>();
        non_royalist = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {
        String[] input = reader.readLine().split("\\s+");
        for (String string : input) {
            int charSum = string.chars().sum() % 26;

            if (charSum == 18) {
                royalist.add(string);
            } else {
                non_royalist.add(string);
            }
        }

        if (royalist.size() >= non_royalist.size()) {
            System.out.println(String.format("Royalists - %d", royalist.size()));
            royalist.forEach(System.out::println);
            System.out.println("All hail Royal!");
        } else {
            non_royalist.forEach(System.out::println);
            System.out.println("Royalism, Declined!");

        }
    }
}
