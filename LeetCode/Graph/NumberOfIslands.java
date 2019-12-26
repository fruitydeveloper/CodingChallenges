/*
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
An island is surrounded by water and is formed by connecting adjacent lands
horizontally or vertically.
You may assume all four edges of the grid are all surrounded by water.

    Input:
    11000
    11000
    00100
    00011

    Output: 3
*/
class Solution {
    
    int[][] NEIGHBOURS = {{-1,0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int numIslands(char[][] grid) {
        if(grid.length == 0) return 0;
        int rows = grid.length, cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int islands = 0;
        
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                if(!visited[row][col] && grid[row][col] == '1') {
                    dfs(grid, visited, row, col);
                    islands++;
                }
            }
        }
        
        return islands;
    }
    
    private void dfs(char[][] grid, boolean[][] visited, int row, int col) {
        int rows = grid.length, cols = grid[0].length;
        if(row >= rows || row < 0 || col >= cols || col < 0 || visited[row][col] || grid[row][col] != '1') {
            return;
        }
        visited[row][col] = true;
        for(int[] n : NEIGHBOURS) {
            dfs(grid, visited, row + n[0], col + n[1]);
        }
    }
}