package L15StreamAPIExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Ex11StudentsJoinedSpecialties {
    private static BufferedReader reader;
    private static Map<String, StudentSpecialty> specialties;
    private static List<Student> students;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        specialties = new LinkedHashMap<>();
        students = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {
        addSpecialties();

        String input;
        while (! "END".equals(input = reader.readLine())) {
            String[] tokens = input.split("\\s+");
            String facultyNumber = tokens[0];
            String firstName = tokens[1];
            String lastName = tokens[2];

            students.add(new Student(firstName, lastName, facultyNumber));
        }


        for (Student student : students) {
            String facultyNumber = student.getFacultyNumber();

            List<String> getSpecialties = specialties.values()
                    .stream()
                    .filter(studentSpecialty -> studentSpecialty.contains(facultyNumber))
                    .map(StudentSpecialty::getName)
                    .collect(Collectors.toCollection(ArrayList::new));

            student.add(getSpecialties);
        }


        students.stream()
                .sorted()
                .forEach(System.out::print);
    }

    private static void addSpecialties() throws IOException {
        String input;
        while (! "Students:".equals(input = reader.readLine())) {
            String[] tokens = input.split("\\s+");
            String specialty = tokens[0] + " " + tokens[1];
            String facultyNumber = tokens[2];

            specialties.putIfAbsent(specialty, new StudentSpecialty(specialty));
            specialties.get(specialty).add(facultyNumber);
        }
    }

    static class Student implements Comparable<Student> {
        private String firstName;
        private String lastName;
        private String facultyNumber;
        private List<String> specialties;

        Student(String firstName, String lastName, String facultyNumber) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.facultyNumber = facultyNumber;
            specialties = new ArrayList<>();
        }

        String getFacultyNumber() {
            return this.facultyNumber;
        }

        String getName() {
            return this.firstName + " " + this.lastName;
        }

        void add(List<String> specialties) {
            this.specialties.addAll(specialties);
        }

        @Override
        public int compareTo(Student other) {
            return this.getName().compareTo(other.getName());
        }

        @Override
        public String toString() {
            StringBuilder output = new StringBuilder();
            this.specialties.forEach(specialty -> {
                output.append(String.format("%s %s %s", this.getName(), this.getFacultyNumber(), specialty));
                output.append(System.lineSeparator());
            });
            return output.toString();
        }
    }


    static class StudentSpecialty {
        private String name;
        private Set<String> facultyNumbers;

        private StudentSpecialty(String name) {
            this.name = name;
            this.facultyNumbers = new LinkedHashSet<>();
        }

        String getName() {
            return this.name;
        }

        void add(String facultyNumber) {
            this.facultyNumbers.add(facultyNumber);
        }

        boolean contains(String facultyNumber) {
            return this.facultyNumbers.contains(facultyNumber);
        }
    }
}
