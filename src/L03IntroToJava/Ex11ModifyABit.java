package L03IntroToJava;

import java.util.Scanner;

public class Ex11ModifyABit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder binaryNumber = new StringBuilder(String.format("%16s", Integer.toBinaryString(scanner.nextInt())).
                replace(" ", "0"));
        int position = scanner.nextInt();
        int v = scanner.nextInt();
        binaryNumber.setCharAt(binaryNumber.length() - 1 - position, v == 0 ? '0' : '1');
        System.out.println(Integer.parseInt(binaryNumber.toString(), 2));

    }
}
