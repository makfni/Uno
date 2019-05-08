package Java;

public class Card {

    private char colour;
    private int rank;
    private Boolean special;
    private Boolean specialX;
    private String ability;
    private String abilityX;

    public Card(Boolean special, Boolean specialX, char colour, int rank){
        this.special = special;
        this.specialX = specialX;
        this.colour = colour;
        this.rank = rank;
    }


    public Card(Boolean special, Boolean specialX, char colour, String ability){
        this.special = special;
        this.colour = colour;
        this.ability = ability;
        this.specialX = specialX;
    }

    public Card(Boolean special, Boolean specialX, String abilityX){
        this.special = special;
        this.specialX = specialX;
        this.abilityX = abilityX;
    }

    public char getColour(){ return colour;}

    public int getRank() { return rank;}

    public Boolean isSpecial(){
        return special;
    }

    public Boolean isSpecialX(){ return specialX;}

    public String getAbility(){ return ability;}

    public String getAbilityX(){ return abilityX;}

    public void setColour(char colour){this.colour = colour;}

    public void showCard(){
        System.out.print("[" + this.colour + this.rank + "] ");
    }

    public void showSpecialCard(){
        System.out.print("[" + this.colour + " " + this.ability + "] ");
    }

    public void showSpecialX(){
        System.out.print("[" + this.abilityX + "] ");
    }


}
