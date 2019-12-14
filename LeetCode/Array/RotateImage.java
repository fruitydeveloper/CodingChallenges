class Solution {
    // Rotate 2D array by 90 degrees
    public void rotate(int[][] matrix) {
        /* Transpose matrix
        1  2  3      1  4  7           
        4  5  6  to  2  5  8
        7  8  9      3  6  9
        */
        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        /* Flip matrix horizontally
        1  4  7       7  4  1
        2  5  8   to  8  5  2
        3  6  9       9  6  3
        */
        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[i].length / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix[i].length - j - 1];
                matrix[i][matrix[i].length - j - 1] = temp;
            }
        }
    }
}