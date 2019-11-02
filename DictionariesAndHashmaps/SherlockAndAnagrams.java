import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static String sortCharArray(char[] arr, int start, int end) {
        char[] sorted = new char[end-start];
        for(int i=start; i<end; i++) {
            sorted[i-start] = arr[i];
        }
        Arrays.sort(sorted);
        return new String(sorted);
    }

    // Complete the sherlockAndAnagrams function below.
    static int sherlockAndAnagrams(String s) {
        char[] arr = s.toCharArray();
        int length = arr.length;
        int subLength = 1, count = 0;

        while(subLength < length) {
            HashMap<String, Integer> anagrams = new HashMap<>();
            for(int i=0; i<=length-subLength; i++) {
                String subString = sortCharArray(arr, i, i+subLength);
                Integer anagram = anagrams.get(subString);
                if(anagram != null) {
                    count += anagram;
                    anagrams.put(subString, anagram+1);
                } else {
                    anagrams.put(subString, 1);
                }
            }
            subLength++;
        }
        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = sherlockAndAnagrams(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
