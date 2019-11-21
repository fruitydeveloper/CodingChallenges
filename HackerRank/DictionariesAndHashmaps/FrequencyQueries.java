import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Solution {

    static void add(int i, Map<Integer, Integer> frequencies,
        Map<Integer, Integer> locations) {

        int oldCount = locations.getOrDefault(i, 0);
        int newCount = oldCount + 1;
        locations.put(i, newCount);

        if (frequencies.containsKey(oldCount)) {
            frequencies.put(oldCount, frequencies.getOrDefault(oldCount, 1) - 1);
        }

        frequencies.put(newCount, frequencies.getOrDefault(newCount, 0) + 1);
    }

    static void remove(int i, Map<Integer, Integer> frequencies,
        Map<Integer, Integer> locations) {

        int oldCount = locations.getOrDefault(i, 0);
        int newCount = (oldCount > 1) ? oldCount - 1 : 0;
        locations.put(i, newCount);

        if (frequencies.containsKey(oldCount)) {
            frequencies.put(oldCount, frequencies.getOrDefault(oldCount, 1) - 1);
        }

        frequencies.put(newCount, frequencies.getOrDefault(newCount, 0) + 1);
    }

    static Integer isPresent(int i, Map<Integer, Integer> frequencies) {
        return frequencies.getOrDefault(i, 0) <= 0 ? 0 : 1;
    }

    // Complete the freqQuery function below.
    static List<Integer> freqQuery(BufferedReader bufferedReader, int q) 
        throws IOException {

        Map<Integer, Integer> frequencies = new HashMap<>(q);
        Map<Integer, Integer> locations = new HashMap<>(q);
        List<Integer> results = new ArrayList<>();

        for(int i=0; i<q; i++) {
            String[] query = bufferedReader.readLine().split(" ");
            int operation = Integer.parseInt(query[0]);
            int number = Integer.parseInt(query[1]);

            switch(operation) {
                case 1:
                    add(number, frequencies, locations);
                    break;
                case 2:
                    remove(number, frequencies, locations);
                    break;
                case 3:
                    results.add(isPresent(number, frequencies));
                    break;
            }
        }

        return results;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(System.in))) {
            
            int q = Integer.parseInt(bufferedReader.readLine().trim());

            List<Integer> ans = freqQuery(bufferedReader, q); 
          
            try (BufferedWriter bufferedWriter = new BufferedWriter(
                        new FileWriter(System.getenv("OUTPUT_PATH")))) {
            
                bufferedWriter.write(ans.stream().map(Object::toString)
                            .collect(joining("\n")) + "\n");
            }
        }
    }
}
