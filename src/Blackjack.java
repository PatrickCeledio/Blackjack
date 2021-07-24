import java.util.Scanner;
public class Blackjack {

    public static boolean playBlackjack(){
        Deck deck; // Deck of cards
        BlackjackHand dealer; // Dealer's hand
        BlackjackHand user; // User's hand

        deck = new Deck();
        dealer = new BlackjackHand();
        user = new BlackjackHand();

        deck.shuffle();
        dealer.addCard( deck.dealCard() );
        dealer.addCard( deck.dealCard() );
        user.addCard( deck.dealCard() );
        user.addCard( deck.dealCard() );


        return true;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int money;
        int bet;
        boolean userWins;

        System.out.println("Welcome to Blackjack. \n1. Start Game\n2. Rules\n3. Exit");
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
                break;
            case 2:
                System.out.println("Blackjack Rules:");
                break;
            case 3:
                System.out.println("Goodbye!");
                break;

        }//end Switch

        System.out.println("\nThanks for playing!");
    }//end Main
}//end Blackjack
