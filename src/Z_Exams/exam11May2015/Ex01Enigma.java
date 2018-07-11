package Z_Exams.exam11May2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex01Enigma {
    private static BufferedReader reader;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            String originalLine = reader.readLine();
            int m = originalLine.replaceAll("[0-9\\s]", "").length() / 2;
            StringBuilder newText = new StringBuilder();

            for (int j = 0; j < originalLine.length(); j++) {
                char k = originalLine.charAt(j);
                if (! Character.isDigit(k) && ! Character.isWhitespace(k)) {
                    int newChar = k + m;
                    newText.append((char) newChar);
                } else {
                    newText.append(k);
                }
            }

            System.out.println(newText.toString());
        }
    }
}
