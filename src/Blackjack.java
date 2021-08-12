import java.util.InputMismatchException;
import java.util.Scanner;

public class Blackjack {

    static int playBlackjack(int bet, int totalMoney) {
        Deck deck; // Deck of cards
        BlackjackHand dealer; // Dealer's hand
        BlackjackHand user; // User's hand
        boolean doubleDown = false; // Checks whether user double down or not
        int hitCounter = 0; // Counts number of hits decided by user

        // Create objects for the deck, the dealer's hand, and the user's hand
        deck = new Deck();
        dealer = new BlackjackHand();
        user = new BlackjackHand();

        // Shuffle deck first
        deck.shuffle();
        // Dealer draws two cards
        dealer.addCard(deck.dealCard());
        dealer.addCard(deck.dealCard());
        // User draws two cards
        user.addCard(deck.dealCard());
        user.addCard(deck.dealCard());

        // Create gap in message out
        System.out.println();

        // **** Code to check for Blackjack ***

        // If dealer draws a blackjack right away, prompt user, return false, user loses
        if (dealer.getBlackjackValue() == 21) {
            pressAnyButtonToContinue();
            System.out.println("Computer has: \n" + dealer.getCard(0) +
                    "\n" + dealer.getCard(1) + "Computer Points: " + dealer.getBlackjackValue());
            System.out.println("User has: \n" + user.getCard(0) +
                    "\n" + user.getCard(1) + "User Points: " + user.getBlackjackValue());
            System.out.println("\n*************************************");
            System.out.println("Computer has Blackjack-- Dealer wins.");
            System.out.println("*************************************");
            // Break out of the current game loop
            return 1;
        }

        // If user draws 21

        // If user draws a blackjack right away, prompt user, user wins
        if (user.getBlackjackValue() == 21) {
            pressAnyButtonToContinue();
            System.out.println("Computer has: \n" + dealer.getCard(0) +
                    "\n" + dealer.getCard(1) + "\nComputer Points: " + dealer.getBlackjackValue() + "\n");
            System.out.println("User has: \n" + user.getCard(0) +
                    "\n" + user.getCard(1) + "\nUser Points: " + user.getBlackjackValue() + "\n");
            System.out.println("\n*************************************");
            System.out.println("User has Blackjack-- User wins.");
            System.out.println("*************************************");
            // Return back to betting prompt with money earned
            return 2;
        }

        // Play Blackjack if neither user or CPU has Blackjack
        // User gets a chance to draw cards first until they stand
        // If user goes over 21, they lose
        while (true) {
            pressAnyButtonToContinue();
            // Display user's cards, allow them to hit or stand
            System.out.println("****************************");
            System.out.println("User's cards are: ");

            // Display user's cards
            for (int i = 0; i < user.getCardCount(); i++) {
                System.out.println(" " + user.getCard(i));
            }

            // Display user's point
            System.out.println("Your total points right now is: " + user.getBlackjackValue());
            System.out.println("******************************");

            // Display dealer's first card
            System.out.println("Computer is showing the " + dealer.getCard(0) + "\n");

            // Check for correct input from user, while user input is incorrect
            // If correct, user goes into hit or stand loop

            if (bet * 2 > totalMoney) {
                // Take in user input
                System.out.println("Enter 'H' to hit or 'S' to stand. ");
                Scanner sc = new Scanner(System.in);
                // Captures user's response by next char-- either H or S
                char userChoice = sc.next().charAt(0);

                do {
                    if (userChoice != 'H' && userChoice != 'h' && userChoice != 'S' && userChoice != 's') {
                        System.out.println("Please enter either 'H' to hit or 'S' to stand: ");
                        userChoice = sc.next().charAt(0);
                    }

                } while (userChoice != 'H' && userChoice != 'S' && userChoice != 'h' && userChoice != 's');

                // IF USER STANDS, BREAK LOOP
                if (userChoice == 'S' || userChoice == 's')
                    break;
                    // IF USER HITS
                else {
                    // increment hit counter
                    hitCounter++;
                    Card newCard = deck.dealCard();
                    user.addCard(newCard);
                    System.out.println("\nUser chooses to hit. ");
                    System.out.println("User draws " + newCard);
                    System.out.println("User's total points are now " + user.getBlackjackValue());

                    if (user.getBlackjackValue() > 21) {
                        // For-loop in case dealer draws more cards
                        System.out.println("\nComputer has: ");
                        for (int i=0; i<dealer.getCardCount();i++){
                            System.out.println(dealer.getCard(i));
                        }
                        System.out.println("\nComputer Points: " + dealer.getBlackjackValue());

                        // Additional for-loop
                        //System.out.println("User has: \n" + user.getCard(0) +
                        //"\n" + user.getCard(1) + "\nUser Points: " + user.getBlackjackValue());
                        System.out.println("\nUser has: ");
                        for (int i=0; i<user.getCardCount();i++){
                            System.out.println(user.getCard(i));
                        }
                        System.out.println("\nUser Points: " + user.getBlackjackValue());
                        System.out.println("\n************************************");
                        System.out.println("Ouch, you went over 21. User learns.");
                        System.out.println("************************************");
                        if (doubleDown == true) {
                            return 5;
                        }
                        return 1;
                    }
                }
            } else if (hitCounter > 1) {
                // Take in user input
                System.out.println("Enter 'H' to hit or 'S' to stand. ");
                Scanner sc = new Scanner(System.in);
                // Captures user's response by next char-- either H or S
                char userChoice = sc.next().charAt(0);

                do {
                    if (userChoice != 'H' && userChoice != 'h' && userChoice != 'S' && userChoice != 's') {
                        System.out.println("Please enter either 'H' to hit or 'S' to stand: ");
                        userChoice = sc.next().charAt(0);
                    }

                } while (userChoice != 'H' && userChoice != 'S' && userChoice != 'h' && userChoice != 's');

                // IF USER STANDS, BREAK LOOP
                if (userChoice == 'S' || userChoice == 's')
                    break;
                    // IF USER HITS
                else {
                    // increment hit counter
                    hitCounter++;
                    Card newCard = deck.dealCard();
                    user.addCard(newCard);
                    System.out.println("\nUser chooses to hit. ");
                    System.out.println("User draws " + newCard);
                    System.out.println("User's total points are now " + user.getBlackjackValue());

                    if (user.getBlackjackValue() > 21) {
                        System.out.println("Computer has: \n" + dealer.getCard(0) +
                                "\n" + dealer.getCard(1) + "\nComputer Points: " + dealer.getBlackjackValue() + "\n");
                        System.out.println("User has: \n" + user.getCard(0) +
                                "\n" + user.getCard(1) + "\nUser Points: " + user.getBlackjackValue() + "\n");
                        System.out.println("\n************************************");
                        System.out.println("Ouch, you went over 21. User learns.");
                        System.out.println("************************************");
                        if (doubleDown == true) {
                            return 5;
                        }
                        return 1;
                    }
                }
            } else {
                // Take in user input
                System.out.println("Enter 'H' to hit or 'S' to stand or 'D' to double down. ");
                Scanner sc = new Scanner(System.in);
                // Captures user's response by next char-- either H or S
                char userChoice = sc.next().charAt(0);

                do {
                    if (userChoice != 'H' && userChoice != 'h' && userChoice != 'S' && userChoice != 's'
                            && userChoice != 'D' && userChoice != 'd') {
                        System.out.println("Please enter either 'H' to hit or 'S' to stand: ");
                        userChoice = sc.next().charAt(0);
                    }

                } while (userChoice != 'H' && userChoice != 'S' && userChoice != 'h' && userChoice != 's'
                        && userChoice != 'D' && userChoice != 'd');

                // IF USER STANDS, BREAK LOOP
                if (userChoice == 'S' || userChoice == 's')
                    break;

                    // IF USER DOUBLES DOWN
                else if (userChoice == 'D' || userChoice == 'd') {
                    while (hitCounter == 0) {
                        doubleDown = true;
                        // Create a new card object from our deck
                        Card newCard = deck.dealCard();

                        // Add new card to user's hand
                        user.addCard(newCard);

                        System.out.println("\nUser chooses to double down! ");
                        System.out.println("User draws " + newCard);

                        // If user doubles-down and gets Blackjack
                        if (user.getBlackjackValue() == 21){
                            System.out.println("***Blackjack!***\nUser's total points are now "
                                    + user.getBlackjackValue());
                            return 4;
                        }
                        System.out.println("User's total points are now " + user.getBlackjackValue());
                        hitCounter++;
                        pressAnyButtonToContinue();

                        // IF USER BUSTS AFTER DOUBLING DOWN
                        if (user.getBlackjackValue() > 21) {
                            System.out.println("\nOuch, you went over 21. User learns.");
                            System.out.println("Computer's other card was " + dealer.getCard(1));
                            System.out.println("Computer's total points: " + dealer.getBlackjackValue());
                            return 5;
                        }
                        break;
                    }
                    break;
                }
                // IF USER HITS
                else {
                    // increment hit counter
                    hitCounter++;
                    Card newCard = deck.dealCard();
                    user.addCard(newCard);
                    System.out.println("\nUser chooses to hit. ");
                    System.out.println("User draws " + newCard);
                    System.out.println("User's total points are now " + user.getBlackjackValue());

                    if (user.getBlackjackValue() > 21) {
                        System.out.println("Computer has: \n" + dealer.getCard(0) +
                                "\n" + dealer.getCard(1) + "\nComputer Points: " + dealer.getBlackjackValue() + "\n");
                        System.out.println("User has: \n" + user.getCard(0) +
                                "\n" + user.getCard(1) + "\nUser Points: " + user.getBlackjackValue() + "\n");
                        System.out.println("\n************************************");
                        System.out.println("Ouch, you went over 21. User learns.");
                        System.out.println("************************************");
                        if (doubleDown == true) {
                            return 5;
                        }
                        return 1;
                    }
                }
            } // end outer-while
        }


        // Dealer AI for drawing
        System.out.println("\n******************************");
        System.out.println("Computer's cards are: \n" + dealer.getCard(0) + "\n\nComputer shows second card:\n"
                + dealer.getCard(1));

        // Logic for computer to draw more cards when hand value is under 17
        while (dealer.getBlackjackValue() <= 16) {
            Card newCard = deck.dealCard();
            System.out.println("\nComputer chooses to hit.\n...They draw " + newCard);
            dealer.addCard(newCard);

            if (dealer.getBlackjackValue() > 21) {
                pressAnyButtonToContinue();
                System.out.println("Computer has: \n" + dealer.getCard(0) +
                        "\n" + dealer.getCard(1) + "\nComputer Points: " + dealer.getBlackjackValue());
                System.out.println("\nUser has: \n" + user.getCard(0) +
                        "\n" + user.getCard(1) + "\nUser Points: " + user.getBlackjackValue());
                System.out.println("\n*************************************");
                System.out.println("Dealer has busted-- user wins.");
                System.out.println("*************************************");
                if (doubleDown == true) {
                    return 4;
                }
                return 2;
            }
        }
        System.out.println("******************************");


        // If both user and computer gets 21 or less, we determine winner by comparing each other's hands
        if (dealer.getBlackjackValue() == user.getBlackjackValue()) {
            System.out.println("Computer and user tie in value. User pushes. ");
            return 3;
        } else if (dealer.getBlackjackValue() > user.getBlackjackValue()) {
            //System.out.println("\nComputer has: \n" + dealer.getCard(0) +
                    //"\n" + dealer.getCard(1) + "\nComputer Points: " + dealer.getBlackjackValue() + "\n");

            // For-loop in case dealer draws more cards
            System.out.println("\nComputer has: ");
            for (int i=0; i<dealer.getCardCount();i++){
                System.out.println(dealer.getCard(i));
            }
            System.out.println("Computer Points: " + dealer.getBlackjackValue());

            // Additional for-loop
            //System.out.println("User has: \n" + user.getCard(0) +
                    //"\n" + user.getCard(1) + "\nUser Points: " + user.getBlackjackValue());
            System.out.println("\nUser has: ");
            for (int i=0; i<user.getCardCount();i++){
                System.out.println(user.getCard(i));
            }
            System.out.println("User Points: " + user.getBlackjackValue());

            System.out.println("\n************************************");
            System.out.println("Computer has a higher value-- Dealer wins!");
            System.out.println("************************************");
            if (doubleDown == true) {
                return 5;
            }
            return 1;
        } else {
            System.out.println("\n******************************");
            System.out.println("User wins! \nComputer: " + dealer.getBlackjackValue() +
                    "\nUser: " + user.getBlackjackValue());
            if (doubleDown) {
                return 4;
            }

            System.out.println("\n******************************");
            return 2;
        }
    }// end playBlackjack()

    static void menu() {
        Scanner sc = new Scanner(System.in);
        int money;

        int bet = 0;
        int userWins;
        System.out.println("*********************\nWelcome to Blackjack.\n*********************\n" +
                "1. Start Game\n2. Rules\n3. Exit");
        int menuChoice = sc.nextInt(); // Captures user input at menu state

        // Start with user having $100
        money = 100;

        switch (menuChoice) {
            case 1:
                while (true) {
                    do {
                        clearTerminal();
                        System.out.println("You currently have " + money + " dollars.");

                        do {
                            System.out.println("How many dollars would you like to bet? (Entering 0 will kick you out. )");
                            System.out.print("$");
                            try {
                                bet = sc.nextInt();
                            } catch (InputMismatchException e) {
                                sc.next();
                                System.out.println("Please enter a numerical value! ");
                            }
                        } while (!(bet > 0));


                        // Check appropriate bet
                        if (bet < 0 || bet > money) {
                            System.out.println("Your bet must be at least $1 - $" + money + " dollars");
                        }
                    } while (bet < 0 || bet > money);

                    if (bet == 0)
                        break;

                    // userWins can be true or false depending on what playBlackjack() returns
                    userWins = playBlackjack(bet, money);
                    // if userWins is true
                    switch (userWins) {
                        // if user loses
                        case 1:
                            money -= bet;
                            break;

                        // if dealer wins
                        case 2:
                            money += bet;
                            break;

                        // if dealer and user tie-- push
                        case 3:
                            money += 0;
                            break;
                        // if user doubles down and wins
                        case 4:
                            System.out.println("User doubles down and wins this round!");
                            money += bet * 2;
                            break;
                        // if user doubles down and loses
                        case 5:
                            System.out.println("User doubles down and loses this round!\n***************************************");
                            money -= bet * 2;
                            break;

                    }


                    System.out.println("");
                    if (money == 0) {
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
        System.out.print("Press enter to continue..");
        try {
            System.in.read();
        } catch (Exception e) {
        }

    }

    public final static void clearTerminal() {
        try {
            // We store the kind of OS user is using as a String
            final String os = System.getProperty("os.name");
            // If it they're using Windows, then use cls
            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
                System.out.println("Windows");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        menu();

        System.out.println("\nThanks for playing!");
    }//end Main
}//end Blackjack
