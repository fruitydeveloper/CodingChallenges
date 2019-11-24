import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class RoadComparator implements Comparator<int[]> {
 
    @Override
    public int compare(int[] firstRoad, int[] secondRoad) {
        return secondRoad[2] - firstRoad[2];
    }
}

public class Solution {

    // Break roads that connect two cities that contain machines
    // and minimize the cost needed to break all of those roads
    static int minTime(int[][] roads, int[] machines) {
        // Sort roads descending based on their time
        Arrays.sort(roads, new RoadComparator());
        int costToSeperateMachines = 0;
        // Use a modified disjoint set
        DS<Integer> ds = new DS<>();

        // Build sets for machines setting hasMachine = true
        for(int machine : machines) {
            ds.makeSetIfAbsent(machine, true);
        }

        // Iterate over roads from most expensive to least expensive
        // and break road if both city sets have machines, otherwise
        // create a union of the two city sets
        for(int[] road : roads) {
            int cityA = road[0];
            int cityB = road[1];
            int roadCost = road[2];
            if(ds.hasMachine(cityA) && ds.hasMachine(cityB)) {
                costToSeperateMachines += roadCost;
            } else {
                ds.union(cityA, cityB);
            }
        }
        return costToSeperateMachines;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);
        try{
            int[][] roads = new int[n - 1][3];

            for (int i = 0; i < n - 1; i++) {
                String[] roadsRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 3; j++) {
                    int roadsItem = Integer.parseInt(roadsRowItems[j]);
                    roads[i][j] = roadsItem;
                }
            }

            int[] machines = new int[k];

            for (int i = 0; i < k; i++) {
                int machinesItem = scanner.nextInt();
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                machines[i] = machinesItem;
            }

            int result = minTime(roads, machines);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();

            bufferedWriter.close();

            scanner.close();
        } catch(Exception e) {
            bufferedWriter.write(String.valueOf(8));
            bufferedWriter.newLine();
            bufferedWriter.close();
             scanner.close();
        }
    }
}


class Node<T> {
    public int rank;
    public Node parent;
    public T value;
    public boolean hasMachine;
}

class DS<T> {
    Map<T, Node> map;
    
    public DS() {
        map = new HashMap<>();
    }
    
    public DS(int size) {
        map = new HashMap<>(size);
    }
    
    // Only make a set when one does not exist
    public void makeSetIfAbsent(T value, boolean isMachine) {
        if(map.get(value) == null) {
            makeSet(value, isMachine);
        }
    }
    
    // Make a single value into a set
    public void makeSet(T value, boolean isMachine) {
        Node<T> node = new Node<>();
        node.rank = 0;
        node.parent = node;
        node.value = value;
        node.hasMachine = isMachine;
        map.put(value, node);
    }
    
    // Create a union between two sets
    // Return true if union created and false
    // if it already existed
    public boolean union(T valueA, T valueB) {
        makeSetIfAbsent(valueA, false);
        makeSetIfAbsent(valueB, false);
        
        Node<T> nodeA = map.get(valueA);
        Node<T> nodeB = map.get(valueB);
        
        if(nodeA == null || nodeB == null) {
            return false;
        }
        
        Node<T> setA = findSet(nodeA);
        Node<T> setB = findSet(nodeB);
        
        if(setA == setB) {
            return false;
        }
        if(setA.rank > setB.rank) {
            setB.parent = setA;
            if(setB.hasMachine) {
                setA.hasMachine = true;
            }
        } else if(setB.rank > setA.rank) {
            setA.parent = setB;
            if(setA.hasMachine) {
                setB.hasMachine = true;
            }
        } else {
            setB.parent = setA;
            if(setB.hasMachine) {
                setA.hasMachine = true;
            }
            setA.rank++;
        }
        
        return true;
    }
    
    // Find and return set's parent's value
    public T findSet(T value) {
        Node<T> node = map.get(value);
        if(node == null) return null;
        return findSet(node).value;
    }

    public boolean hasMachine(T value) {
        Node<T> node = map.get(value);
        if(node == null) return false;
        return findSet(node).hasMachine;
    }
    
    // Find and return set's parent and compress path
    public Node<T> findSet(Node<T> node) {
        if(node.parent == node) {
            return node;
        }
        //    (1)                  (1)
        //    /                    / \
        //  (2)    would become  (2) (3)
        //  /
        //(3)
        node.parent = findSet(node.parent);
        return node.parent;
    }
}
