import java.io.*;
import java.util.*;

public class NameProcessor {
    public static void main(String[] args) {
        // Step 1: Generate and Randomize Names
        List<String> names = Arrays.asList("Bob", "Carol", "Ted", "Alice", "John", "Jane", "Tom", "Jerry", "Mike", "Sara", "Lucy", "Sam", "Anna", "Chris", "Paul");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("names.dat"))) {
            for (int i = 0; i < 10; i++) {
                Collections.shuffle(names);
                for (String name : names) {
                    writer.write(name + " ");
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Step 2: Read the Data from the File and Process It
        List<String> allNames = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("names.dat"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] namesArray = line.trim().split(" ");
                allNames.addAll(Arrays.asList(namesArray));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Remove duplicates and sort the names
        Set<String> uniqueNamesSet = new HashSet<>(allNames);
        List<String> uniqueNamesList = new ArrayList<>(uniqueNamesSet);
        Collections.sort(uniqueNamesList);

        // Step 3: Display the Results
        System.out.println("Sorted names without duplicates:");
        for (String name : uniqueNamesList) {
            System.out.println(name);
        }

        // Calculate total duplicates and count of each duplicated name
        Map<String, Integer> nameCountMap = new HashMap<>();
        for (String name : allNames) {
            nameCountMap.put(name, nameCountMap.getOrDefault(name, 0) + 1);
        }

        int totalDuplicates = 0;
        System.out.println("\nDuplicated names and their counts:");
        for (Map.Entry<String, Integer> entry : nameCountMap.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
                totalDuplicates += (entry.getValue() - 1);
            }
        }
        System.out.println("\nTotal duplicates found: " + totalDuplicates);
    }
}
