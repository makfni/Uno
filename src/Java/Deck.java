package Java;

import java.util.ArrayList;
import java.util.Collections;

public class Deck{

    private ArrayList<Card> deck;
    private char[] colour = {'B', 'G', 'R', 'Y'};
    private int[] rank = {0,1,2,3,4,5,6,7,8,9};
    private String[] abilities = {"Skip turn", "Reverse", "Draw two"};
    private String[] abilityX = {"Change colour", "Draw four"};



    //Create deck
    public Deck(){

        deck = new ArrayList<>();
        for(int x = 0; x < 2; x++) {
            for (int c = 0; c < colour.length; c++) {
                for (int r = 0; r < rank.length; r++) {
                    for (int j = 0; j < abilities.length; j++) {
                        Card card = new Card(true, false, colour[c], abilities[j]);
                        deck.add(card);
                        Card cardX = new Card(false, false, colour[c], rank[r]);
                        deck.add(cardX);
                    }
                }
            }
        }

        for(int x = 0; x < 4; x++) {
            for (int i = 0; i < abilityX.length; i++) {
                Card card = new Card(false,true, abilityX[i]);
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

        if(type == "hand") {
            System.out.println("Your current hand: ");
        }else if(type == "AI"){
            System.out.println("AI Hand: ");
        }else if(type == "table"){
            System.out.println("Table: ");
        }
        System.out.print("{");
        System.out.print(" ");
        for (int i = 0; i < display.size(); i++) {
            if (!display.get(i).isSpecial() && !display.get(i).isSpecialX()) {
                display.get(i).showCard();
            } else if (display.get(i).isSpecial() && !display.get(i).isSpecialX()) {
                display.get(i).showSpecialCard();
            } else if (display.get(i).isSpecialX() && !display.get(i).isSpecial()) {
                display.get(i).showSpecialX();
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


    public char[] getColour() {
        return colour;
    }

    public int[] getRank() {
        return rank;
    }

}
