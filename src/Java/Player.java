package Java;

import java.util.ArrayList;

public class Player {

    private ArrayList<Card> playerHand = new ArrayList<>();

    public Player(ArrayList<Card> playerHand){
        this.playerHand = playerHand;
    }

    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }
}
