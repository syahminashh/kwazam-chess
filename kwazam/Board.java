public class Board {
    private Piece[][] board;
    private Piece[][] tempboard;

    public Board() {
        board = new Piece[8][5];
        tempboard = new Piece[8][5];
        setupInitialPosition();
    }

    private void setupInitialPosition() {
        // Initialize pieces on the board
        board[6][0] = new Ram("Red", 6, 0);
        board[6][1] = new Ram("Red", 6, 1);
        board[6][2] = new Ram("Red", 6, 2);
        board[6][3] = new Ram("Red", 6, 3);
        board[6][4] = new Ram("Red", 6, 4);
        board[7][0] = new Tor("Red", 7, 0);
        board[7][1] = new Biz("Red", 7, 1);
        board[7][2] = new Sau("Red", 7, 2);
        board[7][3] = new Biz("Red", 7, 3);
        board[7][4] = new Xor("Red", 7, 4);
        
        board[1][0] = new Ram("Blue", 1, 0);
        board[1][1] = new Ram("Blue", 1, 1);
        board[1][2] = new Ram("Blue", 1, 2);
        board[1][3] = new Ram("Blue", 1, 3);
        board[1][4] = new Ram("Blue", 1, 4);
        board[0][0] = new Tor("Blue", 0, 0);
        board[0][1] = new Biz("Blue", 0, 1);
        board[0][2] = new Sau("Blue", 0, 2);
        board[0][3] = new Biz("Blue", 0, 3);
        board[0][4] = new Xor("Blue", 0, 4);
    }

    public Piece getPiece(int x, int y) {
        if (x >= 0 && x < 8 && y >= 0 && y < 5) {
            return board[x][y];
        }
        return null;
    }

    public void setPiece(int x, int y, Piece piece) {
        if (x >= 0 && x < 8 && y >= 0 && y < 5) {
            board[x][y] = piece;
        }
    }
    
     public void clearBoard() {
        // Clear the board by setting all positions to null
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = null;
            }
        }
    }
    
    public void switchPieces() {
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                tempboard[i][j]=board[i][j];
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                if(i==0){ 
                    board[i][j]=tempboard[7][j];  
                }else if(i==1){ 
                    board[i][j]=tempboard[6][j];
                }else if(i==2){ 
                    board[i][j]=tempboard[5][j]; 
                }else if(i==3){  
                    board[i][j]=tempboard[4][j];
                }else if(i==4){
                    board[i][j]=tempboard[3][j];
                }else if(i==5){
                    board[i][j]=tempboard[2][j];
                }else if(i==6){
                    board[i][j]=tempboard[1][j];
                }else if(i==7){
                    board[i][j]=tempboard[0][j];
                }
            }
        }
    }
}