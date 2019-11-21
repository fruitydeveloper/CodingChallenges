import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int[] combineSorted(int[] a, int[] b) {
        int indexA = 0;
        int indexB = 0;
        int indexMerged = 0;
        int[] merged = new int[a.length + b.length];

        // Compare both lists and take the smallest element
        while(indexA < a.length && indexB < b.length) {
            if(a[indexA] < b[indexB]){
                merged[indexMerged++] = a[indexA++];
            } else {
                merged[indexMerged++] = b[indexB++];
            }
        }

        // Fill merged with remaining items
        while(indexA < a.length) {
            merged[indexMerged++] = a[indexA++];
        }
        while(indexB < b.length) {
            merged[indexMerged++] = b[indexB++];
        }
        
        return merged;
    }

    static int[] mergeSort(int[] list){
        if(list.length <= 1){
            return list;
        }

        int split = list.length / 2;
        int[] a = new int[split];
        int[] b = new int[list.length-split];

        for(int i=0; i<split; i++){
            a[i] = list[i];
        }
        for(int i=split; i<list.length; i++){
            b[i-split] = list[i];
        }

        return combineSorted(mergeSort(a), mergeSort(b));
    }

    // Complete the maximumToys function below.
    static int maximumToys(int[] prices, int k) {
        int[] sorted = mergeSort(prices);
        int toys = 0;
        int price = 0;

        for(int toy : sorted) {
            price += toy;
            if(price > k) {
                break;
            }
            toys++;
        }
        
        return toys;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] prices = new int[n];

        String[] pricesItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int pricesItem = Integer.parseInt(pricesItems[i]);
            prices[i] = pricesItem;
        }

        int result = maximumToys(prices, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
