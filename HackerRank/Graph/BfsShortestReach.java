import java.io.*;
import java.util.*;

public class Solution {

    private static void solve(LinkedList<Integer>[] adj, int start, int nodes) {
        Queue<Integer> queue = new ArrayDeque<>();
        int[] results = new int[nodes + 1];
        queue.offer(start);

        for(int i=0; i<=nodes; i++) results[i] = -1;
        results[start] = 0;

        int distance = 0;
        while(!queue.isEmpty()) {
            distance += 6;
            int size = queue.size();

            while(size-- > 0) {
                int cur = queue.poll();

                for(int edge : adj[cur]) {
                    if(results[edge] != -1) {
                        continue;
                    }
                    results[edge] = distance;
                    queue.offer(edge);
                }
            }
        }

        for(int i=1; i<=nodes; i++) {
            if(i==start) {
                continue;
            }
            System.out.printf("%d ", results[i]);
        }
        System.out.printf("\n");
    }


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        int testCases = Integer.parseInt(scanner.nextLine());

        for(int t=0; t<testCases; t++) {
            String[] nodesAndEdges = scanner.nextLine().split(" ");
            int nodes = Integer.parseInt(nodesAndEdges[0].trim());
            int edges = Integer.parseInt(nodesAndEdges[1].trim());
            LinkedList<Integer>[] adj = new LinkedList[nodes+1];

            for(int i=0; i<=nodes; i++) adj[i] = new LinkedList<>();

            for(int i=0; i<edges; i++) {
                String[] edge = scanner.nextLine().split(" ");
                int from = Integer.parseInt(edge[0].trim());
                int to = Integer.parseInt(edge[1].trim());

                adj[from].add(to);
                adj[to].add(from);
            }

            int start = Integer.parseInt(scanner.nextLine());
            solve(adj, start, nodes);
        }
    }
}
