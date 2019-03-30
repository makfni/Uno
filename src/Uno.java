import java.util.ArrayList;

public class Uno{
//All game logic will reside in this class
    private playerOne p1;
    private AI bot;
    private Deck deck;
    private ArrayList<Card> field;
    //startGame function to initialize game
    public void startGame(){

        //hands are created
        p1.initHand(deck);
        bot.initAIHand(deck);

        //If hands are populated, nobody has won
        while((p1.handSize(p1.getPlayerOneHand()) > 0) && (bot.handSize(bot.getAIHand()) > 0)){

        }
    }

    //make each player play their turn
    //player goes first and plays game on console
    //AI will play by having their hand looped through to check for playable cards
    //if they receive an ability card, they will play it right away
    //check if deck is empty (if it's not, then next player goes)
    //if no play can be made, draw card from deck if deck is not empty
    //else, call reuse pile function

    public void playerOneTurn(){
        System.out.println();
    }

    //check for empty deck (if it is then reuse pile)

    //invalid move function for main player?

    //check for winner function

    public ArrayList<Card> getField(){
        return this.field;
    }
}
