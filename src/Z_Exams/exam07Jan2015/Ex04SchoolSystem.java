package Z_Exams.exam07Jan2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Ex04SchoolSystem {
    private static BufferedReader reader;
    private static Map<String, Student> students;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        students = new LinkedHashMap<>();
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            String[] tokens = reader.readLine().split("\\s+");
            String fullName = tokens[0] + " " + tokens[1];
            String subject = tokens[2];
            double grade = Double.parseDouble(tokens[3]);

            students.putIfAbsent(fullName, new Student(fullName));
            students.get(fullName).add(subject, grade);
        }

        students.values().stream()
                .sorted()
                .forEach(System.out::println);

    }
}

class Student implements Comparable<Student> {
    private String fullName;
    private Map<String, Subject> subjects;

    Student(String fullName) {
        this.fullName = fullName;
        this.subjects = new LinkedHashMap<>();
    }

    void add(String subject, double grade) {
        this.subjects.putIfAbsent(subject, new Subject(subject));
        this.subjects.get(subject).add(grade);
    }

    private ArrayList<Subject> getSubjects() {
        return this.subjects.values()
                .stream()
                .sorted()
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private String getFullName() {
        return this.fullName;
    }

    @Override
    public int compareTo(Student other) {
        return this.getFullName().compareTo(other.getFullName());
    }

    @Override
    public String toString() {
        return String.format("%s: %s", this.getFullName(), getSubjects().toString());
    }
}

class Subject implements Comparable<Subject> {
    private String name;
    private DoubleSummaryStatistics grades;

    Subject(String name) {
        this.name = name;
        this.grades = new DoubleSummaryStatistics();
    }

    String getName() {
        return this.name;
    }

    void add(double grade) {
        this.grades.accept(grade);
    }

    @Override
    public int compareTo(Subject other) {
        return this.getName().compareTo(other.getName());
    }

    @Override
    public String toString() {
        return String.format("%s - %.2f", this.getName(), this.grades.getAverage());
    }
}