import java.util.Scanner;
public class Blackjack {

    static boolean playBlackjack(){
        Deck deck; // Deck of cards
        BlackjackHand dealer; // Dealer's hand
        BlackjackHand user; // User's hand

        deck = new Deck();
        dealer = new BlackjackHand();
        user = new BlackjackHand();

        // Shuffle deck, and then deal two cards to dealer and user
        deck.shuffle();
        dealer.addCard( deck.dealCard() );
        dealer.addCard( deck.dealCard() );
        user.addCard( deck.dealCard() );
        user.addCard( deck.dealCard() );

        System.out.println();
        System.out.println();

        // Check for Blackjack
        if (dealer.getBlackjackValue() == 21) {
            System.out.println("Computer has the " + dealer.getCard(0) +
                    " and the " + dealer.getCard(1));
            System.out.println("User has the " + user.getCard(0) +
                    " and the " + dealer.getCard(1) + "\n");
            System.out.println("Computer has Blackjack-- Dealer wins.");
            return false;
        }

        if (user.getBlackjackValue() == 21) {
            System.out.println("Computer has the " + dealer.getCard(0) +
                    " and the " + dealer.getCard(1));
            System.out.println("User has the " + user.getCard(0) +
                    " and the " + dealer.getCard(1) + "\n");
            System.out.println("User has Blackjack-- User wins.");
            return true;
        }

        // Play Blackjack is neither user or CPU has Blackjack
        // User gets a chance to draw cards first until they stand
        // If user goes over 21, they lose
        while(true){
            // Display user's cards, allow them to hit or stand
            System.out.println("\n\n****************************");
            System.out.println("\nUser's cards are: ");
            for (int i = 0; i < user.getCardCount(); i++){
                System.out.println(" " + user.getCard(i));
            }
            System.out.println("\nYour total points right now is: " + user.getBlackjackValue());
            System.out.println("\n****************************");
            System.out.println("Computer is showing the " + dealer.getCard(0) + "\n");
            System.out.println("Enter 'H' to hit or 'S' to stand. ");
            Scanner sc = new Scanner(System.in);
            String userChoice = sc.next(); // Captures user's response-- either H or S
            do{
                if (userChoice != "H" || userChoice != "h" || userChoice != "S" || userChoice != "s")
                    System.out.println("Please enter either 'H' or 'S': ");
            } while (userChoice != "H" || userChoice != "S");
                // If user hits, get card
                // If user stands, loop ends
                if (userChoice == "S" || userChoice == "s")
                    break;
                else {
                    Card newCard = deck.dealCard();
                    user.addCard(newCard);
                    System.out.println("\nUser chooses to hit. ");
                    System.out.println("Your card is " + newCard);
                    System.out.println("User's total points are now " + user.getBlackjackValue());
                    if (user.getBlackjackValue() > 21) {
                        System.out.println("\nOuch, you went over 21. User learns.");
                        System.out.println("Computer's other card was " + dealer.getCard(1));
                        return false;
                    }
                }
        } // end outer-while

        // Dealer AI for drawing
        System.out.println("Computer chooses to stand. ");
        System.out.println("Computer's cards are: \n" + dealer.getCard(0) + "\n" + dealer.getCard(1));
        while(dealer.getBlackjackValue() <= 16){
            Card newCard = deck.dealCard();
            System.out.println("Computer chooses to hit. They get " + newCard);
            dealer.addCard(newCard);
            if (dealer.getBlackjackValue() > 21){
                System.out.println("Computer dun goofed by going over 21. User wins. ");
                return true;
            }
        }
        System.out.println("Computer's total is " + dealer.getBlackjackValue());

        // If both user and computer gets 21 or less, we determine winner by comparing each other's hands
        if (dealer.getBlackjackValue() == user.getBlackjackValue()) {
            System.out.println("Computer wins on a tie. User learns. ");
        } else if (dealer.getBlackjackValue() > user.getBlackjackValue()) {
            System.out.println("Computer wins! Computer: " + dealer.getBlackjackValue() +
                    "\nUser: " + user.getBlackjackValue());
            return false;
        } else {
            System.out.println("User wins! Computer: " + dealer.getBlackjackValue() +
                    "\nUser: " + user.getBlackjackValue());
        }
        return true;
    }// end playBlackjack()

    static void menu(){
        Scanner sc = new Scanner(System.in);
        int money;
        int bet;
        boolean userWins;

        System.out.println("\nWelcome to Blackjack. \n1. Start Game\n2. Rules\n3. Exit");
        int menuChoice=sc.nextInt(); // Captures user input at menu state

        // Start with user having $100
        money = 100;

        switch(menuChoice){
            case 1:
                while(true){
                    System.out.println("You currently have " + money + " dollars.");
                    do {
                        System.out.println("How many dollars would you like to bet? (Entering 0 will kick you out. )");
                        System.out.print("$");
                        bet = sc.nextInt();
                        // Check appropriate bet
                        if(bet < 0 || bet > money){
                            System.out.println("Your bet must be at least $1 - $" + money + " dollars");
                        }
                    } while( bet < 0 || bet > money);

                    if (bet == 0)
                        break;

                    userWins = playBlackjack();
                    if (userWins)
                        money += bet;
                    else
                        money -= bet;

                    System.out.println("");
                    if (money == 0){
                        System.out.printf("Looks like you ran out of money! Learn how to pull out next time. ");
                        break;
                    }
                }
                System.out.println("");
                System.out.println("You leave with $" + money + "!");
                menu();
            case 2:
                System.out.println("Blackjack Rules:");
                menu();
            case 3:
                System.out.println("Goodbye!");
                break;

        }//end Switch

    }

    public static void main(String[] args){
        menu();

        System.out.println("\nThanks for playing!");
    }//end Main
}//end Blackjack
