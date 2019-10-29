import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the minimumSwaps function below.
    static int minimumSwaps(int[] arr) {
        int length = arr.length;

        int swaps = 0;
        while(true){
            boolean swapped = false;
            for(int i=0; i<length; i++) {
                if(arr[i] - 1 - i < 0) {
                    // Take an element that is behind it's original position
                    // and swap it back to it's original position
                    swapped = true;
                    swaps++;
                    int original = arr[i] - 1;
                    int temp = arr[original];
                    arr[original] = arr[i];
                    arr[i] = temp;
                }
            }
            // Break if an iteration with no swaps has happened
            if(!swapped) {
                break;
            }
        }

        return swaps;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = minimumSwaps(arr);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
