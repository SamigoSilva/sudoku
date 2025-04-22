package model;

import java.util.Random;

public class Board {
    private int[][] grid;
    private boolean[][] fixed;
    private static final int SIZE = 9;
    private static final int EMPTY = 0;

    public Board() {
        grid = new int[SIZE][SIZE];
        fixed = new boolean[SIZE][SIZE];
        generatePuzzle();
    }

    private void generatePuzzle() {
        // Gerar solução completa
        solve(0, 0);
        
        // Remover células para criar o puzzle
        Random random = new Random();
        int cellsToRemove = 45; // Dificuldade média
        
        while (cellsToRemove > 0) {
            int row = random.nextInt(SIZE);
            int col = random.nextInt(SIZE);
            
            if (grid[row][col] != EMPTY) {
                grid[row][col] = EMPTY;
                fixed[row][col] = false;
                cellsToRemove--;
            }
        }
    }

    private boolean solve(int row, int col) {
        if (row == SIZE) {
            return true;
        }
        
        if (col == SIZE) {
            return solve(row + 1, 0);
        }
        
        if (grid[row][col] != EMPTY) {
            return solve(row, col + 1);
        }
        
        for (int num = 1; num <= SIZE; num++) {
            if (isValidPlacement(row, col, num)) {
                grid[row][col] = num;
                
                if (solve(row, col + 1)) {
                    return true;
                }
                
                grid[row][col] = EMPTY;
            }
        }
        
        return false;
    }

    public boolean isValidPlacement(int row, int col, int num) {
        // Verificar linha e coluna
        for (int i = 0; i < SIZE; i++) {
            if (grid[row][i] == num || grid[i][col] == num) {
                return false;
            }
        }
        
        // Verificar subgrade 3x3
        int subgridStartRow = row - row % 3;
        int subgridStartCol = col - col % 3;
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[subgridStartRow + i][subgridStartCol + j] == num) {
                    return false;
                }
            }
        }
        
        return true;
    }

    // Getters e setters
    public int getValue(int row, int col) {
        return grid[row][col];
    }

    public void setValue(int row, int col, int value) {
        if (!fixed[row][col]) {
            grid[row][col] = value;
        }
    }

    public boolean isFixed(int row, int col) {
        return fixed[row][col];
    }
}