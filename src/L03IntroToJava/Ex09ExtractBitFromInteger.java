package L03IntroToJava;

import java.util.Scanner;

public class Ex09ExtractBitFromInteger {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String binaryNumber = String.format("%16s", Integer.toBinaryString(scanner.nextInt())).replace(" ", "0");
        int position = scanner.nextInt();
        System.out.println(binaryNumber.charAt(binaryNumber.length() - 1 - position));
    }
}
