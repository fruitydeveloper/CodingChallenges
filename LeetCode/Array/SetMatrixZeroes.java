class Solution {
    // Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place
    public void setZeroes(int[][] matrix) {
        boolean isCol = false;
        
        // Use first row and column to mark if the row/col needs to be set to zero
        for(int row = 0; row < matrix.length; row++) {
            // Since first cell for both first row and first column is the same i.e. matrix[0][0]
            // We can use an additional variable for either the first row/column.
            // For this solution we are using an additional variable for the first column
            // and using matrix[0][0] for the first row.
            if(matrix[row][0] == 0) {
                isCol = true;
            }
            for(int col = 1; col < matrix[row].length; col++) {
                if(matrix[row][col] == 0) {
                    matrix[row][0] = 0;
                    matrix[0][col] = 0;
                }
            }
        }
        // Update rows and cols to 0 based on the result from previous pass
        // Ignore first row and col for now to not ruin data
        for(int row = 1; row < matrix.length; row++) {
            for(int col = 1; col < matrix[row].length; col++) {
                if(matrix[row][0] == 0 || matrix[0][col] == 0) {
                    matrix[row][col] = 0;
                }
            }
        }
        // See if the first row needs to be set to zero as well
        if (matrix[0][0] == 0) {
          for (int col = 0; col < matrix[0].length; col++) {
            matrix[0][col] = 0;
          }
        }

        // See if the first column needs to be set to zero as well
        if (isCol) {
          for (int row = 0; row < matrix.length; row++) {
            matrix[row][0] = 0;
          }
        }
    }
}