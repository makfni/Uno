import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class Deck{

    private ArrayList<Card> deck;
    private static char[] colour = {'B', 'G', 'R', 'Y'};
    private static int[] rank = {0,1,2,3,4,5,6,7,8,9};
    String[] abilities = {"Skip turn", "Reverse", "Draw two"};
    String[] abilityX = {"Change colour", "Draw four"};


    //Create deck
    public Deck(){

        deck = new ArrayList<Card>();
        for(int x = 0; x < 2; x++) {
            for (int c = 0; c < colour.length; c++) {
                for (int r = 0; r < rank.length; r++) {
                    Card card = new Card(false, colour[c], rank[r]);
                    deck.add(card);
                }
            }
        }
        for(int x = 0; x < 2; x++) {
            for (int i = 0; i < colour.length; i++) {
                for (int j = 0; j < abilities.length; j++) {
                    Card card = new Card(true, colour[i], abilities[j]);
                    deck.add(card);
                }
            }
        }
        for(int x = 0; x < 4; x++) {
            for (int i = 0; i < abilityX.length; i++) {
                Card card = new Card(true, abilityX[i]);
                deck.add(card);
            }
        }

        Collections.shuffle(deck);

    }

    public Card draw(){
        Card card;
        if(getDeckNum() > 0){
            card = deck.get(getDeckNum() - 1);
            deck.remove(card);
            return card;
        }
        System.out.println("Deck has run out of card.");
        return null;
    }


    public int getDeckNum(){
        int count = 0;
        for(int i = 0; i < deck.size(); i++){
            count++;
        }
        return count;
    }

    //Deals full hand to each player
    public void dealHands(ArrayList<Card> player){
        for(int i = 0; i < 7; i++) {
            player.add(i, draw());
        }
    }
    public void displayHand(ArrayList<Card> hand){
        System.out.println("Your current hand: ");
        System.out.print("{");
        for(Card c : hand) {
            c.showCard();
            c.showSpecialCard();
            c.showSpecialX();
            System.out.print(" ");
        }
        System.out.println("}");
        System.out.println("");
    }

    public void displayDeck(){
        System.out.print("{");
        for(int i = 0; i < getDeckNum(); i++){
            deck.get(i).showCard();
            deck.get(i).showSpecialCard();
            deck.get(i).showSpecialX();
            System.out.print( " ");
        }

        System.out.print("}");
        System.out.print(" ");
    }
}
