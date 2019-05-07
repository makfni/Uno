package Java;

import java.util.ArrayList;

public interface PlayerInterface {

        void playerTurn(ArrayList<Card> table,  ArrayList<Player> playerList );
        Boolean checkValidity(ArrayList<Card> table, Card card, String play);
}
