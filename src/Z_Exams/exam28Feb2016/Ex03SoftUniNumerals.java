package Z_Exams.exam28Feb2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Ex03SoftUniNumerals {
    private static BufferedReader reader;
    private static String[] codes = {"aa", "aba", "bcc", "cc", "cdc"};

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        String input = reader.readLine();
        StringBuilder number = new StringBuilder();

        while (input.length() > 0) {
            for (int i = 0; i < 5; i++) {
                if (input.startsWith(codes[i])) {
                    number.append(i);
                    input = input.substring(codes[i].length());
                }
                if (input.length() == 0) {
                    break;
                }
            }
        }


        System.out.println(new BigInteger(number.toString(),5));
    }
}
