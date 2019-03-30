import java.util.ArrayList;

public class playerOne extends Uno implements Player{

    private ArrayList<Card> playerOneHand;

    public playerOne() {
        this.playerOneHand = new ArrayList<>();
    }

    //Calls dealHand() from Deck and populates player hand
    public ArrayList<Card> initHand(Deck deck){
        playerOneHand.addAll(deck.dealHand());
        return playerOneHand;
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

    public void displayHand(){
        System.out.println("Your current hand: ");
        for(Card c : playerOneHand){
            c.printNorm();
        }
    }

}
