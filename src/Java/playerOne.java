package Java;

import java.util.ArrayList;

public class playerOne{

    private ArrayList<Card> playerOneHand;

    public playerOne() {
        this.playerOneHand = new ArrayList<>();
    }

    public ArrayList<Card> getPlayerOneHand() {

        return playerOneHand;
    }

    public void setPlayerOneHand(ArrayList<Card> playerOneHand) {

        this.playerOneHand = playerOneHand;
    }

    public int handSize(ArrayList<Card> hand){

        return playerOneHand.size();
    }
}
