package L05LinearDataStructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Ex02SplitByWordCasing {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> words = Arrays.stream(reader.readLine().split("[\\/,:;.!\\\\()\"'\\[\\]\\s]+"))
                .filter(e -> ! e.equals(""))
                .collect(Collectors.toList());
        List<String> lowerCase = new ArrayList<>();
        List<String> mixedCase = new ArrayList<>();
        List<String> upperCase = new ArrayList<>();

        for (int i = 0; i < words.size(); i++) {
            if (isMixed(words.get(i))) {
                mixedCase.add(words.get(i));
            } else if (isUpperCase(words.get(i))) {
                upperCase.add(words.get(i));
            } else if (isLowerCase(words.get(i))) {
                lowerCase.add(words.get(i));
            }
        }

        System.out.println(String.format("Lower-case: %s", lowerCase.stream().collect(Collectors.joining(", "))));
        System.out.println(String.format("Mixed-case: %s", mixedCase.stream().collect(Collectors.joining(", "))));
        System.out.println(String.format("Upper-case: %s", upperCase.stream().collect(Collectors.joining(", "))));

    }

    private static boolean isMixed(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (isUpperCase(s) || isLowerCase(s)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isUpperCase(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (! Character.isUpperCase(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean isLowerCase(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (! Character.isLowerCase(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}
