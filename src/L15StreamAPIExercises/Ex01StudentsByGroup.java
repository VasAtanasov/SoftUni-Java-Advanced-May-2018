package L15StreamAPIExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Ex01StudentsByGroup {
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
            int group = Integer.parseInt(tokens[2]);

            students.add(new Student(firstName, lastName, group));
        }

        students.stream()
                .filter(student -> student.group == 2)
                .sorted()
                .forEach(System.out::println);
    }

    static class Student implements Comparable<Student> {
        private String firstName;
        private String lastName;
        private int group;

        private Student(String firstName, String lastName, int group) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.group = group;
        }

        String getFirstName() {
            return this.firstName;
        }

        String getLastName() {
            return this.lastName;
        }

        int getGroup() {
            return this.group;
        }

        @Override
        public int compareTo(Student other) {
            return this.getFirstName().compareTo(other.getFirstName());
        }

        @Override
        public String toString() {
            return String.format("%s %s", this.getFirstName(), this.getLastName());
        }
    }
}

