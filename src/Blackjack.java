import java.util.Scanner;
public class Blackjack {

    static int playBlackjack(int bet){
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
            return 1;
        }

        // If user draws 21

        // If user draws a blackjack right away, prompt user, user wins
        if (user.getBlackjackValue() == 21) {
            System.out.println("Computer has the " + dealer.getCard(0) +
                    " and the " + dealer.getCard(1) + "\nComputer Points: " + dealer.getBlackjackValue());
            System.out.println("User has the " + user.getCard(0) +
                    " and the " + user.getCard(1) + "\nUser Points: " + user.getBlackjackValue());
            System.out.println("User has Blackjack-- User wins.");
            // Return back to betting prompt with money earned
            return 2;
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
                        && userChoice != 'D' && userChoice != 'd' ) {
                    System.out.println("Please enter either 'H' or 'S': ");
                    userChoice = sc.next().charAt(0);
                }

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
                    return 1;
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
                    return 1;
                }
            }
        } // end outer-while

        // Dealer AI for drawing
        System.out.println("\n******************************");
        System.out.println("Computer's cards are: \n" + dealer.getCard(0) +"\n\nComputer shows second card:\n"
                + dealer.getCard(1));
        while(dealer.getBlackjackValue() <= 16){
            Card newCard = deck.dealCard();
            System.out.println("\nComputer chooses to hit.\n...They draw " + newCard);
            dealer.addCard(newCard);
            if (dealer.getBlackjackValue() > 21){
                pressAnyButtonToContinue();
                System.out.println("Computer dun goofed by going over 21!!! Computer busted! User wins. Humanity wins.");
                return 2;
            }
        }
        System.out.println("\n******************************");


        // If both user and computer gets 21 or less, we determine winner by comparing each other's hands
        if (dealer.getBlackjackValue() == user.getBlackjackValue()) {
            System.out.println("Computer wins on a tie. Computer laughs at you. ");
            return 1;
        } else if (dealer.getBlackjackValue() > user.getBlackjackValue()) {
            System.out.println("Computer wins! Computer: " + dealer.getBlackjackValue() +
                    "\nUser: " + user.getBlackjackValue());
            return 1;
        } else {
            System.out.println("\n******************************");
            System.out.println("User wins! \nComputer: " + dealer.getBlackjackValue() +
                    "\nUser: " + user.getBlackjackValue());
            System.out.println("\n******************************");
            return 2;
        }
    }// end playBlackjack()

    static void menu(){
        Scanner sc = new Scanner(System.in);
        int money;
        int bet;
        int userWins;

        System.out.println("Welcome to Blackjack. \n1. Start Game\n2. Rules\n3. Exit");
        int menuChoice=sc.nextInt(); // Captures user input at menu state

        // Start with user having $100
        money = 100;

        switch(menuChoice){
            case 1:
                while(true){
                    do {
                        clearTerminal();
                        System.out.println("You currently have " + money + " dollars.");
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

                    // userWins can be true or false depending on what playBlackjack() returns
                    userWins = playBlackjack(bet);
                    System.out.println("\nuserWins current is: " + userWins);
                    // if userWins is true
                    switch (userWins){
                        // if user wins
                        case 1:
                            money -= bet;
                            break;

                        // if dealer wins
                        case 2:
                            money += bet;
                            break;
                    }


                    System.out.println("");
                    if (money == 0){
                        System.out.printf("Looks like you lost all of your virtual money! Uh-oh. ");
                        break;
                    }
                }
                System.out.println("");
                System.out.println("You leave with $" + money + "!\n");
                menu();
            case 2:
                System.out.println("Blackjack Rules:\n");
                System.out.println("**********************");
                System.out.println("Basic Blackjack Rules:\n" +
                        "**********************" +
                        "\n" +
                        "1. The goal of blackjack is to beat the dealer's hand without going over 21.\n" +
                        "2. Face cards are worth 10. Aces are worth 1 or 11, whichever makes a better hand.\n" +
                        "3. Each player starts with two cards, one of the dealer's cards is hidden until the end.\n" +
                        "4. To 'Hit' is to ask for another card. To 'Stand' is to hold your total and end your turn.\n" +
                        "5. If you go over 21 you bust, and the dealer wins regardless of the dealer's hand.\n" +
                        "6. If you are dealt 21 from the start (Ace & 10), you got a blackjack.\n" +
                        "7. In this game, Blackjack means you will earn twice the money you bet. \n" +
                        "8. Dealer will hit until their card's total 17 or higher.\n" +
                        "9. Doubling is like a hit, only the bet is doubled and you only get one more card.\n" +
                        "10. Split can be done when you have two of the same card - the pair is split into two hands.\n" +
                        "11. Splitting also doubles the bet, because each new hand is worth the original bet.\n" +
                        "12. You can only double/split on the first move, or first move of a hand created by a split.\n" +
                        "13. You cannot play on two aces after they are split.\n" +
                        "14. You can double on a hand resulting from a split, tripling or quadrupling you bet.\n");
                pressAnyButtonToContinue();
                menu();
            case 3:
                System.out.println("Goodbye!");
                // Code to exit the game
                System.exit(0);
                break;

        }//end Switch

    }
    
    static void pressAnyButtonToContinue() {
        System.out.println("Press enter to continue..");
        try {
            System.in.read();
        }
        catch (Exception e){}

    }

    public final static void clearTerminal()
    {
        try
        {
            // We store the kind of OS user is using as a String
            final String os = System.getProperty("os.name");
            // If it they're using Windows, then use cls
            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
                System.out.println("Windows");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        menu();

        System.out.println("\nThanks for playing!");
    }//end Main
}//end Blackjack
