public class BlackjackHand extends Hand {
    public int getBlackjackValue(){
        int val; // Value computed for the hand
        boolean ace; // True if hand contains an ace

        int cards; // Number of cards in the hand

        val = 0;
        ace = false;
        cards = getCardCount();

        for (int i = 0; i < cards; i++){
            Card card;
            int cardVal;
            card = getCard(i);
            cardVal = card.getValue();
            if (cardVal > 10 ){ // For a J, Q, K
                cardVal = 10;
            }
            if (cardVal == 1){ // If an ace is in the hand
                ace = true;
            }
            val += cardVal;
        }

        if ( ace == true && val + 10 <= 21) {
            val += 10;
        }

        return  val;
    }
}
