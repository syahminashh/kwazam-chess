public class Ram extends Piece {
    public Ram(String color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public boolean isValidMove(int newX, int newY, Board board) {
        // Ram can only move forward one step
        if(getColor().equals("Red")){
            if (newX == x - 1 && newY == y && (board.getPiece(newX, newY) == null || board.getPiece(newX, newY).getColor() == "Blue")) {
                return true;
            }
        }
        else if(getColor().equals("Blue")){
            if (newX == x - 1 && newY == y && (board.getPiece(newX, newY) == null || board.getPiece(newX, newY).getColor() == "Red")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void move(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }
}