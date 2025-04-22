public class Main {
    public static void main(String[] args) {
        // Para versão console
        // GameController controller = new GameController(new ConsoleUI());
        
        // Para versão gráfica
        GameController controller = new GameController(new SwingUI());
        controller.startGame();
    }
}
