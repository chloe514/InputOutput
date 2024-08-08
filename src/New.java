import java.io.*;
import java.util.*;

public class New {

    public static void main(String[] args) {
        String inputFile1 = "src/input1.txt";
        String inputFile2 = "src/input2.txt";
        String mergedFile = "merged.txt";
        String commonFile = "common.txt";

        try {
            // Read integers from the first input file
            List<Integer> list1 = readIntegersFromFile(inputFile1);

            // Read integers from the second input file
            List<Integer> list2 = readIntegersFromFile(inputFile2);

            // Merge the contents of the two input files
            List<Integer> mergedList = new ArrayList<>(list1);
            mergedList.addAll(list2);
            writeIntegersToFile(mergedList, mergedFile);

            // Identify integers present in both input files
            Set<Integer> commonSet = new HashSet<>(list1);
            commonSet.retainAll(list2);
            List<Integer> commonList = new ArrayList<>(commonSet);
            writeIntegersToFile(commonList, commonFile);

            System.out.println("Merging and finding common integers completed successfully.");

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IO error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format: " + e.getMessage());
        }
    }

    private static List<Integer> readIntegersFromFile(String fileName) throws IOException {
        List<Integer> integers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    integers.add(Integer.parseInt(line));
                } catch (NumberFormatException e) {
                    System.err.println("Skipping invalid number format in file " + fileName + ": " + line);
                }
            }
        }
        return integers;
    }

    private static void writeIntegersToFile(List<Integer> integers, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Integer number : integers) {
                writer.write(number.toString());
                writer.newLine();
            }
        }
    }
}


