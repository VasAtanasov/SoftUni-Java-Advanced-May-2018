package L04IntroToJavaExercises;

import java.util.Scanner;

public class Ex03FormattingNumbers {
    private static final int MAX_LENGTH;
    private static final String LEFT;
    private static final String RIGHT;
    private static Scanner scanner;

    static {
        scanner = new Scanner(System.in);
        MAX_LENGTH = 10;
        LEFT = "left";
        RIGHT = "right";
    }

    public static void main(String[] args) {
        int a = scanner.nextInt();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();

        String hexNumber = align(Integer.toHexString(a).toUpperCase(), " ", LEFT);
        String binary = align(Integer.toBinaryString(a), "0", RIGHT);
        String twoDigitsAfterDecimal = align(String.format("%.2f", b), " ", RIGHT);
        String threeDigitsAfterDecimal = align(String.format("%.3f", c), " ", LEFT);

        System.out.println(String.format("|%s|%s|%s|%s|",
                hexNumber,
                binary,
                twoDigitsAfterDecimal,
                threeDigitsAfterDecimal));
    }

    private static String align(String number, String fill, String alignment) {
        int count = MAX_LENGTH - number.length();
        String emptyString = getEmptyString(count, fill);
        if (LEFT.equals(alignment)) {
            return number + emptyString;
        } else {
            return emptyString + number;
        }
    }

    private static String getEmptyString(int count, String fill) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < count; i++) {
            output.append(fill);
        }
        return output.toString();
    }
}
