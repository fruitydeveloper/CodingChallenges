/*
Given a 2D grid consists of 0s (land) and 1s (water).
An island is a maximal 4-directionally connected group of 0s and a closed island
is an island totally (all left, top, right, bottom) surrounded by 1s.
E.g. Islands that touch the edge of the graph don't count
*/
class Solution {
    
    public int closedIsland(int[][] grid) {
        int islands = 0;
        
        for(int r = 0; r < grid.length; r++) {
            for(int c = 0; c < grid[r].length; c++) {
                if(grid[r][c] == 0) {
                    islands += dfs(grid, r, c);
                }
            }
        }
        
        return islands;
    }
    
    private int dfs(int[][] grid, int row, int col) {
        if(row < 0 || row >= grid.length || col < 0 || col >= grid[row].length) {
            return 0; // An edge is met
        }
        if(grid[row][col] == 1) {
            return 1; // Return 1 when 1 is met
        }
        grid[row][col] = 1;
        int up = dfs(grid, row - 1, col);
        int down = dfs(grid, row + 1, col);
        int left = dfs(grid, row, col - 1);
        int right = dfs(grid, row, col + 1);
        return up & down & left & right; // If any edge is met will return 0
    }
}