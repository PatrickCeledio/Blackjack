import java.util.Scanner;
public class Blackjack {

    static boolean playBlackjack(int bet){
        Deck deck; // Deck of cards
        BlackjackHand dealer; // Dealer's hand
        BlackjackHand user; // User's hand
        int userBet = bet;

        // Create objects for the deck, the dealer's hand, and the user's hand
        deck = new Deck();
        dealer = new BlackjackHand();
        user = new BlackjackHand();

        // Shuffle deck first
        deck.shuffle();
        // Dealer draws two cards
        dealer.addCard( deck.dealCard() );
        dealer.addCard( deck.dealCard() );
        // User draws two cards
        user.addCard( deck.dealCard() );
        user.addCard( deck.dealCard() );

        // Create gaps in message out
        System.out.println();
        System.out.println();

        // **** Code to check for Blackjack ***

        // If dealer draws a blackjack right away, prompt user, return false, user loses
        if (dealer.getBlackjackValue() == 21) {
            System.out.println("Computer has the " + dealer.getCard(0) +
                    " and the " + dealer.getCard(1) + "\nComputer Points: " + dealer.getBlackjackValue());
            System.out.println("User has the " + user.getCard(0) +
                    " and the " + user.getCard(1) + "\nUser Points: " + user.getBlackjackValue());
            System.out.println("Computer has Blackjack-- Dealer wins.");
            // Break out of the current game loop
            return false;
        }

        // If user draws 21

        // If user draws a blackjack right away, prompt user, user wins
        if (user.getBlackjackValue() == 21) {
            System.out.println("Computer has the " + dealer.getCard(0) +
                    " and the " + dealer.getCard(1) + "\nComputer Points: " + dealer.getBlackjackValue());
            System.out.println("User has the " + user.getCard(0) +
                    " and the " + user.getCard(1) + "\nUser Points: " + user.getBlackjackValue());
            System.out.println("User has Blackjack-- User wins.");
            //
            return true;
        }

        // Play Blackjack is neither user or CPU has Blackjack
        // User gets a chance to draw cards first until they stand
        // If user goes over 21, they lose
        while(true){
            // Display user's cards, allow them to hit or stand
            System.out.println("\n\n****************************");
            System.out.println("\nUser's cards are: ");

            // Display user's cards
            for (int i = 0; i < user.getCardCount(); i++) {
                System.out.println(" " + user.getCard(i));
            }

            // Display user's point
            System.out.println("\nYour total points right now is: " + user.getBlackjackValue());
            System.out.println("\n******************************");

            // Display dealer's first card
            System.out.println("Computer is showing the " + dealer.getCard(0) + "\n");

            // Take in user input
            System.out.println("Enter 'H' to hit or 'S' to stand. ");
            Scanner sc = new Scanner(System.in);
            
            // Captures user's response by next char-- either H or S
            char userChoice = sc.next().charAt(0);

            // Check for correct input from user, while user input is incorrect
            // If correct, user goes into hit or stand loop
            do{
                if (userChoice != 'H' && userChoice != 'h' && userChoice != 'S' && userChoice != 's'
                        && userChoice != 'D' && userChoice != 'd' )
                    System.out.println("Please enter either 'H' or 'S': ");
            } while (userChoice != 'H' && userChoice != 'S' && userChoice != 'h' && userChoice != 's'
                        && userChoice != 'D' && userChoice != 'd' );

            // If user hits, get card
            // If user stands, loop ends
            if (userChoice == 'S' || userChoice == 's') // User stands
                break;
            else if ( userChoice == 'D' || userChoice == 'd') { // User double-downs WIP

                // Create a new card object from our deck
                Card newCard = deck.dealCard();

                // Add new card to user's hand
                user.addCard(newCard);

                // Double the bet
                userBet += userBet;

                System.out.println("\nUser chooses to double down! ");
                System.out.println("User draws " + newCard);
                System.out.println("User's total points are now " + user.getBlackjackValue());

                if (user.getBlackjackValue() > 21) {
                    System.out.println("\nOuch, you went over 21. User learns.");
                    System.out.println("Computer's other card was " + dealer.getCard(1));
                    System.out.println("Computer's total points: " + dealer.getBlackjackValue());
                    return false;
                }

                // Break out of hit or stand loop
                break;

            }
            else { // User hits
                Card newCard = deck.dealCard();
                user.addCard(newCard);
                System.out.println("\nUser chooses to hit. ");
                System.out.println("User draws " + newCard);
                System.out.println("User's total points are now " + user.getBlackjackValue());

                if (user.getBlackjackValue() > 21) {
                    System.out.println("\nOuch, you went over 21. User learns.");
                    System.out.println("Computer's other card was " + dealer.getCard(1));
                    System.out.println("Computer's total points: " + dealer.getBlackjackValue());
                    return false;
                }
            }
        } // end outer-while

        // Dealer AI for drawing
        System.out.println("\n******************************");
        System.out.println("Computer's cards are: \n" + dealer.getCard(0) + "\nComputer shows second card: "
                + dealer.getCard(1));
        while(dealer.getBlackjackValue() <= 16){
            Card newCard = deck.dealCard();
            System.out.println("\nComputer chooses to hit.\n...They draw " + newCard);
            pressAnyButtonToContinue();
            dealer.addCard(newCard);
            if (dealer.getBlackjackValue() > 21){
                pressAnyButtonToContinue();
                System.out.println("Computer dun goofed by going over 21!!! Computer busted! User wins. Humanity wins.");
                return true;
            }
        }
        System.out.println("\n******************************");


        // If both user and computer gets 21 or less, we determine winner by comparing each other's hands
        if (dealer.getBlackjackValue() == user.getBlackjackValue()) {
            System.out.println("Computer wins on a tie. User learns. ");
            return false;
        } else if (dealer.getBlackjackValue() > user.getBlackjackValue()) {
            System.out.println("Computer wins! Computer: " + dealer.getBlackjackValue() +
                    "\nUser: " + user.getBlackjackValue());
            return false;
        } else {
            System.out.println("\n******************************");
            System.out.println("User wins! \nComputer: " + dealer.getBlackjackValue() +
                    "\nUser: " + user.getBlackjackValue());
            System.out.println("\n******************************");
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

                    userWins = playBlackjack(bet);
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
                // Code to texit the game
                System.exit(0);
                break;

        }//end Switch

    }
    
    static void pressAnyButtonToContinue() {
        System.out.println("Press enter to continue..\n");
        try {
            System.in.read();
        }
        catch (Exception e){}

    }

    public static void main(String[] args){
        menu();

        System.out.println("\nThanks for playing!");
    }//end Main
}//end Blackjack
