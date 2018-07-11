package L04IntroToJavaExercises;

import java.util.Scanner;

public class Ex07CharacterMultiplier {
    private static Scanner scanner;
    private static int sum;

    static {
        scanner = new Scanner(System.in);
        sum = 0;
    }

    public static void main(String[] args) {
        String firstSting = scanner.next("\\w+");
        String secondSting = scanner.next("\\w+");
        int length = Math.max(firstSting.length(), secondSting.length());

        for (int i = 0; i < length; i++) {
            int charOfFirst = i < firstSting.length() ? firstSting.charAt(i) : 1;
            int charOfSecond = i < secondSting.length() ? secondSting.charAt(i) : 1;

            sum += (charOfFirst * charOfSecond);
        }

        System.out.println(sum);
    }
}
