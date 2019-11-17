import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static long mergesort(int[] arr, int[] temp, int leftStart, int rightEnd) {
        if(leftStart >= rightEnd) {
            return 0;
        }
        long result = 0;
        int middle = (leftStart + rightEnd) / 2;
        result += mergesort(arr, temp, leftStart, middle);
        result += mergesort(arr, temp, middle + 1, rightEnd);
        result += combineHalves(arr, temp, leftStart, rightEnd);
        return result;
    }

    static long combineHalves(int[] arr, int[] temp, int leftStart, int rightEnd) {
        int leftEnd = (leftStart + rightEnd) / 2;
        int rightStart = leftEnd + 1;
        long result = 0;

        int left = leftStart;
        int right = rightStart;
        int index = leftStart;

        while(left <= leftEnd && right <= rightEnd) {
            if(arr[left] > arr[right]) {
                temp[index] = arr[right];
                right++;
                // Elements were inverted
                result += leftEnd + 1 - left;
            } else {
                temp[index] = arr[left];
                left++;
            }
            index++;
        }

        System.arraycopy(arr, left, temp, index, leftEnd - left + 1);
        System.arraycopy(arr, right, temp, index, rightEnd - right + 1);
        System.arraycopy(temp, leftStart, arr, leftStart, rightEnd - leftStart + 1);
        
        return result;
    }

    // Complete the countInversions function below.
    static long countInversions(int[] arr) {

        return mergesort(arr, new int[arr.length], 0, arr.length - 1);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] arr = new int[n];

            String[] arrItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            }

            long result = countInversions(arr);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
