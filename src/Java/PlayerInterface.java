package Java;

import java.util.ArrayList;

public interface PlayerInterface {

        void playerTurn(ArrayList<Card> table, ArrayList<ArrayList<Card>> playerList );
        Boolean checkValidity(ArrayList<Card> table, Card card, String play);
        void specialEffect(ArrayList<Card> hand, Card card, String special);
}
