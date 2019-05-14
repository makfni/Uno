package Java;

import java.util.ArrayList;

public interface PlayerInterface {

        void playerTurn(ArrayList<Card> table, ArrayList<Card> player);

       // Boolean checkValidity(ArrayList<Card> table, Card card, String action);

        void specialEffect(ArrayList<Card> hand, Card card, String special);


        Boolean checkWinner(int size);
}
