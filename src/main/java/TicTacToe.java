import java.util.Arrays;

public class TicTacToe {

    // Variables needed for a game
    // board - consists of player initials on board
    // userMarker - marker for user turn
    // aiMarker - marker for ai turn
    // winner - who is the winner?
    // currentMarker - who is playing currently?

    // How does game look like?. (In backend)
    // 0 | 1 | 2
    // ---------
    // 3 | 4 | 5
    // ---------
    // 6 | 7 | 8
    // How the user sees the board as?.
    // 1 | 2 | 3
    // ---------
    // 4 | 5 | 6
    // ---------
    // 7 | 8 | 9
    // How the UI looks like?.
    // | - | - | - |
    // -------------
    // | - | - | - |
    // -------------
    // | - | - | - |

    // Gameplay?.
    // | X | X | O |
    // -------------
    // | X | O | O |
    // -------------
    // | - | - | O |

    protected char[] board;
    protected char userMarker;
    protected char aiMarker;
    protected char winner;
    protected char currentMarker;

    // Create a constructor (Instance of a class.)
    public TicTacToe(char playerToken, char aiMarker){
        this.userMarker = playerToken;
        this.aiMarker = aiMarker;
        this.winner = '-';
        this.currentMarker = userMarker;
        this.board = setBoard();
    }

    public char[] setBoard(){
        char[] board = new char[9];
        // Assign '-' to the initial board
        Arrays.fill(board, '-');
        return board;
    }

    public boolean playTurn(int spot){
        boolean isValid = withinRange(spot) && !isSpotTaken(spot);

        if (isValid){
            board[spot - 1] = currentMarker;
            currentMarker = (currentMarker == userMarker) ? aiMarker : userMarker;
        }
        return isValid;
    }

    // Check if the spot is within range
    public boolean withinRange(int number){
        return number > 0 && number < board.length + 1;
    }

    // Check if the spot selected is taken or available
    public boolean isSpotTaken(int number){
        return board[number - 1] != '-';
    }

    // Print the board
    public void printBoard(){
        // | - | - | - |
        // -------------
        // | - | - | - |
        // -------------
        // | - | - | - |
        System.out.println();
        for (int i = 0; i < board.length; i++){
            if (i % 3 == 0 && i != 0){
                System.out.println();
                System.out.println("-------------");
            }
            System.out.print(" | " + board[i]);
        }
        System.out.println();
    }

    // Print the board with the index numbers
    public static void printIndexBoard(){
        System.out.println();
        for (int i = 0; i < 9; i++){
            if (i % 3 == 0 && i != 0){
                System.out.println();
                System.out.println("-------------");
            }
            System.out.print(" | " + (i + 1)); // +1 is added for the abstraction
        }
        System.out.println();
    }

    public boolean isThereAWinner(){
        boolean diagonalsAndMiddles = (rightDi() || leftDi() || middleRow() || secondCol()) && board[4] != '-';
        boolean topAndFirst = (topRow() || firstCol()) && board[0] != '-';
        boolean bottomAndThirdCol = (bottomRow() || thirdCol()) && board[8] != '-';

        if (diagonalsAndMiddles){
            this.winner = board[4];
        }
        else if (topAndFirst){
            this.winner = board[0];
        }
        else if (bottomAndThirdCol){
            this.winner = board[8];
        }
        // Just return the condition (either false or true)
        return diagonalsAndMiddles || topAndFirst || bottomAndThirdCol;
    }

    public boolean topRow() {
        return board[0] == board[1] && board[1] == board[2];
    }

    public boolean bottomRow(){
        return board[6] == board[7] && board[7] == board[8];
    }

    public boolean middleRow(){
        return board[3] == board[4] && board[4] == board[5];
    }

    public boolean firstCol(){
        return board[0] == board[3] && board[3] == board[6];
    }

    public boolean secondCol(){
        return board[1] == board[4] && board[4] == board[7];
    }

    public boolean thirdCol(){
        return board[2] == board[5] && board[5] == board[8];
    }

    public boolean rightDi(){
        return board[0] == board[4] && board[4] == board[8];
    }

    public boolean leftDi(){
        return board[2] == board[4] && board[4] == board[6];
    }

    public boolean isTheBoardFilled(){
        for (char c : board) {
            if (c == '-') {
                return false;
            }
        }
        return true;
    }

    public String isGameOver(){
        boolean didSomeoneWin = isThereAWinner();
        if (didSomeoneWin){
            return "We have a winner!. The winner is " + this.winner + "'s";
        }
        else if(isTheBoardFilled()){
            return "Draw: Game Over!";
        }
        else{
            return "notOver";
        }
    }
}
