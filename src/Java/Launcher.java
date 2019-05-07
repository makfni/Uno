package Java;

import java.lang.reflect.Method;
import java.util.Scanner;

public class Launcher extends Uno{

    public static void main(String[] args) throws Exception{

        Uno uno = new Uno();
        Method u = Uno.class.getDeclaredMethod("startGame");
        u.setAccessible(true);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido a mi juego de Uno!\n"
                + "Choose one of the options.\n"
                + "1 - New Game\n"
                + "2 - Exit");

        int opt = scanner.nextInt();
        switch (opt) {
            case 1:
                u.invoke(uno);
                break;
            case 2:
                System.out.println("Hasta la proxima!");
                break;
            default:
                break;
        }
    }
}
