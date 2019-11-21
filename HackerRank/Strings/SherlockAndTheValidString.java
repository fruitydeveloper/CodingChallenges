import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the isValid function below.
    static String isValid(String s) {
        Map<Integer, Integer> chars = new HashMap<>();

        for(char c : s.toCharArray()) {
            chars.put(c - 'a', chars.getOrDefault(c - 'a', 0) + 1);
        }

        List<Integer> values = new ArrayList<>(chars.values());
        Collections.sort(values);

        long firstValues = values.stream().filter(x -> x == values.get(0)).count();
        long lastValues = values.stream().filter(x -> x == values.get(values.size()-1)).count();
        if(firstValues == values.size() || 
            (firstValues == values.size() - 1 && values.get(values.size()-1) - values.get(values.size()-2) == 1) ||
            (lastValues == values.size() - 1 && values.get(0) == 1)) {
                return "YES";
            }
            return "NO";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        String result = isValid(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
