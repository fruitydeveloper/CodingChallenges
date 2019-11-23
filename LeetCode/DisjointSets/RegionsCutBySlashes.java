class Solution {
    /**
    ------  Every square is represented as 4 direct
    |\ 0/|  0 - south, 1 - east, 2 - north, 3 - west
    |3\/ |
    | /\1|
    |/2 \|
    ------
    */
    public int regionsBySlashes(String[] grid) {
        int N = grid.length;
        DS<Integer> ds = new DS<>(4 * N * N);
        
        // Create all unions
        for(int row = 0; row < N; row++) {
            for(int col = 0; col < N; col++) {
                int root = 4 * (row * N + col);
                char value = grid[row].charAt(col);
                
                // Connect inside the cell
                if(value != '\\') {
                    ds.union(root + 0, root + 1);
                    ds.union(root + 2, root + 3);
                }
                
                if(value != '/') {
                    ds.union(root + 0, root + 3);
                    ds.union(root + 1, root + 2);
                }
                
                // Connect to neighbour cells
                // nort
                if(row + 1 < N) {
                    ds.union(root + 2, (root + 4 * N) + 0);
                }
                // south
                if(row - 1 >= 0) {
                    ds.union(root + 0, (root - 4 * N) + 2);
                }
                // east
                if(col + 1 < N) {
                    ds.union(root + 3, (root + 4) + 1);
                }
                // west
                if(col - 1 >= 0) {
                    ds.union(root + 1, (root - 4) + 3);
                }
            }
        }
        
        // Count number of different sets
        int result = 0;
        for(int i = 0; i < 4 * N * N; i++) {
            if(ds.findSet(i) == i) {
                result++;
            }
        }
        
        return result;
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
    
    public DS(int size) {
        map = new HashMap<>(size);
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
        makeSetIfAbsent(valueA);
        makeSetIfAbsent(valueB);
        
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