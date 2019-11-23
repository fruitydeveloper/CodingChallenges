import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int luckBalance(int k, int[][] contests) {
        List<Integer> importantContests = new ArrayList<>();
        int luck = 0;
        for(int[] contest : contests) {
            int value = contest[0];
            if(contest[1] == 0) {
                // Win non-important if luck is negative or lose otherwise
                luck += Math.abs(value);
            } else {
                importantContests.add(value);
            }
        }


        Collections.sort(importantContests);
        // Edge case when k is larger than important contest size
        if(k > importantContests.size()) {
            k = importantContests.size();
        }

        // Can only lose k important contests. Lose ones that grant highest luck
        // and win those that grant negative or lowest luck
        for(int i = importantContests.size()-1; i > importantContests.size()-1-k; i--) {
            luck += importantContests.get(i);
        }

        for(int i = 0; i < importantContests.size()-k; i++) {
            luck -= importantContests.get(i);
        }
        return luck;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[][] contests = new int[n][2];

        for (int i = 0; i < n; i++) {
            String[] contestsRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int contestsItem = Integer.parseInt(contestsRowItems[j]);
                contests[i][j] = contestsItem;
            }
        }

        int result = luckBalance(k, contests);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
