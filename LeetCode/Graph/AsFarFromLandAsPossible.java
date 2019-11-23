class Solution {
    
    private static int[][] NEIGHBOURS = {
        {-1,0}, {1, 0}, {0, -1}, {0, 1}
    };

    public int maxDistance(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) return -1;
        int N = grid.length;
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> queue = new ArrayDeque<>();
        
        // Fill in queue with all 1's
        for(int row = 0; row < N; row++) {
            for(int col = 0; col < N; col++) {
                if(grid[row][col] == 1) {
                   queue.offer(new int[]{row, col});
                    visited[row][col] = true;
                }
            }
        }
        
        int level = -1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                int[] e = queue.poll();
                int row = e[0];
                int col = e[1];
                for(int[] n : NEIGHBOURS) {
                    int newRow = row + n[0];
                    int newCol = col + n[1];
                    if(isValid(newRow, newCol, N) && visited[newRow][newCol] == false && grid[newRow][newCol] == 0) {
                        queue.offer(new int[]{newRow, newCol});
                        visited[newRow][newCol] = true;
                    }
                }
            }
            level++;
        }
        
        return level <= 0 ? -1 : level; 
    }
    
    private boolean isValid(int row, int col, int N) {
        return row >= 0 && col >= 0 && row < N && col < N;
    }
}