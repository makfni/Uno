import java.util.ArrayList;
import java.util.Collections;

public class Deck{

    private ArrayList<Card> deck;
    private char[] colour = new char[]{'B', 'G', 'R', 'Y'};
    private int[] rank = new int[]{0,1,2,3,4,5,6,7,8,9};
    private String[] ability = new String[]{"Skip turn", "Reverse", "Draw two"};
    private String[] abilityX = new String[]{"Change colour", "Draw four"};
    Uno uno;

    //Create deck
    public ArrayList<Card> initDeck(){

        this.deck = new ArrayList<Card>();
        for(int i = 0; i < colour.length; i++) {
            for (int j = 0; j < rank.length; j++) {
                deck.add(new Card(colour[i], rank[i]));
                deck.add(new Card(colour[i], rank[i]));
                for(int k = 0; k < 4; k++){
                    if (deck.get(i).rank == 0){
                        deck.remove(i);
                    }
                }
            }
        }
        for(int i = 0; i < colour.length; i++){
            for(int j = 0; j < ability.length; j++) {
                deck.add(new Card(true, colour[i], ability[j]));
                deck.add(new Card(true, colour[i], ability[j]));
                deck.add(new Card(true, colour[i], ability[j]));
                deck.add(new Card(true, colour[i], ability[j]));
            }
        }
        for(int i = 0; i < abilityX.length; i++){
            deck.add(new Card(true, abilityX[i]));
            deck.add(new Card(true, abilityX[i]));
            deck.add(new Card(true, abilityX[i]));
            deck.add(new Card(true, abilityX[i]));
        }

        Collections.shuffle(deck);
        return deck;
    }

    //Returns size of deck
    public int deckSize(){
        int size = 0;
        for(int i = 0; i < deck.size(); i++){
            size++;
        }

        return size;
    }

    //Draws card from deck -> remove from deck -> add to pile
    public Card drawCard(){
        Card card;
        if(deckSize() > 0){
            card = deck.get(deckSize() - 1);
            uno.getField().add(card);
            deck.remove(card);
            return card;
        }

        System.out.println("Deck ran out of cards. Recreating deck from pile.");
        return null;
    }

    //Deals full hand to each player
    public ArrayList<Card> dealHand(){
        ArrayList<Card> hand = new ArrayList<>();
        for(int i = 0; i < 7; i++){
            hand.add(deck.get(i));
        }
        return hand;
    }
}
