public class Card {

    private char colour;
    private int rank;
    private Boolean special;
    private String ability;
    private String abilityX;

    public Card(Boolean special, char colour, int rank){
        this.special = special;
        this.colour = colour;
        this.rank = rank;
        this.special = false;

    }


    public Card(Boolean special, char colour, String ability){
        this.special = special;
        this.colour = colour;
        this.ability = ability;

    }

    public Card(Boolean special, String abilityX){
        this.special = special;
        this.special = special;
        this.abilityX = abilityX;
    }

    public void setColour(char colour) {
        this.colour = colour;
    }

    public char getColour(){
        return colour;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public int getRank(){
        return rank;
    }

    public String getAbility(){
        return ability;
    }

    public void printNorm(){
        System.out.println(colour + rank);
    }

    public void printSpec(){
        System.out.println(colour + ability);
    }

    public void printSpecX(){
        System.out.println(ability);
    }

    public void showCard(){
        System.out.print(this.rank);
    }

    public void showSpecialCard(){
        System.out.print(this.colour + " " + this.ability + " | ");
    }

    public void showSpecialX(){
        System.out.print(this.abilityX + " | ");
    }


}
