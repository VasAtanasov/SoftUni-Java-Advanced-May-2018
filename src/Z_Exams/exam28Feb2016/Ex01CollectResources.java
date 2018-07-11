package Z_Exams.exam28Feb2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Ex01CollectResources {
    private static BufferedReader reader;
    private static Resource[] resources;
    private static final List<String> validResources;
    private static int maxQuantity;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        validResources = Arrays.asList("wood", "stone", "food", "gold");
        maxQuantity = 0;
    }

    public static void main(String[] args) throws IOException {
        createResourcesObjects();

        int count = Integer.parseInt(reader.readLine());
        for (int i = 0; i < count; i++) {
            int collectedQuantity = collectResources();
            if (collectedQuantity > maxQuantity) {
                maxQuantity = collectedQuantity;
            }
            Arrays.stream(resources).forEach(Resource::reset);
        }

        System.out.println(maxQuantity);
    }

    private static int collectResources() throws IOException {
        String[] tokens = reader.readLine().split("\\s+");
        int start = Integer.parseInt(tokens[0]);
        int step = Integer.parseInt(tokens[1]);
        int collectedQuantity = 0;

        for (int index = start; true; index += step) {
            Resource resource = resources[index % resources.length];
            if (resource.isCollected()) {
                break;
            } else if (validResources.contains(resource.getType())) {
                collectedQuantity += resource.collect();
            }
        }
        return collectedQuantity;
    }

    private static void createResourcesObjects() throws IOException {
        resources = Arrays.stream(reader.readLine().split("\\s+"))
                .map(mapResource)
                .toArray(Resource[]::new);
    }

    private static Function<String, Resource> mapResource = resource -> {
        String[] tokens = resource.split("_");
        if (tokens.length == 1) {
            return new Resource(tokens[0], 1);
        } else {
            return new Resource(tokens[0], Integer.parseInt(tokens[1]));
        }
    };
}

class Resource {
    private String type;
    private int quantity;
    private boolean isCollected;

    Resource(String type, int quantity) {
        this.type = type;
        this.quantity = quantity;
        this.isCollected = false;
    }

    String getType() {
        return this.type;
    }

    int collect() {
        this.isCollected = true;
        return this.quantity;
    }

    boolean isCollected() {
        return this.isCollected;
    }

    void reset() {
        this.isCollected = false;
    }
}
