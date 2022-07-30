import java.util.Arrays;
import java.util.Scanner;

public class ConnectFour {

    //define the two players
    private static final char[] Players = {'R', 'Y'};
    private static char activePlayer = Players[0];

    //define the dimensions of the board
    private final int width, height;

    //define the grid
    private final char[][] grid;

    //save the last location
    private int lastCol = -1;
    private int lastRow = -1;

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
        return activePlayer;
    }

    public void nextTurn(){
        if(activePlayer == Players[0]){
            activePlayer = Players[1];
        }
        else{
            activePlayer = Players[0];
        }
    }

    public void chooseAndPlace(Scanner input){
        do {
            int column = input.nextInt();
            if(column>width){
                System.out.printf("Column must be between 1 and %d. Please enter a new column:",width);
                System.out.println("");
                continue;
            }
            for (int i = height - 1; i >= 0; i--) {
                if (grid[i][column - 1] == 'x') {
                    grid[i][column - 1] = activePlayer;
                    lastCol = column - 1;
                    lastRow = i;
                    return;
                }
            }
            System.out.println("That column is already filled, please select a new column:");
        }while(true);
    }

    private String getHorizontal(int row){
        return new String(grid[row]);
    }

    private String getVertical(int column){
        String output = new String();
        for(int i =0; i<height;i++){
            output = output + grid[i][column];
        }
        return output;
    }

    public boolean hasWon(){
        String winningString = String.format("%c%c%c%c", activePlayer, activePlayer, activePlayer, activePlayer);
        System.out.println(getVertical(lastCol));
        System.out.println(getHorizontal(lastRow));
        return getHorizontal(lastRow).contains(winningString) ||
                getVertical(lastCol).contains(winningString);
    }
}
