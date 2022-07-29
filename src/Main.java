import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //create the game board and output some instructions
        ConnectFour myGame = new ConnectFour(7, 6);
        System.out.println("Welcome to a practice Connect Four Game by Dylan Moore!");
        System.out.println("To play, simply enter the column number you would like your piece to drop into");
        myGame.printGrid();
        Scanner scanner = new Scanner(System.in);

        while(!myGame.isFull()){
            System.out.printf("%2s Player is up! Please Enter a Column:", myGame.getCurrentPlayer());
            int column = scanner.nextInt();
            myGame.placePiece(column);
            myGame.printGrid();
        }

    }
}
