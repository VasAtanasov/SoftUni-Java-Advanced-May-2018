package Z_Exams.exam04Oct2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.BiPredicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex01DragonSharp {
    private static final String IF_LOOP;
    private static final String IF_OUT;
    private static final String ELSE_LOOP;
    private static final String ELSE_OUT;
    private static BufferedReader reader;
    private static Pattern loopPattern;
    private static Pattern outPattern;
    private static Pattern elseLoopPattern;
    private static Pattern elseOutPattern;
    private static Map<String, BiPredicate<Integer, Integer>> conditions;
    private static List<String> strings;

    static {
        IF_LOOP = "^if\\s+\\((?<numOne>\\d+)(?<cond>==|<|>)(?<numTwo>\\d+)\\)\\s+loop\\s+(?<loop>\\d+)\\s+out\\s+\"(?<string>[^\\n]+?)\";$";
        IF_OUT = "^if\\s+\\((?<numOne>\\d+)(?<cond>==|<|>)(?<numTwo>\\d+)\\)\\s+out\\s+\"(?<string>[^\\n]+?)?\";$";
        ELSE_LOOP = "^else\\s+loop\\s+(?<loop>\\d+)\\s+out\\s+\"(?<string>[^\\n]+?)\";$";
        ELSE_OUT = "^else\\s+out\\s+\"(?<string>[^\\n]+?)\";$";
        reader = new BufferedReader(new InputStreamReader(System.in));
        loopPattern = Pattern.compile(IF_LOOP);
        outPattern = Pattern.compile(IF_OUT);
        elseLoopPattern = Pattern.compile(ELSE_LOOP);
        elseOutPattern = Pattern.compile(ELSE_OUT);
        conditions = new HashMap<String, BiPredicate<Integer, Integer>>() {{
            put("<", (numOne, numTwo) -> numOne < numTwo);
            put("==", Objects::equals);
            put(">", (numOne, numTwo) -> numOne > numTwo);
        }};
        strings = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {
        boolean ifStatementTrue = true;
        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            String input = reader.readLine();
            Matcher loopMatcher = loopPattern.matcher(input);
            Matcher outMatcher = outPattern.matcher(input);
            Matcher elseLoopMatcher = elseLoopPattern.matcher(input);
            Matcher elseOutMatcher = elseOutPattern.matcher(input);

            if (loopMatcher.find()) {
                int numOne = Integer.parseInt(loopMatcher.group("numOne"));
                int numTwo = Integer.parseInt(loopMatcher.group("numTwo"));
                int count = Integer.parseInt(loopMatcher.group("loop"));
                String condition = loopMatcher.group("cond");
                String text = loopMatcher.group("string");

                if (conditions.get(condition).test(numOne, numTwo)) {
                    for (int j = 0; j < count; j++) {
                        strings.add(text);
                    }
                    ifStatementTrue = true;
                } else {
                    ifStatementTrue = false;
                }

            } else if (outMatcher.find()) {
                int numOne = Integer.parseInt(outMatcher.group("numOne"));
                int numTwo = Integer.parseInt(outMatcher.group("numTwo"));
                String condition = outMatcher.group("cond");
                String text = outMatcher.group("string");

                if (conditions.get(condition).test(numOne, numTwo)) {
                    strings.add(text);
                    ifStatementTrue = true;
                } else {
                    ifStatementTrue = false;
                }

            } else if (elseLoopMatcher.find() && i > 0) {
                if (ifStatementTrue) {
                    continue;
                }
                int count = Integer.parseInt(elseLoopMatcher.group("loop"));
                String text = elseLoopMatcher.group("string");

                for (int j = 0; j < count; j++) {
                    strings.add(text);
                }
                ifStatementTrue = true;
            } else if (elseOutMatcher.find() && i > 0) {
                if (ifStatementTrue) {
                    continue;
                }
                String text = elseOutMatcher.group("string");

                strings.add(text);
                ifStatementTrue = true;
            } else {
                System.out.println(String.format("Compile time error @ line %d", i + 1));
                return;
            }
        }


        strings.forEach(System.out::println);
    }
}