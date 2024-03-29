import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class assignmentSeven {

    private static final Random randGen = new Random();
    private static final Scanner scnr = new Scanner(System.in);
    private static String playerName;
    private static String printDataDecision;

    public static void main(String[] args) throws IOException {

        String screenOnly = "screen only";
        String printFile = "print on file";
        String bothPrint = "both";

        System.out.println("Welcome to the dice throw challenge!");
        System.out.println("Do you feel lucky? “Punk”... Do ya?");
        System.out.println("What is your name?");
        playerName = scnr.nextLine();
        System.out.println("How many rounds do you want to play?");
        int roundsToPlay = scnr.nextInt();
        scnr.nextLine(); //consumes the newline character from above to make way for scnr.nextLine()
        System.out.println();
        System.out.println("Do you want to print on screen only, print on file, or both?");
        printDataDecision = scnr.nextLine();


        if(printDataDecision.equals(screenOnly)) {
            int roundsWonPlayer = 0, roundsWonComp = 0;
            for (int roundsPlayed = 0; roundsPlayed < roundsToPlay &&
                    roundsWonPlayer <= roundsToPlay / 2 &&
                    roundsWonComp <= roundsToPlay / 2; roundsPlayed++) {

                System.out.format("Let's begin round %d%n", roundsPlayed + 1);
                System.out.println("-------------------");

                boolean youFirst = (roundsPlayed & 1) == 1;
                System.out.format("In round %d, %s will go first.%n", roundsPlayed + 1, getRoundName(youFirst));

                int score1 = doTurn(youFirst),
                        score2 = doTurn(!youFirst),
                        playerScore = youFirst ? score1 : score2,
                        compScore = youFirst ? score2 : score1;

                boolean playerWins;
                if (playerScore <= 24 && compScore <= 24) {
                    playerWins = playerScore > compScore;
                } else if (playerScore > 24 && compScore <= 24) {
                    playerWins = false;
                } else if (playerScore <= 24 && compScore > 24) {
                    playerWins = true;
                } else {
                    playerWins = playerScore < compScore;
                }

                System.out.format("%s wins this round.%n", playerWins ? playerName : "Computer");
                if (playerWins) {
                    roundsWonPlayer++;
                } else {
                    roundsWonComp++;
                }

                System.out.format("TOTAL ROUNDS WON:%n"
                                + "%s: %d%n"
                                + "Computer: %d%n%n",
                        playerName, roundsWonPlayer, roundsWonComp);
            }

            if (roundsWonPlayer > roundsWonComp) {
                System.out.format("%s WINS THE TOURNAMENT!!!%n", playerName.toUpperCase());
            } else if (roundsWonPlayer < roundsWonComp) {
                System.out.println("COMPUTER WINS THE TOURNAMENT!!!");
            } else {
                System.out.println("IT IS A TIE!!!");
            }
        }else if(printDataDecision.equals(printFile)){
            BufferedWriter writer = new BufferedWriter(new FileWriter(
                    "writeFile.txt"));
            int roundsWonPlayer = 0, roundsWonComp = 0;
            for (int roundsPlayed = 0; roundsPlayed < roundsToPlay &&
                    roundsWonPlayer <= roundsToPlay / 2 &&
                    roundsWonComp <= roundsToPlay / 2; roundsPlayed++) {

                System.out.format("Let's begin round %d%n", roundsPlayed + 1);
                System.out.println("-------------------");

                boolean youFirst = (roundsPlayed & 1) == 1;
                System.out.format("In round %d, %s will go first.%n", roundsPlayed + 1, getRoundName(youFirst));

                int score1 = doTurn(youFirst),
                        score2 = doTurn(!youFirst),
                        playerScore = youFirst ? score1 : score2,
                        compScore = youFirst ? score2 : score1;

                boolean playerWins;
                if (playerScore <= 24 && compScore <= 24) {
                    playerWins = playerScore > compScore;
                } else if (playerScore > 24 && compScore <= 24) {
                    playerWins = false;
                } else if (playerScore <= 24 && compScore > 24) {
                    playerWins = true;
                } else {
                    playerWins = playerScore < compScore;
                }

                System.out.format("%s wins this round.%n", playerWins ? playerName : "Computer");
                if (playerWins) {
                    roundsWonPlayer++;
                } else {
                    roundsWonComp++;
                }

                writer.write(String.valueOf(System.out.format("TOTAL ROUNDS WON:%n"
                                + "%s: %d%n"
                                + "Computer: %d%n%n",
                        playerName, roundsWonPlayer, roundsWonComp)));
            }

            if (roundsWonPlayer > roundsWonComp) {
                System.out.format("%s WINS THE TOURNAMENT!!!%n", playerName.toUpperCase());
            } else if (roundsWonPlayer < roundsWonComp) {
                System.out.println("COMPUTER WINS THE TOURNAMENT!!!");
            } else {
                System.out.println("IT IS A TIE!!!");
            }
            writer.close();

        }
    }

    private static int doTurn(boolean yourTurn) {
        String roundName = getRoundName(yourTurn);
        int[] rolls = getRolls();

        System.out.format("Dice rolls for %s: ", roundName);
        for (int i = 0; i < rolls.length - 1; i++) {
            System.out.format("%d, ", rolls[i]);
        }
        System.out.format("and %d.%n", rolls[rolls.length - 1]);

        int sum = getRollScore(rolls);
        System.out.format("Score for %s: %d%n", roundName, sum);

        for (int hitCounter = 0; hitCounter < 3; hitCounter++) {
            if (yourTurn && sum >= 24 || !yourTurn && sum >= 21) {
                break;
            }
            boolean rollOne;
            if (yourTurn) {
                System.out.println("Do you want to hit or stay?");
                if (!scnr.nextLine().equalsIgnoreCase("hit")) {
                    break;
                }
                System.out.println("Do you want to roll one (1) or two (2) dice?");
                rollOne = scnr.nextInt() == 1;
                scnr.nextLine();  //consumes the newline character to make way for scnr.nextLine()                  
            } else {
                rollOne = sum >= 15;
            }

            int roll4 = newRoll();
            sum += roll4;            //one additional roll
            if (rollOne) {
                System.out.format("%s rolls one more die. It is %d.%n", roundName, roll4);
            } else {
                int roll5 = newRoll();
                sum += roll5;
                System.out.format("%s rolls two more dice. They are %d and %d.%n", roundName, roll4, roll5);
            }
            System.out.format("%s's total score is %d.%n", roundName, sum);
        }
        System.out.println();

        return sum;
    }

    private static String getRoundName(boolean yourTurn) {
        return yourTurn ? playerName : "computer";
    }

    private static int getRollScore(int[] roll) {
        if (roll[0] == roll[1] && roll[0] == roll[2]) {
            return 24;
        }
        return roll[0] + roll[1] + roll[2];
    }

    private static int newRoll() {
        return randGen.nextInt(6) + 1;
    }

    private static int[] getRolls() {
        return new int[]{newRoll(), newRoll(), newRoll()};
    }
}