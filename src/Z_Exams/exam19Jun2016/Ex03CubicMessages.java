package Z_Exams.exam19Jun2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex03CubicMessages {
    private static BufferedReader reader;
    private static String regex;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        regex = "^(?<before>\\d+)(?<message>[A-Za-z]{messageLength})(?<after>[^A-Za-z\\n]*)$";
    }

    public static void main(String[] args) throws IOException {
        String input;
        while (! "Over!".equals(input = reader.readLine())) {
            int length = Integer.parseInt(reader.readLine());
            Matcher matcher = Pattern.compile(makeRegex(length)).matcher(input);
            if (matcher.find()) {
                String message = matcher.group("message");
                List<Integer> indexes = getIndexes(input);
                String decrypted = getMessageString(message, indexes);
                System.out.println(String.format("%s == %s", message, decrypted));
            }
        }
    }

    private static String getMessageString(String message, List<Integer> indexes) {
        StringBuilder decrypted = new StringBuilder();
        for (Integer index : indexes) {
            if (index >= 0 && index < message.length()) {
                decrypted.append(message.charAt(index));
            } else {
                decrypted.append(" ");
            }
        }
        return decrypted.toString();
    }

    private static String makeRegex(int m) {
        return regex.replace("messageLength", String.valueOf(m));
    }

    private static List<Integer> getIndexes(String string) {
        List<Integer> indexes = new ArrayList<>();
        Matcher matcher = Pattern.compile("[0-9]").matcher(string);
        while (matcher.find()) {
            indexes.add(Integer.parseInt(matcher.group()));
        }
        return indexes;
    }
}
