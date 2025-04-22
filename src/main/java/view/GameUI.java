package view;

public interface GameUI {
    void setController(GameController controller);
    void updateCell(int row, int col, int value, boolean isFixed);
    void showMessage(String message);
}