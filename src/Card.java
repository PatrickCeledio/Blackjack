public class Card {
    // Create the codes for the suits and monarchy
    public final static int SPADES = 0, HEARTS = 1, DIAMONDS = 2, CLUBS = 3;
    public final static int ACE = 1, JACK = 11, QUEEN = 12, KING = 13;

    private final int suit; // Values for the 4 suits
    private final int value; // Values for 1 - 11 cards

    public Card(int theValue, int theSuit){
        value = theValue;
        suit = theSuit;
    }

    public int getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    public String getSuitAsString(){
        switch (suit){
            case SPADES: return "Spades";
            case HEARTS: return "Hearts";
            case DIAMONDS: return "Diamonds";
            case CLUBS: return "Clubs";
            default: return "Missingno";
        }
    }

    public String getValueAsString(){
        switch (value){
            case 1: return "Ace (1/11)";
            case 2: return "2";
            case 3: return "3";
            case 4: return "4";
            case 5: return "5";
            case 6: return "6";
            case 7: return "7";
            case 8: return "8";
            case 9: return "9";
            case 10: return "10";
            case 11: return "Joker (10)";
            case 12: return "Queen (10)";
            case 13: return "King (10)";
            default: return "Pikachu";
        }
    }

    public String toString(){
        return getValueAsString() + " of " + getSuitAsString();
    }

}// end Card Class
