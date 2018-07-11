package L15StreamAPIExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Ex03StudentsByAge {
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
                .filter(student -> student.age >= 18 && student.getAge() <= 24)
                .forEach(System.out::println);
    }

    static class Student {
        private String firstName;
        private String lastName;
        private int age;

        private Student(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        String getFirstName() {
            return this.firstName;
        }

        String getLastName() {
            return this.lastName;
        }

        int getAge() {
            return this.age;
        }

        @Override
        public String toString() {
            return String.format("%s %s %d", this.getFirstName(), this.getLastName(), this.getAge());
        }
    }
}
