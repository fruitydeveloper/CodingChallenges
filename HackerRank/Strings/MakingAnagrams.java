import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the makeAnagram function below.
    static int makeAnagram(String a, String b) {
        int charsToDelete = 0;
        HashMap<Character, Integer> characters = new HashMap<>();

        for(char c : a.toCharArray()) {
            int charCount = characters.getOrDefault(c, 0);
            characters.put(c, charCount + 1);
        }

        for(char c : b.toCharArray()) {
            int charCount = characters.getOrDefault(c, 0);
            if(charCount - 1 < 0) {
                charsToDelete++;
            }
            characters.put(c, charCount - 1);
        }

        System.out.println(charsToDelete);

        for(char c : characters.keySet()) {
            int charCount = characters.get(c);
            if(charCount > 0) {
                charsToDelete += charCount;
            }
        }

        return charsToDelete;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String a = scanner.nextLine();

        String b = scanner.nextLine();

        int res = makeAnagram(a, b);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
