class Solution {
    // Use a Disjoint sets data structure
    // to find loops in undirected graphs
    public int[] findRedundantConnection(int[][] edges) {
        DS<Integer> ds = new DS<>();
        for(int[] edge : edges) {
            ds.makeSetIfAbsent(edge[0]);
            ds.makeSetIfAbsent(edge[1]);
            if(!ds.union(edge[0], edge[1])) {
                // When a union existed, that's when a loop was intruduced
                return edge;
            }
        }
        return new int[]{0,0};
    }
}

class Node<T> {
    public int rank;
    public Node parent;
    public T value;
}

class DS<T> {
    Map<T, Node> map;
    
    public DS() {
        map = new HashMap<>();
    }
    
    // Only make a set when one does not exist
    public void makeSetIfAbsent(T value) {
        if(map.get(value) == null) {
            makeSet(value);
        }
    }
    
    // Make a single value into a set
    public void makeSet(T value) {
        Node<T> node = new Node<>();
        node.rank = 0;
        node.parent = node;
        node.value = value;
        map.put(value, node);
    }
    
    // Create a union between two sets
    // Return true if union created and false
    // if it already existed
    public boolean union(T valueA, T valueB) {
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
        } else if(setB.rank > setA.rank) {
            setA.parent = setB;
        } else {
            setB.parent = setA;
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