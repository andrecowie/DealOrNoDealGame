
/**
 * Created by Andre Cowie 14862344 on 16/03/2016
 * Tony van Swet 0829113 5/04/2016
 * Need to add DBInteractions 15/05 -abc
 */
import java.util.*;

public class DealOrNoDeal {
    static Integer[] prizes = {0, 1, 2, 5, 10, 20, 50, 100, 150, 200, 500, 750, 1000, 2000, 3000, 4000, 5000, 10000, 15000, 20000, 30000, 50000, 75000, 100000, 200000, 500000};
    private Case[] cases = new Case[26];
    Case[] sortedCases = new Case[26];
    private Player contestant;
    private int selectedCase;

    public static Scanner scan = new Scanner(System.in);

    public DealOrNoDeal(String _contestantName, int _selectedCase) {
        /*
        if(_contestantName is in the database){
         then load the player and produce out put saying his previous score and which case he choose.

        } else{*/
            this.contestant = new Player(_contestantName);
        //}

        setSelectedCase(_selectedCase);
        ArrayList<Integer> prize = new ArrayList<Integer>(Arrays.asList(prizes));
        for(int i=0; i < 26; i++){
            this.sortedCases[i] = new Case();
            this.sortedCases[i].setDollarsInside(prize.get(i));
        }
        Collections.shuffle(prize);
        for (int i = 0; i < 26; i++) {
            this.cases[i] = new Case();
            this.cases[i].setDollarsInside(prize.get(i));
        }
        this.cases[_selectedCase].setSelected(true);
    }

    public int Offer() {
        int totalAccumulated = 0;
        int remainingUnopened = 0;
        for (int i = 0; i < 26; i++) {
            if (!this.cases[i].isOpen()) {
                totalAccumulated = totalAccumulated + this.cases[i].getDollarsInside();
                remainingUnopened++;
            }
        }
        return totalAccumulated / remainingUnopened;
    }

    public boolean isItADeal() {
        System.out.println("Great you have opened enough cases for the banker to give you an offer.");
        int bankersDeal = this.Offer();
        System.out.println("The banker gave you an offer of $" + bankersDeal);
        System.out.println("(D)eal or (N)o Deal?");
        scan.nextLine();
        String soDeal = scan.nextLine();
        switch (soDeal.toUpperCase()) {
            case "D":
                break;
            case "N":
                break;
            default:
                System.out.println("please enter again ");
                boolean repeat = true;

                while (repeat) {
                    System.out.println("(D)eal or (N)o Deal");
                    soDeal = scan.nextLine();

                    switch (soDeal.toUpperCase()) {
                        case "D":
                            repeat = false;
                            break;
                        case "N":
                            repeat = false;
                            break;
                    }
                }
                break;
        }
        if (soDeal.charAt(0) == 'D' || soDeal.charAt(0) == 'd') {
            System.out.println("DEAL!\nYou won $" + bankersDeal + "\nSpend it wisely.");
            return true;
        } else {
            System.out.println("NO DEAL!");
            return false;
        }
    }

    public void openCase(int caseNumber) {
        this.cases[caseNumber].setOpen(true);
        System.out.println("You opened case number: " + (caseNumber + 1));
        System.out.println("It contained: $" + this.cases[caseNumber].getDollarsInside());
    }

    public void pickACase() {
        System.out.println("Pick a case!");
        printClosedCases();
        try {
            int selection = (scan.nextInt() - 1);
            while (selection == this.getSelectedCase()) {
                System.out.println("You can not open your case just yet, pick another.");
                selection = (scan.nextInt() - 1);
            }
            while (this.cases[selection].isOpen()) {
                System.out.println("You have already opened that case, pick another.");
                selection = (scan.nextInt() - 1);
            }
            this.openCase(selection);
        } catch (Exception e) {
            System.out.println("That is not a valid case!");
            scan.nextLine();
            pickACase();
        }
    }

    public String printCases() {
        String openedCases = "Opened Prizes: ";
        String closedCases = "\nPrizes Still Available: ";
        for (int i = 0; i < 26; i++) {
            for(int x = 0; x < 26; x ++){
                if(cases[x].getDollarsInside() == prizes[i].intValue()){
                    if(cases[x].isOpen()){
                        openedCases += prizes[i].intValue();
                        openedCases += ", ";
                    } else{
                        closedCases += prizes[i].intValue();
                        closedCases += ", ";
                    }
                }
            }
        }
        return openedCases.substring(0, openedCases.length() - 2) + closedCases.substring(0, closedCases.length() - 2);
    }

    public void printClosedCases() {
        System.out.print("Available Cases: ");
        for (int i = 0; i < 26; i++) {
            if (!(cases[i].isOpen())) {
                if(!cases[i].isSelected())
                System.out.print("["+(i+1)+"]");
            }
        }
        System.out.println(" ");
    }

    public static String welcomePlayer(){
        System.out.println("Welcome to deal or no deal!");
        System.out.println("What is your name?");
        String playerName = scan.nextLine();
        while (playerName.length() < 1) {
            System.out.println("Please enter a valid name");
            playerName = scan.nextLine();
        }
        System.out.print("We wish you good luck " + playerName + ".\nNow lets get underway, we have twenty-six cases each containing potential prize that you could win!\n");
        return playerName;
    }

    public static int selectContestantsCast(){
        int playersCase = 0;

        while(playersCase < 1 || playersCase > 26){
            System.out.println("Which case would you like to select? (1-26)");
            while (!scan.hasNextInt()) {

                System.out.println("Enter a valid number");
                scan.next();
            }
            playersCase = scan.nextInt();
        }

        playersCase--;
        return playersCase;
    }

    public boolean openSomeCases(int howMany){
        System.out.println("Great you will now open "+howMany+" cases in a row and then the banker will give you an offer");
        for (int i = 0; i < howMany; i++) {
            pickACase();
        }
        System.out.println(this.printCases());
        if (this.isItADeal()) {
            return true;
        }else{
            return false;
        }
    }


    public static void main(String[] args) {
        String playerName = welcomePlayer();
        int playersCase = selectContestantsCast();
        System.out.println("Its time to play deal or no deal!!!");
        DealOrNoDeal game = new DealOrNoDeal(playerName, playersCase);

        for(int i = 6; i > 0;i--){
            if(i==2 || i==1){
                if(game.openSomeCases(i)){
                    return;
                }
                if(game.openSomeCases(i)){
                    return;
                }
                if (i == 1){
                    System.out.println("Wow you have mad it to the end of the game there are only two prizes left!\nAre you sure you don't want this offer?");
                    System.out.println("$"+game.Offer());
                    System.out.println("(D)eal or (N)o Deal?");
                    String soDeal;
                    do {
                        soDeal =scan.nextLine();
                    } while (!(soDeal.toUpperCase().charAt(0) == 'D' || soDeal.toUpperCase().charAt(0) == 'N'));
                    if (soDeal.charAt(0) == 'D') {
                        System.out.println("DEAL!\nYou won $" + game.Offer() + "\nSpend it wisely.");
                    } else {
                        System.out.println("NO DEAL!");
                        System.out.println("Time to open your case!\nIt contains: $" + game.cases[playersCase].getDollarsInside() + "\nCongratulations. Well played.");
                    }
                }
            }else{
                if(game.openSomeCases(i)){
                    return;
                }
            }
        }
    }

    public Case[] getCases() {
        return cases;
    }

    public void setCases(Case[] cases) {
        this.cases = cases;
    }

    public int getSelectedCase() {
        return selectedCase;
    }

    public void setSelectedCase(int selectedCase) {
        this.selectedCase = selectedCase;
    }

    public Player getContestant() {
        return contestant;
    }
}
