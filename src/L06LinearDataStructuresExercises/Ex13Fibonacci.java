package L06LinearDataStructuresExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex13Fibonacci {
    private static BufferedReader reader;
    private static long[] fibArray;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    private static long fibonacci(long n) {
        long fibValue;
        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return 1;
        } else if (fibArray[(int) (n-1)] != 0) {
            return fibArray[(int)  (n-1)];
        } else {
            fibValue = fibonacci(n - 1) + fibonacci(n - 2);
            fibArray[(int)  (n-1)] = 1;
            return fibValue;
        }
    }

    public static void main(String args[]) throws IOException {
        int n = Integer.parseInt(reader.readLine());
        fibArray = new long[n];
        fibArray[0] = 1;
        fibArray[1] = 1;
        System.out.println(fibonacci(n));
    }
}

