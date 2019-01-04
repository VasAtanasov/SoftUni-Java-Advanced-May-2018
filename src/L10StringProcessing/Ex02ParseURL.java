package L10StringProcessing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex02ParseURL {
    private static BufferedReader reader;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {
        String URL = reader.readLine();

        int indexOfProtocol = URL.indexOf("://");

        if (indexOfProtocol < 0) {
            System.out.println("Invalid URL");
            return;
        }

        String protocol = URL.substring(0, indexOfProtocol);

        URL = URL.substring(indexOfProtocol+3);

        if (URL.contains("://")) {
            System.out.println("Invalid URL");
            return;
        }

        int indexOfServer = URL.indexOf("/");

        String server = URL.substring(0, indexOfServer);

        String resources = URL.substring(indexOfServer + 1);

        System.out.println(String.format("Protocol = %s",protocol));
        System.out.println(String.format("Server = %s",server));
        System.out.println(String.format("Resources = %s",resources));


    }
}
