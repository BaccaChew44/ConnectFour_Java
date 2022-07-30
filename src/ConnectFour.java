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
        String temp = new String(grid[0]);
        return !temp.contains("x");
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

    private String getRightDiagonal(int row, int col){
        int botRow = row;
        int leftCol = col;
        int topRow = row;
        int rightCol = col;
        String output = new String();
        //find the left boundary
        while(botRow < height-1 && leftCol != 0){
            botRow += 1;
            leftCol -= 1;
        }

        //find the right boundary
        while(topRow > 0 && rightCol < width-1){
            topRow -= 1;
            rightCol += 1;
        }

        //get the entires in our boundary
        for(int i = leftCol; i<=rightCol; i++){
            output = output + grid[botRow][i];
            botRow--;
            if(botRow < topRow){
                return output;
            }
        }
        return output;
    }

    private String getLeftDiagonal(int row, int col){
        int botRow = row;
        int leftCol = col;
        int topRow = row;
        int rightCol = col;
        String output = new String();
        //find the left boundary
        while(topRow !=0 && leftCol != 0){
            topRow -= 1;
            leftCol -= 1;
        }

        //find the right boundary
        while(botRow < height-1 && rightCol < width-1){
            botRow += 1;
            rightCol += 1;
        }

        //get the entries in our boundary
        for(int i = leftCol; i<=rightCol; i++){
            output = output + grid[topRow][i];
            topRow++;
            if(topRow > botRow){
                return output;
            }
        }
        return output;
    }

    public boolean hasWon(){
        String winningString = String.format("%c%c%c%c", activePlayer, activePlayer, activePlayer, activePlayer);

        System.out.println(getLeftDiagonal(lastRow, lastCol));
        return getHorizontal(lastRow).contains(winningString) ||
                getVertical(lastCol).contains(winningString) ||
                getRightDiagonal(lastRow, lastCol).contains(winningString) ||
                getLeftDiagonal(lastRow, lastCol).contains(winningString);
    }
}
