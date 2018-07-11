package Z_Exams.exam21Sep2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;


public class Ex01TimeSpan {
    private static BufferedReader reader;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        String[] firstTime = reader.readLine().split(":");
        String[] secondTime = reader.readLine().split(":");

        long firstTimeSeconds = getSeconds(firstTime);
        long secondTimeSeconds = getSeconds(secondTime);

        long span = Math.abs(firstTimeSeconds - secondTimeSeconds);
        calculateTime(span);
    }

    private static long getSeconds(String[] firstTime) {
        long seconds = Long.parseLong(firstTime[2]);
        seconds += Long.parseLong(firstTime[1]) * 60;
        seconds += Long.parseLong(firstTime[0]) * 60 * 60;
        return seconds;
    }

    private static void calculateTime(long seconds) {
        long sec = seconds % 60;
        long minutes = seconds % 3600 / 60;
        long hours = seconds / 3600;
        long days = seconds / 86400;
        DecimalFormat df = new DecimalFormat("00");
        System.out.println(hours + ":" + df.format(minutes) + ":" + df.format(sec));
    }
}
