package Java;

import java.util.ArrayList;
import java.util.Arrays;
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

            for (int x = 0; x < 1; x++) {
                for(String abilityX : abilityX){
                    Card card = new Card(false,true,abilityX);
                    deck.add(card);
                }
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
