package L04IntroToJavaExercises;

import java.util.Scanner;

public class Ex01RectangleArea {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double sideA = scanner.nextDouble();
        double sideB = scanner.nextDouble();

        System.out.println(String.format("%.2f", sideA * sideB));
    }
}
