package Java;

import java.util.ArrayList;
import java.util.Collections;

public class Deck{
    //"Skip turn", "Reverse"
    private ArrayList<Card> deck;
    private char[] colour = {'B', 'Y', 'R', 'G'};
    //,6,7,8,9
    private int[] rank = {1,2,3,4,5,5,5};
    private String[] abilities = {"Draw two","Draw two","Draw two","Draw two" };
    private String[] abilityX = {"Change colour", "Draw four"};

    //Create deck
    public Deck(){

        deck = new ArrayList<>();

        for(int i = 0; i < 2; i++) {
            for (char c : colour)
                for(int r : rank){
                    Card card = new Card(false,false,c,r);
                    deck.add(card);
                }

            for (char c : colour)
                for(String ability : abilities){
                    Card card  = new Card(true,false,c, ability);
                    deck.add(card);
                }

            for (int x = 0; x < 2; x++) {
                for(String abilityX : abilityX){
                    Card card = new Card(false,true,abilityX);
                    deck.add(card);
                }
            }
        }

        for(char c : colour){
            Card card = new Card(false, false, c, 0);
            deck.add(card);
        }

        shuffleDeck(deck);
    }

    public void shuffleDeck(ArrayList<Card> deck){
        Collections.shuffle(deck);
    }

    public Card draw(){

        AI bot = new AI();
        playerOne p1 = new playerOne();

        Card card;
        if(getDeckNum() > 0){
            card = deck.get(getDeckNum() - 1);
            deck.remove(card);
            return card;
        } else {
            if(bot.getAIHand().size() > p1.getHandSize()){
                System.out.println("AI won the game :(");
                System.exit(0);
            }else{
                System.out.println("You won the game!");
                System.exit(0);
            }
        }
        System.out.println("Java.Deck has run out of card.");
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

    public void display(ArrayList<Card> display, String type){

        switch (type) {
            case "hand":
                System.out.println("Your current hand: ");
                break;
            case "AI":
                System.out.println("AI Hand: ");
                break;
            case "table":
                System.out.println("Table: ");
                break;
        }
        System.out.print("{");
        System.out.print(" ");
        for (Card card : display) {
            if (!card.isActionCard() && !card.isWildCard()) {
                card.showCard();
            } else if (card.isActionCard() && !card.isWildCard()) {
                card.showSpecialCard();
            } else if (card.isWildCard() && !card.isActionCard()) {
                card.showSpecialX();
            }
            System.out.print(" ");
        }
        System.out.println("}");
        System.out.println(" ");
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


    public String[] getAbilities() {
        return abilities;
    }

    public String[] getAbilityX() {
        return abilityX;
    }

    public ArrayList<Card> getDeck() { return deck;}

    public char[] getColour() {
        return colour;
    }

    public int[] getRank() {
        return rank;
    }
}