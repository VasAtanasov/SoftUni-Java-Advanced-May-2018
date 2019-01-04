package L03IntroToJava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

public class Ex07ProductOfNumbers {
    private static BufferedReader reader;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        BigInteger product = BigInteger.ONE;
        BigInteger[] numbers = Arrays.stream(reader.readLine().split("\\s+"))
                .map(BigInteger::new)
                .toArray(BigInteger[]::new);
        BigInteger start = numbers[0];
        BigInteger end = numbers[1];

        while (start.compareTo(end) <= 0) {
            product = product.multiply(start);
            start = start.add(BigInteger.ONE);
        }

        System.out.println(String.format("product[%s..%s] = %s",numbers[0],numbers[1],product));
    }
}
