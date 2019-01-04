package Z_Exams.exam13Jun2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex04JediDreams {
    private static BufferedReader reader;
    private static Map<String, List<String>> methods;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        methods = new LinkedHashMap<>();
    }

    public static void main(String[] args) throws IOException {
        int linesCount = Integer.parseInt(reader.readLine());

        Pattern pattern = Pattern.compile("(?<method>[a-zA-Z]*[A-Z]+[a-zA-Z]*)\\s*\\(");
        String currentKey = "";

        for (int i = 0; i < linesCount; i++) {
            String line = reader.readLine();
            Matcher matcher = pattern.matcher(line);

            if (line.trim().startsWith("static")) {
                if (matcher.find()) {
                    currentKey = matcher.group("method");
                    methods.putIfAbsent(currentKey, new ArrayList<>());
                }
            }

            while (matcher.find()) {
                String currentMatch = matcher.group("method");
                int methodIndex = line.indexOf(currentMatch);
                String newString = line.substring(0, methodIndex);
                if (! newString.contains("new")) {
                    methods.get(currentKey).add(currentMatch);
                }
            }
        }

        methods.entrySet().stream()
                .sorted((a, b) -> {
                    int index = Integer.compare(b.getValue().size(), a.getValue().size());
                    return (index != 0) ? index : a.getKey().compareTo(b.getKey());
                })
                .forEach(method -> {
                    System.out.print(String.format("%s -> ", method.getKey()));
                    if (method.getValue().size() > 0) {
                        System.out.print(String.format("%d -> ", method.getValue().size()));
                    }
                    System.out.println(method.getValue().stream()
                            .sorted(String::compareTo)
                            .reduce((a, b) -> a + ", " + b).orElse("None"));
                });

    }
}

