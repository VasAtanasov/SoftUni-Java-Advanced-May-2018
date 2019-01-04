package L15StreamAPIExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ex09StudentsEnrolled {
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
            String facultyNumber = tokens[0];
            String[] marks = new String[tokens.length - 1];
            System.arraycopy(tokens, 1, marks, 0, tokens.length - 1);

            students.add(new Student(facultyNumber, marks));
        }

        students.stream()
                .filter(student -> student.getYear().equals("15") || student.getYear().equals("14"))
                .forEach(System.out::println);

    }

    static class Student {
        private String facultyNumber;
        private String[] marks;
        private String year;

        private Student(String facultyNumber, String... marks) {
            this.facultyNumber = facultyNumber;
            this.marks = marks;
            this.setYear(facultyNumber);
        }

        private void setYear(String facultyNumber) {
            this.year = facultyNumber.substring(facultyNumber.length() - 2);
        }

        String[] getMarks() {
            return this.marks;
        }

        String getYear() {
            return this.year;
        }

        @Override
        public String toString() {
            return String.format("%s", Arrays.toString(this.getMarks()).replaceAll("[\\[\\],]", ""));
        }
    }
}
