package Z_Exams.lab27Oct2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DragonEra {
    private static BufferedReader reader;
    private static List<Dragon> dragons;
    static int dragonsCount = 0;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        dragons = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {
        int dragonStart = Integer.parseInt(reader.readLine());
        for (int i = 1; i <= dragonStart; i++) {
            Dragon dragon = new Dragon("Dragon_" + i, 0);

            int eggs = Integer.parseInt(reader.readLine());
            for (int eggCount = 0; eggCount < eggs; eggCount++) {
                Egg egg = new Egg(dragon, 0);
                dragon.lay(egg);
            }
            dragons.add(dragon);
        }

        dragonsCount = dragonStart + 1;


        int years = Integer.parseInt(reader.readLine());
        for (int i = 0; i < years; i++) {
            String yearType = reader.readLine();
            YearType yearFactor = YearType.valueOf(yearType);

            for (Dragon dragon : dragons) {
                passAge(dragon, yearFactor);
            }
        }

        for (Dragon dragon : dragons) {
            print(dragon,0);
        }

    }

    public static void print(Dragon dragon, int indentation){
        String indent = "";
        for (int i = 0; i <indentation ; i++) {
            indent += " ";
        }
        if(dragon.isAlive()){
            System.out.printf("%s%s\n",indent,dragon.getName());
        }

        for (Dragon child : dragon.getChildren()) {
            print(child,indentation + 2);
        }
    }

    private static void passAge(Dragon dragon, YearType factor) {
        dragon.age();
        dragon.lay();

        if (dragon.isAlive()) {
            for (Egg egg : dragon.getEggs()) {
                egg.setYearFactor(factor);

                egg.age();
                egg.hatch();
            }
        }

        for (Dragon child : dragon.getChildren()) {
            passAge(child, factor);
        }
    }
}

class Dragon {
    private static final int AGE_DEATH = 6;
    private static final int AGE_LAY_EGGS_START = 3;
    private static final int AGE_LAY_EGGS_END = 4;

    private String name;
    private int age;
    private List<Egg> eggs;
    private List<Dragon> children;
    private boolean isAlive;

    Dragon(String name, int age) {
        this.name = name;
        this.age = age;
        this.eggs = new ArrayList<>();
        this.children = new ArrayList<>();
        this.isAlive = true;
    }

    boolean isAlive() {
        return this.isAlive;
    }

    String getName() {
        return this.name;
    }

    void lay(Egg egg) {
        this.eggs.add(egg);
    }

    Iterable<Egg> getEggs() {
        return this.eggs;
    }

    Iterable<Dragon> getChildren() {
        return this.children;
    }

    void lay() {
        if (this.age >= AGE_LAY_EGGS_START && this.age <= AGE_LAY_EGGS_END) {
            Egg egg = new Egg(this, - 1);
            this.lay(egg);
        }
    }

    void age() {
        if (this.isAlive) {
            this.age += 1;
        }
        if (this.age == AGE_DEATH) {
            this.isAlive = false;
        }
    }

    void increaseOffspring(Dragon baby) {
        this.children.add(baby);
    }

    @Override
    public String toString() {
        return this.getName();
    }
}

class Egg {
    private static final int AGE_HATCH = 2;
    private int age;
    private Dragon parent;
    private YearType yearFactor;

    Egg(Dragon parent, int age) {
        this.age = age;
        this.parent = parent;
    }

    void setYearFactor(YearType yearFactor) {
        this.yearFactor = yearFactor;
    }

    void age() {
        this.age += 1;
    }

    void hatch() {
        if (this.age == AGE_HATCH) {
            int yearFactor = this.yearFactor.ordinal();
            for (int i = 0; i < yearFactor; i++) {
                Dragon baby = new Dragon(
                        this.parent.getName() + "/" + "Dragon_" + DragonEra.dragonsCount,
                        - 1
                );

                this.parent.increaseOffspring(baby);
                DragonEra.dragonsCount++;
            }
        }
    }
}

enum YearType {
    Bad, Normal, Good
}