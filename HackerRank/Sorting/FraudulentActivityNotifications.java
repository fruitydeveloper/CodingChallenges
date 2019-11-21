import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int getMedian(int[] countingSort, int d) {
        int position = 0;
        // As countingSort array is already in sorted order,
        // we just need to count till the middle element
        for(int i=0; i<countingSort.length; i++) {
            position += countingSort[i];
            if(position >= (d/2)+1) {
                if(d%2 == 1) {
                    // Just a single center element
                    return i*2;
                } else if(position == (d/2)+1) {
                    // Two center elements: Find the second element
                    for(int j=i+1; j<countingSort.length; j++) {
                        if(countingSort[j] > 0) {
                            return i+j;
                        } else {
                            return 0;
                        }
                    }
                } else {
                    // Two center elements, but they are both the same value
                    return i*2;
                }
            }
        }
        return 0;
    }

    // Complete the activityNotifications function below.
    static int activityNotifications(int[] expenditure, int d) {
        int notifications = 0;
        // 0 <= expenditure[i] <= 200
        // Counting sort will keep the frequencies of each element
        // as values and their values as keys.
        int[] countingSort = new int[201];

        // Initialize all values as 0's
        for(int i=0; i<countingSort.length; i++) {
            countingSort[i] = 0;
        }

        // fill in the minimum transaction data
        for(int i=0; i<d; i++){
            countingSort[expenditure[i]] += 1;
        }
        
        for(int i=d; i<expenditure.length; i++) {
            int median = getMedian(countingSort, d);
            if(expenditure[i] >= median) {
                notifications++;
            }
            
            // Add new index to countingSort array
            countingSort[expenditure[i]] += 1;
            // And remove old index
            countingSort[expenditure[i-d]] -= 1;
        }

        return notifications;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] expenditure = new int[n];

        String[] expenditureItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int expenditureItem = Integer.parseInt(expenditureItems[i]);
            expenditure[i] = expenditureItem;
        }

        int result = activityNotifications(expenditure, d);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
