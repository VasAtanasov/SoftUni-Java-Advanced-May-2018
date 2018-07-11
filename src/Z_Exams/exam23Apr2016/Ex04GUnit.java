package Z_Exams.exam23Apr2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex04GUnit {
    private static final String REGEX;
    private static Pattern pattern;
    private static BufferedReader reader;
    private static Map<String, Class> classMap;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        REGEX = "^(?<class>[A-Z][A-Za-z0-9]+) \\| (?<method>[A-Z][A-Za-z0-9]+) \\| (?<unit>[A-Z][A-Za-z0-9]+)$";
        pattern = Pattern.compile(REGEX);
        classMap = new LinkedHashMap<>();
    }

    public static void main(String[] args) throws IOException {
        String input;
        while (! "It's testing time!".equals(input = reader.readLine())) {
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                String className = matcher.group("class");
                String method = matcher.group("method");
                String unit = matcher.group("unit");
                classMap.putIfAbsent(className, new Class(className));
                classMap.get(className).add(method, unit);
            }
        }

        classMap.values()
                .stream()
                .sorted()
                .forEach(System.out::print);
    }
}

class Class implements Comparable<Class> {
    private String name;
    private Map<String, Method> methods;

    Class(String name) {
        this.name = name;
        this.methods = new LinkedHashMap<>();
    }

    private String getName() {
        return this.name;
    }

    private Map<String, Method> getMethods() {
        return this.methods;
    }

    private int getTotalUnitTestsCount() {
        return methods.values()
                .stream()
                .map(Method::getUnitTestsCount)
                .reduce(0, (a, b) -> a + b);
    }

    private String getMethodsString() {
        StringBuilder methodsString = new StringBuilder();
        this.getMethods()
                .values()
                .stream()
                .sorted()
                .forEach(methodsString::append);
        return methodsString.toString();
    }

    void add(String method, String unitTest) {
        this.getMethods().putIfAbsent(method, new Method(method));
        this.getMethods().get(method).add(unitTest);
    }

    @Override
    public int compareTo(Class other) {
        int index = Integer.compare(other.getTotalUnitTestsCount(), this.getTotalUnitTestsCount());
        index = index != 0 ? index : Integer.compare(this.getMethods().size(), other.getMethods().size());
        return index != 0 ? index : this.getName().compareTo(other.getName());
    }

    @Override
    public String toString() {
        return String.format("%s:%n%s", this.getName(), this.getMethodsString());
    }
}

class Method implements Comparable<Method> {
    private String name;
    private Map<String, UnitTest> unitTests;

    Method(String name) {
        this.name = name;
        this.unitTests = new LinkedHashMap<>();
    }

    private String getName() {
        return this.name;
    }

    private Map<String, UnitTest> getUnitTests() {
        return this.unitTests;
    }

    int getUnitTestsCount() {
        return this.getUnitTests().size();
    }

    private String getUnitTestsString() {
        StringBuilder unitTestsString = new StringBuilder();
        this.getUnitTests()
                .values()
                .stream()
                .sorted()
                .forEach(unitTest -> unitTestsString.append(unitTest).append(System.lineSeparator()));
        return unitTestsString.toString();
    }

    void add(String unitTest) {
        unitTests.putIfAbsent(unitTest, new UnitTest(unitTest));
    }

    @Override
    public int compareTo(Method other) {
        int index = Integer.compare(other.getUnitTestsCount(), this.getUnitTestsCount());
        return index != 0 ? index : this.getName().compareTo(other.getName());
    }

    @Override
    public String toString() {
        return String.format("##%s%n%s", this.getName(), this.getUnitTestsString());
    }
}


class UnitTest implements Comparable<UnitTest> {
    private String name;

    UnitTest(String name) {
        this.name = name;
    }

    private String getName() {
        return this.name;
    }

    @Override
    public int compareTo(UnitTest other) {
        int index = Integer.compare(this.getName().length(), other.getName().length());
        return index != 0 ? index : this.getName().compareTo(other.getName());
    }

    @Override
    public String toString() {
        return String.format("####%s", this.getName());
    }
}