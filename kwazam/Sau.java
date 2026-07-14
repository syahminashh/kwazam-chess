public class Sau extends Piece {
    public Sau(String color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public boolean isValidMove(int newX, int newY, Board board) {
        // Sau can move one step in any direction
        if(getColor().equals("Red")){
            if (board.getPiece(newX, newY) == null || board.getPiece(newX, newY).getColor() == "Blue") {
                if((Math.abs(newX - x) <= 1 && Math.abs(newY - y) <= 1)||
                (Math.abs(newX - x) <= 1 && Math.abs(newY - y) <= 1)||
                (Math.abs(newX - x) <= 1 && Math.abs(newY - y) <= 1)||
                (Math.abs(newX - x) <= 1 && Math.abs(newY - y) <= 1)){
                    return true;
                }
            }
        }else if(getColor().equals("Blue")){
            if (board.getPiece(newX, newY) == null || board.getPiece(newX, newY).getColor() == "Red") {
                if((Math.abs(newX - x) <= 1 && Math.abs(newY - y) <= 1)||
                (Math.abs(newX - x) <= 1 && Math.abs(newY - y) <= 1)||
                (Math.abs(newX - x) <= 1 && Math.abs(newY - y) <= 1)||
                (Math.abs(newX - x) <= 1 && Math.abs(newY - y) <= 1)){
                    return true;
                }
            }
        }
        return (false);
    }

    @Override
    public void move(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }
}