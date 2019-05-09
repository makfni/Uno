package Java;

public class Card {

    private char colour;
    private int rank;
    private Boolean special;
    private Boolean specialX;
    private String actionCard;
    private String wildCard;

    public Card(Boolean special, Boolean specialX, char colour, int rank){

        this.special = special;
        this.specialX = specialX;
        this.colour = colour;
        this.rank = rank;
    }


    public Card(Boolean special, Boolean specialX, char colour, String actionCard){

        this.special = special;
        this.colour = colour;
        this.actionCard = actionCard;
        this.specialX = specialX;
    }

    public Card(Boolean special, Boolean specialX, String wildCard){

        this.special = special;
        this.specialX = specialX;
        this.wildCard = wildCard;
    }

    public char getColour(){ return colour;}

    public int getRank() { return rank;}

    public Boolean isActionCard(){
        return special;
    }

    public Boolean isWildCard(){ return specialX;}

    public String getActionCard(){ return actionCard;}

    public String getWildCard(){ return wildCard;}

    public void setColour(char colour){this.colour = colour;}

    public void showCard(){
        System.out.print("[" + this.colour + this.rank + "] ");
    }

    public void showSpecialCard(){
        System.out.print("[" + this.colour + " " + this.actionCard + "] ");
    }

    public void showSpecialX(){
        System.out.print("[" + this.wildCard + "] ");
    }


}
