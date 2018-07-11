package L15StreamAPIExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Ex05FilterStudentsByEmail {
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
            String email = tokens[2];

            students.add(new Student(firstName, lastName, email));
        }

        students.stream()
                .filter(student -> student.getEmail().endsWith("@gmail.com"))
                .forEach(System.out::println);
    }

    static class Student {
        private String firstName;
        private String lastName;
        private String email;

        private Student(String firstName, String lastName, String email) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
        }

        String getEmail() {
            return this.email;
        }

        String getFirstName() {
            return this.firstName;
        }

        String getLastName() {
            return this.lastName;
        }

        @Override
        public String toString() {
            return String.format("%s %s", this.getFirstName(), this.getLastName());
        }
    }
}
