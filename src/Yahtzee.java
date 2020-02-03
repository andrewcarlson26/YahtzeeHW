/*
 * This program creates a game of yahtzee for one hand, the user is
 * given 5 dice, all randomly rolled, and asked to keep them or re roll them for 3 rolls total,
 * including the first roll. A score is computed based off the dice the user has left at the end.
 * CPSC 224-01, Spring 2020
 * Programming Assignment #1
 * No sources to cite.
 *
 * @author Andrew Carlson
 * @version v1.0 2/3/20
 */
import com.sun.codemodel.internal.JArray;
import sun.text.normalizer.UCharacter;

import javax.swing.plaf.DesktopIconUI;
import java.lang.reflect.Array;
import java.util.*;

public class Yahtzee
{
    public static void main(String[] args)
    {
        String playAgain = "y";
        while (playAgain.contains("y"))
        {
            int turnCount = 0;
            boolean goodChar = false; //used for the userChoice check as well
            final int NUM_DICE = 5; //Number of Dice in play
            //Declaration of dice
            YahtzeeDie die1 = new YahtzeeDie();
            YahtzeeDie die2 = new YahtzeeDie();
            YahtzeeDie die3 = new YahtzeeDie();
            YahtzeeDie die4 = new YahtzeeDie();
            YahtzeeDie die5 = new YahtzeeDie();

            //Create Array
            Hand myHand = new Hand();
            //Load Array with Dice
            myHand.addHand(0, die1);
            myHand.addHand(1, die2);
            myHand.addHand(2, die3);
            myHand.addHand(3, die4);
            myHand.addHand(4, die5);

            //Keyboard input
            Scanner kb = new Scanner(System.in);
            //default all no user to choice to begin
            String userChoice = "nnnnn";

            //Intro to game
            displayGreeting();

            //Initial Rolls
            die1.roll();
            die2.roll();
            die3.roll();
            die4.roll();
            die5.roll();

            while (turnCount != 2)
            {
                myHand.showHand(); //displays hand

                System.out.println("Press 'y' to keep the dice and 'n' to keep the corresponding dice (i.e 'yynny') then press enter:");

                userChoice = kb.nextLine(); //takes in the user choice to keep or re roll dice


                //Checks valid entry of whether to keep or re roll
                    while ((userChoice.length() != NUM_DICE)) {
                        System.out.println("ERROR: Incorrect number of key inputs! Please retype them now:");
                        userChoice = kb.nextLine();
                    }

                    //Checks if the chars in the userChoice string are legal they MUST be a 'y' or a 'n'
                    goodChar = false; //This is done to reset the loop when someone rolls again
                    while(!goodChar)
                    {
                        for (int j = 0; j < NUM_DICE; j++)
                        {
                         if( (userChoice.charAt(j) == 'y') || (userChoice.charAt(j) == 'n'))
                         {
                             goodChar = true;
                         }
                         else
                         {
                             goodChar = false;
                             System.out.println("ERROR: Incorrect key inputs! Please retype them now:");
                             userChoice = kb.nextLine();
                             break;
                         }
                        }

                    }
                //iterates through userChoice and re rolls die if it encounters an 'n'
                for (int i = 0; i < NUM_DICE; i++)
                {
                    if(userChoice.charAt(i) == 'n')
                    {
                        myHand.rollDieInHand(i); //rolls a die inside the current hand
                        myHand.getDieSideUpInHand(i); //gets the side up inside the hand
                    }
                }
                if (userChoice.matches("yyyyy"))
                {
                    break;
                }
                turnCount++;
            }

            //Sorts arrayList
            myHand.sortHand();
            System.out.println("Your sorted hand is: ");
            myHand.showHand();
            System.out.println();
            System.out.println("SCORING TABLE:");

            //upper scorecard
            for (int dieValue = 1; dieValue <= 6; dieValue++)
            {
                int currentCount = 0;
                for (int diePosition = 0; diePosition < 5; diePosition++)
                {
                    if (myHand.getDieSideUpInHand(diePosition) == dieValue)
                        currentCount++;
                }
                System.out.println("Score " + dieValue * currentCount + " on the " + dieValue + " line");
            }

            //lower scorecard
            if (myHand.maxOfAKind() >= 3)
            {
                System.out.println("Score " + myHand.totalHand() + " on the 3 of a Kind Line");
            } else System.out.println("Score 0 on the 3 of a Kind line");

            if (myHand.maxOfAKind() >= 4)
            {
                System.out.println("Score " + myHand.totalHand() + " on the ");
                System.out.println("4 of a Kind line");
            } else System.out.println("Score 0 on the 4 of a Kind line");

            if (myHand.fullHouseFound())
                System.out.println("Score 25 on the Full House line");
            else
                System.out.println("Score 0 on the Full House line");

            if (myHand.findStraight() >= 4)
                System.out.println("Score 30 on the Small Straight line");
            else
                System.out.println("Score 0 on the Small Straight line");

            if (myHand.findStraight() >= 5)
                System.out.println("Score 40 on the Large Straight line");
            else
                System.out.println("Score 0 on the Large Straight line");

            if (myHand.maxOfAKind() >= 5)
                System.out.println("Score 50 on the Yahtzee line");
            else
                System.out.println("Score 0 on the Yahtzee line");

            System.out.println("Score " + myHand.totalHand() + " on the chance line");

            System.out.println("Thanks for playing! Want to play again? Press 'y' for yes and 'n' for no!");
            playAgain = kb.nextLine();
        }
    }

    /*
     *Displays greeting at the beginning of a new game
     *This is a static method and just used to clean up some code and just output the intro to the user
     * returns nothing
     */

    public static void displayGreeting()
    {
        System.out.println("Welcome to Yahtzee");
        System.out.println("Let's get you started with your first roll! Please press any key to continue!");
    }

}

