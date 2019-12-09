class Solution {
    int rows;
    int cols;
    
    int[][] NEIGHBOURS = {{1,0},{0,1}};
    
    /**
        Find the Kth smallest element in a matrix that is sorted
        both vertically and horizontally. E.g:
        1 5 10
        2 7 15
        4 8 16
     */
    public int kthSmallest(int[][] matrix, int k) {
        if(k == 1) return matrix[0][0];
        rows = matrix.length;
        cols = matrix[0].length;
        Queue<Point> queue = new PriorityQueue<>((a,b) -> a.value - b.value);
        boolean[][] visited = new boolean[rows][cols];

        queue.add(new Point(0, 0, matrix[0][0]));
        
        Point cur = null;
        
        while(k-- > 0 && !queue.isEmpty()) {
            cur = queue.remove();
            for(int[] n : NEIGHBOURS) {
                int r = cur.row + n[0];
                int c = cur.col + n[1];
                if(isValid(r, c) && !visited[r][c] ) {
                    queue.add(new Point(r, c, matrix[r][c]));
                    visited[r][c] = true;
                }
            }
        } 
        return cur.value;
    }
    
    boolean isValid(int row, int col) {
        return row >= 0 && col >= 0 && row < rows && col < cols;
    }
}

class Point {
    int row;
    int col;
    int value;
    public Point(int row, int col, int value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }
}