package Java;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Launcher{
    public static void main(String args[]){

        Uno uno = new Uno();
        playerOne p1 = new playerOne();
        AI bot = new AI();
        Deck deck = new Deck();
        ArrayList<Card> field = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);


        System.out.println("Bienvenido a mi juego de Uno!\n"
                + "Choose one of the options.\n"
                + "1 - New Game\n"
                + "2 - Exit");

        int opt = scanner.nextInt();
        switch (opt) {
            case 1:
                uno.startGame();
                break;
            case 2:
                System.out.println("Hasta la proxima!");
                break;
            default:
                break;
        }
    }
}
