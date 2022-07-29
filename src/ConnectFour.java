import java.util.Arrays;

public class ConnectFour {

    //define the two players
    private static final char[] Players = {'R', 'Y'};
    private static char activePlayer = Players[1];

    //define the dimensions of the board
    private final int width, height;

    //define the grid
    private final char[][] grid;

    private int lastCol = -1;
    private int lastTop = -1;

    public ConnectFour(int w, int h){
        width = w;
        height = h;
        grid = new char[h][w];

        //init the grid with "blanks"
        for(int i=0; i<h; i++){
            Arrays.fill(grid[i] = new char[w], 'x');
        }
    }

    public void printGrid() {
        for (int i = 0; i < grid.length; i++) {
            if (i == 0) {
                System.out.print("   ");
                for (int j = 0; j < grid[0].length; j++) {
                    System.out.printf("%2d ", j + 1);
                }
                System.out.println();
            }
            for (int j = 0; j < grid[i].length; j++) {
                if (j == 0) {
                    System.out.printf("%2d:", i + 1);
                }
                System.out.printf("%2s ", grid[i][j]);
            }
            System.out.println();
        }
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public boolean isFull(){
        for(int i=0; i<=width; i++){
            if(grid[i][height] == 'x') {
                return false;
            }
        }
        return true;
    }

    public char getCurrentPlayer(){
        if(activePlayer == Players[0]){
            return Players[1];
        }
        else{
            return Players[0];
        }
    }

    public void nextTurn(){
        if(activePlayer == Players[0]){
            activePlayer = Players[1];
        }
        else{
            activePlayer = Players[0];
        }
    }

    public void placePiece(int column){
        for(int i=height-1;i>=0;i--){
            if(grid[i][column-1] == 'x') {
                grid[i][column-1] = activePlayer;
                return;
            }
        }
    }
}
