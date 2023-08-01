class Solution {
    public void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        
        // Create a copy of the board to store the next state without affecting the current state.
        int[][] nextBoard = new int[m][n];
        
        // Define the eight possible directions of neighbors (horizontal, vertical, and diagonal).
        int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int liveNeighbors = 0;
                
                // Count the number of live neighbors for the current cell.
                for (int[] dir : directions) {
                    int newRow = i + dir[0];
                    int newCol = j + dir[1];
                    
                    if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n) {
                        liveNeighbors += board[newRow][newCol] & 1; // We use the last bit to keep the current state.
                    }
                }
                
                // Apply the rules to update the next state.
                if ((board[i][j] == 1) && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    nextBoard[i][j] = 0; // Cell dies due to under-population or over-population.
                } else if (board[i][j] == 0 && liveNeighbors == 3) {
                    nextBoard[i][j] = 1; // Cell becomes alive due to reproduction.
                } else {
                    nextBoard[i][j] = board[i][j]; // Cell remains the same.
                }
            }
        }
        
        // Update the original board with the next state.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = nextBoard[i][j];
            }
        }
    }
}
