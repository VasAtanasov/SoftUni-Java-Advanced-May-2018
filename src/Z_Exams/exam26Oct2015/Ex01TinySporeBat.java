package Z_Exams.exam26Oct2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex01TinySporeBat {
    private static final String REGEX;
    private static Pattern pattern;
    private static final int GLOW_CAPS_FOR_SPOREBAT = 30;
    private static BufferedReader reader;
    private static Gatherer gatherer;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        gatherer = new Gatherer();
        REGEX = "(?<enemies>[^\\n]+)(?<glowCap>[0-9])";
        pattern = Pattern.compile(REGEX);
    }

    public static void main(String[] args) throws IOException {
        String input;
        while (! "Sporeggar".equals(input = reader.readLine())) {
            Matcher matcher = pattern.matcher(input);
            while (matcher.find()) {
                String enemies = matcher.group("enemies");
                int glowCap = Integer.parseInt(matcher.group("glowCap"));
                for (int i = 0; i < enemies.length(); i++) {
                    char ch = enemies.charAt(i);
                    if (ch == 'L') {
                        gatherer.heal();
                    } else {
                        gatherer.takeDamage(ch);
                    }

                    if (gatherer.isDead()) {
                        System.out.println(String.format("Died. Glowcaps: %d", gatherer.getGlowCaps()));
                        return;
                    }
                }

                gatherer.collect(glowCap);
            }
        }

        System.out.println(gatherer);
        if (gatherer.getGlowCaps() >= GLOW_CAPS_FOR_SPOREBAT) {
            System.out.println(String.format("Bought the sporebat. Glowcaps left: %d", gatherer.getGlowCaps() - GLOW_CAPS_FOR_SPOREBAT));
        } else {
            System.out.println(String.format("Safe in Sporeggar, but another %d Glowcaps needed.", GLOW_CAPS_FOR_SPOREBAT - gatherer.getGlowCaps()));
        }

    }
}

class Gatherer {
    private int health;
    private int glowCaps;

    Gatherer() {
        this.health = 5800;
        this.glowCaps = 0;
    }

    private int getHealth() {
        return this.health;
    }

    int getGlowCaps() {
        return this.glowCaps;
    }

    void takeDamage(int damage) {
        this.health = this.health - damage;
    }

    void collect(int glowCaps) {
        this.glowCaps = this.glowCaps + glowCaps;
    }

    void heal() {
        this.health = this.health + 200;
    }

    boolean isDead() {
        return this.health <= 0;
    }

    @Override
    public String toString() {
        return String.format("Health left: %d", this.getHealth());
    }
}
