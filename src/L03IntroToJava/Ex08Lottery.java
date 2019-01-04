package L03IntroToJava;

public class Ex08Lottery {
    public static void main(String[] args) {
        for (int i = 1; i < 9; i++) {
            for (int j = i+1; j < 10; j++) {
                for (int k = j+1; k < 11; k++) {
                    System.out.println(i + " " + j + " " + k);
                }
            }
        }
    }
}
