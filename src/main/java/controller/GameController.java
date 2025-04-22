package controller;

import model.Board;
import view.GameUI;

public class GameController {
    private Board board;
    private GameUI ui;

    public GameController(GameUI ui) {
        this.board = new Board();
        this.ui = ui;
        ui.setController(this);
    }

    public void startGame() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                ui.updateCell(row, col, board.getValue(row, col), board.isFixed(row, col));
            }
        }
    }

    public void setCellValue(int row, int col, int value) {
        if (value >= 0 && value <= 9) {
            board.setValue(row, col, value);
        }
    }

    public void checkSolution() {
        if (board.isComplete()) {
            ui.showMessage("Parabéns! Você resolveu o Sudoku!");
        } else {
            ui.showMessage("Ainda há erros no tabuleiro. Continue tentando!");
        }
    }
}