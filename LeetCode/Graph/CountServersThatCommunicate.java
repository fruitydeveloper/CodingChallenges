/*
You are given a map of a server center, represented as a m * n integer matrix grid,
where 1 means that on that cell there is a server and 0 means that it is no server.
Two servers are said to communicate if they are on the same row or on the same column.
*/
class Solution {
    public int countServers(int[][] grid) {
        if(grid.length == 0) return 0;
        int[] rows = new int[grid.length];
        int[] cols = new int[grid[0].length];
        int count = 0;
        
        for(int r = 0; r < grid.length; r++) {
            for(int c = 0; c < grid[r].length; c++) {
                if(grid[r][c] == 1) {
                    rows[r]++;
                    cols[c]++;
                }
            }
        }
        
        for(int r = 0; r < grid.length; r++) {
            for(int c = 0; c < grid[r].length; c++) {
                if(grid[r][c] == 1 && (rows[r] > 1 || cols[c] > 1)) {
                    count++;
                }
            }
        }
        
        return count;
    }
}