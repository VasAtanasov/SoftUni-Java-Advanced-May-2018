package L15StreamAPIExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Ex04SortStudents {
    private static BufferedReader reader;
    private static List<Student> students;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        students = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {
        String input;
        while (! "END".equals(input = reader.readLine())) {
            String[] tokens = input.split("\\s+");
            String firstName = tokens[0];
            String lastName = tokens[1];

            students.add(new Student(firstName, lastName));
        }

        students.stream()
                .sorted()
                .forEach(System.out::println);
    }

    static class Student implements Comparable<Student> {
        private String firstName;
        private String lastName;

        private Student(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        String getFirstName() {
            return this.firstName;
        }

        String getLastName() {
            return this.lastName;
        }

        @Override
        public int compareTo(Student other) {
            int index = this.getLastName().compareTo(other.getLastName());
            return index != 0 ? index : other.getFirstName().compareTo(this.getFirstName());
        }

        @Override
        public String toString() {
            return String.format("%s %s", this.getFirstName(), this.getLastName());
        }
    }
}
