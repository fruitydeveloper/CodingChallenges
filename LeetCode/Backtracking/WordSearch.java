/*
Given a 2D board and a word, find if the word exists in the grid.
The word can be constructed from letters of sequentially adjacent cell, where "adjacent"
cells are those horizontally or vertically neighboring.
The same letter cell may not be used more than once.

    board =
    [
        ['A','B','C','E'],
        ['S','F','C','S'],
        ['A','D','E','E']
    ]

    Given word = "ABCCED", return true.
    Given word = "SEE", return true.
    Given word = "ABCB", return false.
*/
class Solution {
    private static int[][] NEIGHBOURS = {
        {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };
    
    public boolean exist(char[][] board, String word) {
        char c = word.charAt(0);
        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board[row].length; col++) {
                if(board[row][col] != c) {
                    continue;
                }
                boolean[][] visited = new boolean[board.length][board[row].length];
                visited[row][col] = true;
                boolean result = backtracking(board, visited, word.substring(1), row, col);
                if(result) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean isValid(char[][] board,int r, int c) {
        return r >= 0 && r < board.length && c >= 0 && c < board[r].length;
    }
    
    private boolean backtracking(char[][] board, boolean[][] visited, String word, int r, int c) {
        if(word.length() == 0) {
            return true;
        }
        for(int[] n : NEIGHBOURS) {
            int row = r + n[0];
            int col = c + n[1];
            if(!isValid(board, row, col)) {
                continue;
            }
            if(board[row][col] != word.charAt(0)) {
                continue;
            }
            if(visited[row][col]) {
                continue;
            }
            visited[row][col] = true;
            boolean result = backtracking(board, visited, word.substring(1), row, col);
            visited[row][col] = false;
            if(result) {
                return true;
            }
        }
        return false;
    }
}