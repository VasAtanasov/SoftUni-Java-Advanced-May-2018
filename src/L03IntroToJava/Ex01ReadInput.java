package L03IntroToJava;

import java.util.Scanner;

public class Ex01ReadInput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String stringOne = scanner.next("\\w+");
        String stringTwo = scanner.next("\\w+");
        int intOne = scanner.nextInt();
        int intTwo = scanner.nextInt();
        int intTree = scanner.nextInt();
        scanner.nextLine();
        String word = scanner.nextLine();

        System.out.println(String.format("%s %s %s %d", stringOne, stringTwo, word, (intOne + intTwo + intTree)));
    }
}
