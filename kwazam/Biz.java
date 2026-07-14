public class Biz extends Piece {
    public Biz(String color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public boolean isValidMove(int newX, int newY, Board board) {
        // Biz moves in a 3x2 L shape
        if(getColor().equals("Red")){
            if (((Math.abs(newX - x) == 2 && Math.abs(newY - y) == 1) || 
                (Math.abs(newX - x) == 1 && Math.abs(newY - y) == 2) ||
                (Math.abs(newX + x) == 2 && Math.abs(newY + y) == 1) || 
                (Math.abs(newX + x) == 1 && Math.abs(newY + y) == 2) ||
                (Math.abs(newX + x) == 2 && Math.abs(newY - y) == 1) || 
                (Math.abs(newX + x) == 1 && Math.abs(newY - y) == 2) ||
                (Math.abs(newX - x) == 2 && Math.abs(newY + y) == 1) || 
                (Math.abs(newX - x) == 1 && Math.abs(newY + y) == 2)) && 
                (board.getPiece(newX, newY) == null || board.getPiece(newX, newY).getColor() == "Blue")){
                return true;
            }
        }
        else if(getColor().equals("Blue")){
            if (((Math.abs(newX - x) == 2 && Math.abs(newY - y) == 1) || 
                (Math.abs(newX - x) == 1 && Math.abs(newY - y) == 2) ||
                (Math.abs(newX + x) == 2 && Math.abs(newY + y) == 1) || 
                (Math.abs(newX + x) == 1 && Math.abs(newY + y) == 2) ||
                (Math.abs(newX + x) == 2 && Math.abs(newY - y) == 1) || 
                (Math.abs(newX + x) == 1 && Math.abs(newY - y) == 2) ||
                (Math.abs(newX - x) == 2 && Math.abs(newY + y) == 1) || 
                (Math.abs(newX - x) == 1 && Math.abs(newY + y) == 2)) && 
                (board.getPiece(newX, newY) == null || board.getPiece(newX, newY).getColor() == "Red")){
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