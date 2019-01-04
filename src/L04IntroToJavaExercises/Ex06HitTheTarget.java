package L04IntroToJavaExercises;

import java.util.Scanner;

public class Ex06HitTheTarget {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();

        StringBuilder sum = new StringBuilder();
        StringBuilder diff = new StringBuilder();

        for (int i = 1; i <= 20; i++) {
            for (int j = 1; j <= 20; j++) {
                if (i + j == number) {
                    sum.append(String.format("%d + %d = %d%n", i, j, number));
                }

                if (i - j == number) {
                    diff.append(String.format("%d - %d = %d%n", i, j, number));
                }
            }
        }

        System.out.print(sum.toString());
        System.out.println(diff.toString());
    }
}
