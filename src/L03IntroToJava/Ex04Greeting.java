package L03IntroToJava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex04Greeting {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String firstName = reader.readLine();
        String lastName = reader.readLine();

        firstName = firstName.isEmpty() ? "*****" : firstName;
        lastName = lastName.isEmpty() ? "*****" : lastName;

        System.out.println(String.format("Hello, %s %s!",firstName,lastName));
    }

}
