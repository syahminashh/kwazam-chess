
public abstract class Piece {
    protected String color;
    protected int x, y;

    public Piece(String color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public abstract boolean isValidMove(int newX, int newY, Board board);
    public abstract void move(int newX, int newY);
    
    public String getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}