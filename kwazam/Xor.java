public class Xor extends Piece {
    public Xor(String color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public boolean isValidMove(int newX, int newY, Board board) {
        // Xor moves diagonally any distance
        if(getColor().equals("Blue")){
            if (board.getPiece(newX, newY) == null || board.getPiece(newX, newY).getColor() == "Red") {
                if (Math.abs(newX - x) == Math.abs(newY - y)) {
                    for (int i = 1; i < Math.abs(newX - x); i++) {
                        int stepX = x + (newX > x ? i : -i);
                        int stepY = y + (newY > y ? i : -i);
                        if (board.getPiece(stepX, stepY) != null) return false; // Cannot jump over pieces
                    }
                    return true;
                } else if (Math.abs(newX + x) == Math.abs(newY + y)) {
                    for (int i = 1; i < Math.abs(newX - x); i++) {
                        int stepX = x + (newX > x ? i : -i);
                        int stepY = y + (newY > y ? i : -i);
                        if (board.getPiece(stepX, stepY) != null) return false; // Cannot jump over pieces
                    }
                    return true;
                } else if (Math.abs(newX + x) == Math.abs(newY - y)) {
                    for (int i = 1; i < Math.abs(newX - x); i++) {
                        int stepX = x + (newX > x ? i : -i);
                        int stepY = y + (newY > y ? i : -i);
                        if (board.getPiece(stepX, stepY) != null) return false; // Cannot jump over pieces
                    }
                    return true;
                } else if (Math.abs(newX - x) == Math.abs(newY + y)) {
                    for (int i = 1; i < Math.abs(newX - x); i++) {
                        int stepX = x + (newX > x ? i : -i);
                        int stepY = y + (newY > y ? i : -i);
                        if (board.getPiece(stepX, stepY) != null) return false; // Cannot jump over pieces
                    }
                    return true;
                }
            }
        }
        else if(getColor().equals("Red")){
            if (board.getPiece(newX, newY) == null || board.getPiece(newX, newY).getColor() == "Blue") {
                if (Math.abs(newX - x) == Math.abs(newY - y)) {
                    for (int i = 1; i < Math.abs(newX - x); i++) {
                        int stepX = x + (newX > x ? i : -i);
                        int stepY = y + (newY > y ? i : -i);
                        if (board.getPiece(stepX, stepY) != null) return false; // Cannot jump over pieces
                    }
                    return true;
                } else if (Math.abs(newX + x) == Math.abs(newY + y)) {
                    for (int i = 1; i < Math.abs(newX - x); i++) {
                        int stepX = x + (newX > x ? i : -i);
                        int stepY = y + (newY > y ? i : -i);
                        if (board.getPiece(stepX, stepY) != null) return false; // Cannot jump over pieces
                    }
                    return true;
                } else if (Math.abs(newX + x) == Math.abs(newY - y)) {
                    for (int i = 1; i < Math.abs(newX - x); i++) {
                        int stepX = x + (newX > x ? i : -i);
                        int stepY = y + (newY > y ? i : -i);
                        if (board.getPiece(stepX, stepY) != null) return false; // Cannot jump over pieces
                    }
                    return true;
                } else if (Math.abs(newX - x) == Math.abs(newY + y)) {
                    for (int i = 1; i < Math.abs(newX - x); i++) {
                        int stepX = x + (newX > x ? i : -i);
                        int stepY = y + (newY > y ? i : -i);
                        if (board.getPiece(stepX, stepY) != null) return false; // Cannot jump over pieces
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
    }
}