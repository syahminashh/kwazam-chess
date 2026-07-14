import javax.swing.JOptionPane;

public class GameController {
    private GameModel model;
    private GameView view;

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        view.updateBoard();
    }

    public void handleMove(int fromX, int fromY, int toX, int toY) {
        if (model.movePiece(fromX, fromY, toX, toY)) {
            view.updateBoard();
        } else {
            // Handle invalid move (e.g., show a message)
            JOptionPane.showMessageDialog(view.getFrame(), "Invalid move!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
} 