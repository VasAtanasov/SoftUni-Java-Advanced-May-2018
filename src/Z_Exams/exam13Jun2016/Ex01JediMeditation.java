package Z_Exams.exam13Jun2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Ex01JediMeditation {
    private static BufferedReader reader;
    private static List<String> masters;
    private static List<String> knights;
    private static List<String> padawans;
    private static List<String> toshkoSlav;
    private static boolean isYoda;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        masters = new ArrayList<>(10000);
        knights = new ArrayList<>(10000);
        padawans = new ArrayList<>(10000);
        toshkoSlav = new ArrayList<>();
        isYoda = false;
    }

    public static void main(String[] args) throws IOException {
        getJedis();

        StringBuilder output = new StringBuilder();
        if (isYoda) {
            output.append(resultString(masters))
                    .append(resultString(knights))
                    .append(resultString(toshkoSlav))
                    .append(resultString(padawans));
            System.out.println(output.toString().trim());

        } else {
            output.append(resultString(toshkoSlav))
                    .append(resultString(masters))
                    .append(resultString(knights))
                    .append(resultString(padawans));
            System.out.println(output.toString().trim());
        }
    }

    private static String resultString(Collection<String> collection) {
        if (! collection.isEmpty()) {
            return collection.toString().replaceAll("[\\[\\],]", "") + " ";
        }
        return "";
    }

    private static void getJedis() throws IOException {
        int count = Integer.parseInt(reader.readLine());
        for (int i = 0; i < count; i++) {
            String[] tokens = reader.readLine().split("\\s+");
            for (String token : tokens) {
                if (token.startsWith("m")) {
                    masters.add(token);
                } else if (token.startsWith("k")) {
                    knights.add(token);
                } else if (token.startsWith("p")) {
                    padawans.add(token);
                } else if (token.startsWith("t") || token.startsWith("s")) {
                    toshkoSlav.add(token);
                } else if (!isYoda) {
                    isYoda = true;
                }
            }
        }
    }
}
