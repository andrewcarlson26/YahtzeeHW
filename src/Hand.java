import sun.jvm.hotspot.types.CIntegerField;

import java.util.ArrayList;
import java.util.Comparator;

public class Hand extends YahtzeeDie
{

    private ArrayList<YahtzeeDie> hand;
    //Constructor - creates an array of data type YahtzeeDie
    public Hand()
    {
        hand = new ArrayList<YahtzeeDie>();
    }

    //adds a Yahtzee die to the current hand
    /*
     *Gives the ability to add a die of type YahtzeeDie to hand, along with an index spot to place it in
     * params: integer which is an index position, and a die of type YahtzeeDie
     * doesn't return anything just adds to hand
     */
    public void addHand(int index, YahtzeeDie die) {
        hand.add(index, die);
    }

    /*
     *Sorts hand by integer comparison smallest to largest
     * returns nothing, however, organizes the hand e.g. (2, 3, 5, 6, 6)
     */
    public void sortHand() {
        hand.sort(Comparator.comparingInt(YahtzeeDie::getSideUp));
    }

    /*
     *Displays hand to the user
     * returns nothing just outputs a the hand
     */
    public void showHand()
    {
        for (int i = 0; i < hand.size(); i++) {
            System.out.print(hand.get(i).getSideUp() + ", ");
        }
        System.out.println();
    }

    /*
     * Iterates through hand and totals the hand
     * returns an integer
     */
    //totals the hand and returns an integer
    public int totalHand()
    {
        int total = 0;
        int count = 0;

        for (int i = 0; i < hand.size(); i++) {
            count = hand.get(i).getSideUp();
            total += count;
        }
        return total;
    }

    /*
     *Computes the longest length of a straight found in hand
     *returns an integer
     */
    public int findStraight()
    {
        int maxLength = 1;
        int curLength = 1;
        int counter = 0;
        for (counter = 0; counter < 4; counter++) {
            if (hand.get(counter).getSideUp() + 1 == hand.get(counter + 1).getSideUp()) // jump of 1
            {
                curLength++;
            } else if (hand.get(counter).getSideUp() + 1 < hand.get(counter + 1).getSideUp()) //jump of >= 2
            {
                curLength = 1;
            }
            if (curLength > maxLength) {
                maxLength = curLength;
            }
        }
        return maxLength;
    }

    /*
     *iterates through hand and looks to see if a fullHouse is found
     *returns a boolean (t/f)
     */
    public boolean fullHouseFound()
    {
        boolean foundFH = false;
        boolean found3K = false;
        boolean found2K = false;
        int currentCount = 0;
        int dieValue = 0;
        int diePosition = 0;
        for (dieValue = 0; dieValue <= 6; dieValue++) {
            currentCount = 0;

            for (diePosition = 0; diePosition < 5; diePosition++)
            {
                if (hand.get(diePosition).getSideUp() == dieValue)
                    currentCount++;
            }
            if (currentCount == 2)
                found2K = true;
            if (currentCount == 3)
                found3K = true;
        }
        if (found2K && found3K)
            foundFH = true;
        return foundFH;
    }

    /*
     *iterates through hand and looks for the max of a kind within the hand and returns an integer
     *returns an integer
     */
    public int maxOfAKind()
    {
        int maxCount = 0;
        int currentCount ;
        int dieValue = 0;
        for (dieValue = 1; dieValue <=6; dieValue++)
        {
            currentCount = 0;
            for (int diePosition = 0; diePosition < 5; diePosition++)
            {
                if (hand.get(diePosition).getSideUp() == dieValue)
                    currentCount++;
            }
            if (currentCount > maxCount)
                maxCount = currentCount;
        }
        return maxCount;
    }

    /*
     *Fetches the a die given the index and returns the sideUp value of that die
     * params: integer index position in hand arrayList
     * Returns int,  die side up inside the hand
     */
    public int getDieSideUpInHand(int index)
    {
       return hand.get(index).getSideUp();
    }

    /*
     * calls the roll method of Dice class and reassigns that Dice or YahtzeeDie with another value [1-6]
     */
    public void rollDieInHand(int index)
    {
        hand.get(index).roll();
    }
}