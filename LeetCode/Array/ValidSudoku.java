class Solution {
    /*
    Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

    - Each row must contain the digits 1-9 without repetition.
    - Each column must contain the digits 1-9 without repetition.
    - Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
    */
    public boolean isValidSudoku(char[][] board) {
        return validateRows(board) && validateColumns(board) && validateBoxes(board);
    }
    private boolean validateRows(char[][] board) {
        for(int row = 0; row < 9; row++) {
            boolean[] numbers = new boolean[9];
            for(int i = 0; i < 9; i++) {
                char c = board[row][i];
                int num = c - '0' - 1;
                if(num > 9 || num < 0) continue;
                if(numbers[num]) {
                    return false;
                }
                numbers[num] = true;
            }
        }
        return true;
    }
    private boolean validateColumns(char[][] board) {
        for(int col = 0; col < 9; col++) {
            boolean[] numbers = new boolean[9];
            for(int i = 0; i < 9; i++) {
                char c = board[i][col];
                int num = c - '0' - 1;
                if(num > 9 || num < 0) continue;
                if(numbers[num]) {
                    return false;
                }
                numbers[num] = true;
            }
        }
        return true;
    }
    private boolean validateBoxes(char[][] board) {
        for(int row = 0; row < 9; row += 3) {
            for(int col = 0; col < 9; col += 3) {
            boolean[] numbers = new boolean[9];
                for(int i = 0; i < 9; i++) {
                    int boxRow = row + (i / 3);
                    int boxCol = col + (i % 3);
                    char c = board[boxRow][boxCol];
                    int num = c - '0' - 1;
                    if(num > 9 || num < 0) continue;
                    if(numbers[num]) {
                        return false;
                    }
                    numbers[num] = true;
                }
            }
        }
        return true;
    }
}