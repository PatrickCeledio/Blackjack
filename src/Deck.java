public class Deck {

    private Card[] deck;
    private int cardsUsed;

    public Deck(){ // Create a new unshuffled deck of cards
        deck = new Card[52];
        int cardCount = 0;
        for (int suit = 0; suit <= 3; suit++ ){
            for (int value = 1; value <=13; value++){
                deck[cardCount] = new Card(value, suit);
                cardCount++;
            }
        }
        cardsUsed = 0;
    }

    public void shuffle() {
        for (int i = 51; i > 0; i--){
            int rand = (int)(Math.random()*(i+1));
            Card temp = deck[i];
            deck[i] = deck[rand];
            deck[rand] = temp;
        }
        cardsUsed = 0;
    }

    public int cardsLeft() {
        return 52 - cardsLeft();
    }

    public Card dealCard() {
        if (cardsUsed == 52)
            shuffle();
        cardsUsed++;
        return deck[cardsUsed - 1];
    }
} // end Deck
