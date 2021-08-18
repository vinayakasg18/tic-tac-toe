import java.util.Scanner;

public class TicTacToeApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Allows for continue games

        boolean doYouWannaPlay = true;
        while (doYouWannaPlay){
            System.out.println("Welcome to TicTacToe, Pic your character and also name your opponent");
            System.out.println();
            System.out.println("Enter a single char which will represent you on the board");
            char playerToken = sc.next().charAt(0);
            System.out.println("Enter your opponents char");
            char opponentToken = sc.next().charAt(0);

            // Create a instance of the game
            TicTacToe game = new TicTacToe(playerToken, opponentToken);

            // Create a AI character instance
            AI ai = new AI();

            // Setup the game
            System.out.println();
            System.out.println("We are gonna start the game now. To play, enter a number and your number shall put in it's place. \n The number goes from 1-9, left to right. We shall see who will win the round");
            TicTacToe.printIndexBoard();
            System.out.println();
            game.printBoard();

            // Let's play
            while (game.isGameOver().equals("notOver")){
                if (game.currentMarker == game.userMarker){
                    //User turn
                    System.out.println("It's your turn. Enter your spot");
                    int spot = sc.nextInt();
                    while (!game.playTurn(spot)){
                        System.out.println(" Try again. " + spot + "is invalid. This is already taken or out of range");
                        spot = sc.nextInt();
                    }
                    System.out.println("You picked "+ spot + "!");
                }
                else {
                    // Ai turn
                    System.out.println("It's my turn");
                    //pick a spot
                    int aiSpot = ai.pickSpot(game);
                    game.playTurn(aiSpot);
                    System.out.println("I picked "+ aiSpot + "!");
                }
                // Print new board
                System.out.println();
                game.printBoard();
            }
            System.out.println(game.isGameOver());
            System.out.println();

            // setup a new game
            System.out.println("Do you want to play again? Enter Y if you do, otherwise enter anything else");
            char response = sc.next().charAt(0);
            doYouWannaPlay = (response == 'Y');
            System.out.println();
            System.out.println();
        }
    }
}
