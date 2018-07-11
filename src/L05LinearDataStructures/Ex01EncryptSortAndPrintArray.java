package L05LinearDataStructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ex01EncryptSortAndPrintArray {
    private static BufferedReader reader;
    private static final String vowels;
    private static List<Integer> codes;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        vowels = "aeiou";
        codes = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {
        int count = Integer.parseInt(reader.readLine());

        for (int i = 0; i < count; i++) {
            String name = reader.readLine();
            int sum = getSum(name);
            codes.add(sum);
        }

        Collections.sort(codes);
        codes.forEach(System.out::println);

    }

    private static int getSum(String name) {
        int length = name.length();
        int sum = 0;
        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);
            if (vowels.indexOf(Character.toLowerCase(ch)) != - 1) {
                sum += (ch * length);
            } else {
                sum += (ch / length);
            }
        }
        return sum;
    }
}
