public class Main {
    public static void main(String[] args) {
        GameModel model = new GameModel();
        GameView view = new GameView(model);
        GameController controller = new GameController(model, view);
        
        view.updateBoard();
    }
} 