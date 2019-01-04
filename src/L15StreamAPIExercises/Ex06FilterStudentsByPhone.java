package L15StreamAPIExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Ex06FilterStudentsByPhone {
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
            String phoneNumber = tokens[2];

            students.add(new Student(firstName, lastName, phoneNumber));
        }

        students.stream()
                .filter(student -> student.getPhoneNumber().startsWith("02") || student.getPhoneNumber().startsWith("+3592"))
                .forEach(System.out::println);


    }

    static class Student {
        private String firstName;
        private String lastName;
        private String phoneNumber;

        private Student(String firstName, String lastName, String phoneNumber) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.phoneNumber = phoneNumber;
        }

        String getPhoneNumber() {
            return this.phoneNumber;
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
