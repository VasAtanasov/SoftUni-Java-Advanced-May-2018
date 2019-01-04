package L03IntroToJava;

import java.util.Scanner;

public class Ex11ModifyABit01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int p = scanner.nextInt();
        int v = scanner.nextInt();

        if (v == 0) {
            int mask = ~(1 << p);
            int result = mask & n;
            System.out.println(result);

        } else {
            int mask = 1 << p;
            int result = n | mask;
            System.out.println(result);
        }
    }
}
