import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Node {
    public int id;
    public int path;
    public int parent;

    public Node(int id, int path, int parent) {
        this.id = id;
        this.path = path;
        this.parent = parent;
    }
}

public class Solution {

    // Complete the findShortest function below.

    /*
     * Find the nearest node with the same color or return -1
     *
     * For the unweighted graph, <name>:
     *
     * 1. The number of nodes is <name>Nodes.
     * 2. The number of edges is <name>Edges.
     * 3. An edge exists between <name>From[i] to <name>To[i].
     *
     */
    static int findShortest(int graphNodes, int[] graphFrom, int[] graphTo, long[] ids, int val) {
        Queue<Node> queue = new ArrayDeque<>();
        LinkedList<Node>[] adj = new LinkedList[graphNodes+1];
        for(int i=0; i<=graphNodes; i++) adj[i] = new LinkedList<>();

        // Create adjacency list
        for(int i=0; i<graphFrom.length; i++) {
            int from = graphFrom[i];
            int to = graphTo[i];
            adj[from].add(new Node(to, -1, -1));
            adj[to].add(new Node(from, -1, -1));
        }

        // Add starting points and 
        for(int i=0; i<ids.length; i++) {
            if(ids[i] == val) {
                queue.offer(new Node(i+1, 0, i+1));
            }
        }

        // BFS from all starting points. By updating neighbours parents
        // to starting node and increasing paths. Return when two paths
        // with distinct parents connect
        while(!queue.isEmpty()) {
            Node cur = queue.poll();

            for(Node a : adj[cur.id]) {
                if(a.parent != -1 && a.parent != cur.parent) {
                    return a.path + cur.path - 1;
                }
                if(a.parent == -1) {
                    a.parent = cur.parent;
                    a.path = cur.path + 1;
                    queue.offer(a);
                }
            }
        }

        return -1;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] graphNodesEdges = scanner.nextLine().split(" ");
        int graphNodes = Integer.parseInt(graphNodesEdges[0].trim());
        int graphEdges = Integer.parseInt(graphNodesEdges[1].trim());

        int[] graphFrom = new int[graphEdges];
        int[] graphTo = new int[graphEdges];

        for (int i = 0; i < graphEdges; i++) {
            String[] graphFromTo = scanner.nextLine().split(" ");
            graphFrom[i] = Integer.parseInt(graphFromTo[0].trim());
            graphTo[i] = Integer.parseInt(graphFromTo[1].trim());
        }

        long[] ids = new long[graphNodes];

        String[] idsItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < graphNodes; i++) {
            long idsItem = Long.parseLong(idsItems[i]);
            ids[i] = idsItem;
        }

        int val = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int ans = findShortest(graphNodes, graphFrom, graphTo, ids, val);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
