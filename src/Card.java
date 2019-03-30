public class Card {

    char colour;
    int rank;
    String ability;
    Boolean special;

    public Card(char colour, int rank){
      //  this.special = special;
        this.colour = colour;
        this.rank = rank;

    }

    public Card(Boolean Special, char colour, String ability){
        this.special = special;
        this.colour = colour;
        this.ability = ability;
        special = true;
    }

    public Card(Boolean Special, String ability){
        this.special = special;
        this.special = special;
        this.ability = ability;
        special = true;
    }

    public void setColour(char colour) {
        this.colour = colour;
    }

    public char getColour(){
        return this.colour;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public int getRank(){
        return this.rank;
    }

    public String getAbility(){
        return this.ability;
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
}
