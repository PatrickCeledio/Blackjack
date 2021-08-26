public class BlackjackHand extends Hand {
    // Check if hand has Blackjack
    public int getBlackjackValue(){
        int val; // Value computed for the hand
        boolean ace; // True if hand contains an ace

        int cards; // This will hold cardinality of cards in hand

        val = 0;
        ace = false;
        cards = getCardCount(); // Assign cards cardinality of cards in hand


        for (int i = 0; i < cards; i++){
            Card card;
            int cardVal;
            card = getCard(i);

            // Get number and suit of card
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

        return val;
    }
}
