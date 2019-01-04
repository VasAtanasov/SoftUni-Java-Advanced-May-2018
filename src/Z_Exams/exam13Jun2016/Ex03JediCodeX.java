package Z_Exams.exam13Jun2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex03JediCodeX {
    private static BufferedReader reader;
    private static List<String> jediNames;
    private static List<String> jediMessages;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        jediNames = new ArrayList<>();
        jediMessages = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine());

        StringBuilder input = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String message = reader.readLine();
            input.append(message);
        }

        String namePattern = reader.readLine();
        String nameRegex = String.format("%s(?<name>[A-Za-z]{%d})(?![A-Za-z])"
                , namePattern, namePattern.length());
        Pattern namePat = Pattern.compile(nameRegex);
        Matcher nameMatcher = namePat.matcher(input.toString());

        String messagePattern = reader.readLine();
        String messageRegex = String.format("%s(?<message>[A-Za-z0-9]{%d})(?![A-Za-z0-9])"
                , messagePattern, messagePattern.length());
        Pattern messagePat = Pattern.compile(messageRegex);
        Matcher messageMatcher = messagePat.matcher(input.toString());

        while (nameMatcher.find()) {
            jediNames.add(nameMatcher.group("name"));
        }

        while (messageMatcher.find()) {
            jediMessages.add(messageMatcher.group("message"));
        }

        int[] messageIndex = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::valueOf)
                .toArray();

        int currentIndex = 0;
        for (int index : messageIndex) {
            if (index - 1 < jediMessages.size() && currentIndex < jediNames.size()) {
                System.out.println(String.format("%s - %s", jediNames.get(currentIndex), jediMessages.get(index - 1)));
                currentIndex++;
            }
        }
    }
}

