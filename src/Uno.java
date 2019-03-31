import java.util.ArrayList;
import java.util.Scanner;

public class Uno{
//All game logic will reside in this class
    private playerOne p1 = new playerOne();
    private AI bot = new AI();
    private Deck deck = new Deck();
    private ArrayList<Card> field = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void mainMenu(){
        System.out.println("Bienvenido a mi juego de Uno!\n"
                + "Choose one of the options.\n"
                + "1 - New Game\n"
                + "2 - Exit");

        int opt = scanner.nextInt();

        switch(opt){
            case 1:
                startGame();
                break;
            case 2:
                break;
            default:
                System.out.println("Invalid input.");
                mainMenu();
        }
    }

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

    }

    //check for empty deck (if it is then reuse pile)

    //invalid move function for main player?

    //check for winner function

    public ArrayList<Card> getField(){
        return this.field;
    }
}
