public class Tor extends Piece {
    private int turns;

    public Tor(String color, int x, int y) {
        super(color, x, y);
        this.turns = 0;
    }

    @Override
    public boolean isValidMove(int newX, int newY, Board board) {
        // Tor moves orthogonally any distance
        if(getColor().equals("Red")){
            if (board.getPiece(newX, newY) == null || board.getPiece(newX, newY).getColor() == "Blue") {
                if (newX == x) {
                    for (int i = Math.min(y, newY) + 1; i < Math.max(y, newY); i++) {
                        if (board.getPiece(x, i) != null) return false;
                    }
                    return true;
                } else if (newY == y) {
                    for (int i = Math.min(x, newX) + 1; i < Math.max(x, newX); i++) {
                        if (board.getPiece(i, y) != null) return false;
                    }
                    return true;
                } else if (newX == x) {
                    for (int i = Math.min(y, newY) - 1; i < Math.max(y, newY); i++) {
                        if (board.getPiece(x, i) != null) return false;
                    }
                    return true;
                } else if (newY == y) {
                    for (int i = Math.min(x, newX) - 1; i < Math.max(x, newX); i++) {
                        if (board.getPiece(i, y) != null) return false;
                    }
                    return true;
                }
            }
        }
        else if(getColor().equals("Blue")){
            if (board.getPiece(newX, newY) == null || board.getPiece(newX, newY).getColor() == "Red") {
                if (newX == x) {
                    for (int i = Math.min(y, newY) + 1; i < Math.max(y, newY); i++) {
                        if (board.getPiece(x, i) != null) return false;
                    }
                    return true;
                } else if (newY == y) {
                    for (int i = Math.min(x, newX) + 1; i < Math.max(x, newX); i++) {
                        if (board.getPiece(i, y) != null) return false;
                    }
                    return true;
                } else if (newX == x) {
                    for (int i = Math.min(y, newY) - 1; i < Math.max(y, newY); i++) {
                        if (board.getPiece(x, i) != null) return false;
                    }
                    return true;
                } else if (newY == y) {
                    for (int i = Math.min(x, newX) - 1; i < Math.max(x, newX); i++) {
                        if (board.getPiece(i, y) != null) return false;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void move(int newX, int newY) {
        this.x = newX;
        this.y = newY;
        turns++;
        if (turns >= 2) {
            // Transform to Xor after 2 turns
            // Logic to transform piece
        }
    }
}