package L04IntroToJavaExercises;

import java.util.Scanner;

public class Ex02TriangleArea {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int aX = scanner.nextInt();
        int aY = scanner.nextInt();

        int bX = scanner.nextInt();
        int bY = scanner.nextInt();

        int cX = scanner.nextInt();
        int cY = scanner.nextInt();

        int area = Math.abs((aX * (bY - cY) + bX * (cY - aY) + cX * (aY - bY)) / 2);
        System.out.println(String.format("%d", Math.round(area)));
    }
}
