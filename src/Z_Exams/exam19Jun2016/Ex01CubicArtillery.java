package Z_Exams.exam19Jun2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Ex01CubicArtillery {
    private static BufferedReader reader;
    private static Deque<Bunker> bunkers;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        bunkers = new ArrayDeque<>();
    }

    public static void main(String[] args) throws IOException {
        int buberMaxCapacity = Integer.parseInt(reader.readLine());

        String input;
        while (! "Bunker Revision".equals(input = reader.readLine())) {
            String[] tokens = input.split("\\s+");
            for (String token : tokens) {
                if (Character.isLetter(token.charAt(0))) {
                    bunkers.addLast(new Bunker(token, buberMaxCapacity));
                } else {
                    addWeaponToBunker(token);
                }
            }
        }
    }

    private static void addWeaponToBunker(String token) {
        int weapon = Integer.parseInt(token);
        while (! bunkers.isEmpty()) {
            if (bunkers.size() == 1) {
                bunkers.peekFirst().addIfPossible(weapon);
                break;
            } else if (bunkers.peekFirst().add(weapon)) {
                break;
            } else {
                System.out.println(bunkers.removeFirst());
            }
        }
    }
}

class Bunker {
    private final String NAME;
    private final int MAX_CAPACITY;
    private Deque<Integer> weapons;
    private int currentCapacity;

    Bunker(String name, int maxCapacity) {
        this.NAME = name;
        this.MAX_CAPACITY = maxCapacity;
        this.weapons = new ArrayDeque<>();
        this.currentCapacity = 0;
    }

    boolean add(int weapon) {
        if ((this.currentCapacity + weapon) <= MAX_CAPACITY) {
            this.weapons.addLast(weapon);
            currentCapacity += weapon;
            return true;
        }
        return false;
    }

    private String getNAME() {
        return this.NAME;
    }

    void addIfPossible(int weapon) {
        while (! this.add(weapon) && ! this.weapons.isEmpty()) {
            currentCapacity -= weapons.removeFirst();
        }
    }

    @Override
    public String toString() {
        String weapons = this.weapons.isEmpty() ? "Empty" : this.weapons.toString().replaceAll("[\\[\\]]", "");
        return String.format("%s -> %s", this.getNAME(), weapons);
    }
}
