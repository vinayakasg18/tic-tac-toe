import java.util.ArrayList;
import java.util.Random;

public class AI {
    public int pickSpot(TicTacToe game){
        ArrayList<Integer> choices = new ArrayList<>();

        for (int i = 0; i < 9; i++){
            // add the slot to choices list if the available
            if (game.board[i] == '-'){
                choices.add(i + 1); // +1 for abstraction
            }
        }
        Random rand = new Random();
        return choices.get(Math.abs(rand.nextInt() % choices.size()));
    }
}
