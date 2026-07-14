import javax.swing.JOptionPane;

public class GameModel {
    private Board board;
    private String currentPlayer;

    public GameModel() {
        board = new Board();
        currentPlayer = "Red"; // Starting player
    }

    public Board getBoard() {
        return board;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }
    
    public void setCurrentPlayer(String player) {
        this.currentPlayer = player; // Method to set the current player
    }

    public void switchPlayer() {
        currentPlayer = currentPlayer.equals("Red") ? "Blue" : "Red";
        board.switchPieces();
    }

    public boolean movePiece(int fromX, int fromY, int toX, int toY) {
        Piece piece = board.getPiece(fromX, fromY);
        Piece targetPiece = board.getPiece(toX, toY);

        // Check if the piece belongs to the current player
        if (piece != null && piece.getColor().equals(currentPlayer)) {
            // Check if the move is valid
            if (piece.isValidMove(toX, toY, board)) {
                // If the target piece is an opponent's piece, capture it
                if (targetPiece != null) {
                    // Check if the captured piece is a Sau
                    if (targetPiece instanceof Sau) {
                        // Declare the current player as the winner
                        JOptionPane.showMessageDialog(null, currentPlayer + " wins by capturing the Sau!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                        System.exit(0); // Exit the game
                    } else {
                        // Capture the opponent's piece
                        board.setPiece(toX, toY, piece); // Move the piece to the target location
                        board.setPiece(fromX, fromY, null); // Remove the piece from the original location
                        piece.move(toX, toY); // Update the piece's position
                        switchPlayer(); // Switch player after a valid move
                        return true;
                    }
                } else {
                    // Move to an empty square
                    board.setPiece(toX, toY, piece); // Move the piece to the target location
                    board.setPiece(fromX, fromY, null); // Remove the piece from the original location
                    piece.move(toX, toY); // Update the piece's position
                    switchPlayer(); // Switch player after a valid move
                    return true;
                }
            }
        }
        return false;
    }
}