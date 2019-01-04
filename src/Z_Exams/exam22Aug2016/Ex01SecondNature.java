package Z_Exams.exam22Aug2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Collectors;

public class Ex01SecondNature {
    private static BufferedReader reader;
    private static Deque<Integer> flowers;
    private static Deque<Integer> buckets;
    private static Deque<Integer> secondNatureFlowers;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        secondNatureFlowers = new ArrayDeque<>();
    }

    public static void main(String[] args) throws IOException {
        readInput();

        while (! flowers.isEmpty() && ! buckets.isEmpty()) {
            int currentBucket = buckets.removeLast();
            int currentFlower = flowers.removeFirst();

            int difference = currentBucket - currentFlower;

            if (difference < 0) {
                flowers.addFirst(Math.abs(difference));
            } else if (difference == 0) {
                secondNatureFlowers.addLast(currentFlower);
            } else {
                if (buckets.isEmpty()) {
                    buckets.addLast(difference);
                } else {
                    buckets.addLast(buckets.removeLast() + difference);
                }
            }
        }

        printBucketsOrFlowers();
        printNatureFlowers();

    }

    private static void printBucketsOrFlowers() {
        if (flowers.isEmpty()) {
            StringBuilder outputBuckets = new StringBuilder();
            while (! buckets.isEmpty()) {
                outputBuckets.append(buckets.removeLast()).append(" ");
            }
            System.out.println(outputBuckets.toString().trim());
        } else {
            System.out.println(flowers.toString().replaceAll("[\\[\\],]", ""));
        }
    }

    private static void printNatureFlowers() {
        if (secondNatureFlowers.isEmpty()) {
            System.out.println("None");
        } else {
            System.out.println(secondNatureFlowers.toString().replaceAll("[\\[\\],]", ""));
        }
    }

    private static void readInput() throws IOException {
        flowers = Arrays.stream(reader.readLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));
        buckets = Arrays.stream(reader.readLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));
    }
}
