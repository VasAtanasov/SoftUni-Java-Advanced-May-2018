package L15StreamAPIExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Ex07ExcellentStudents {
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
            String[] marks = new String[tokens.length - 2];
            System.arraycopy(tokens, 2, marks, 0, tokens.length - 2);

            students.add(new Student(firstName, lastName, marks));
        }

        students.stream()
                .filter(Student::hasExcellentMark)
                .forEach(System.out::println);

    }

    static class Student {
        private String firstName;
        private String lastName;
        private String[] marks;

        private Student(String firstName, String lastName, String... marks) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.marks = marks;
        }

        String[] getMarks() {
            return this.marks;
        }

        boolean hasExcellentMark() {
            for (String mark : this.getMarks()) {
                if (mark.equals("6")) {
                    return true;
                }
            }
            return false;
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
