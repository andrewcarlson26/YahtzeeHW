import sun.jvm.hotspot.types.CIntegerField;

import java.util.ArrayList;
import java.util.Comparator;

public class Hand extends YahtzeeDie
{
    private ArrayList<YahtzeeDie> hand;
    //creates an array of data type YahtzeeDie
    public Hand()
    {
        hand = new ArrayList<YahtzeeDie>();
    }

    //adds a Yahtzee die to the current hand
    public void addHand(int index, YahtzeeDie die) {
        hand.add(index, die);
    }

    //Sorts hand by integer comparison smallest to largest
    public void sortHand() {
        hand.sort(Comparator.comparingInt(YahtzeeDie::getSideUp));
    }

    //Displays the hand
    public void showHand()
    {
        for (int i = 0; i < hand.size(); i++) {
            System.out.print(hand.get(i).getSideUp() + ", ");
        }
        System.out.println();
    }

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

    //returns the longest length of a straight found in hand
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

    //iterates through a hand and looks to see if a full house is found or not
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

    //iterates through hand and looks for the max of a kind within the hand and returns an integer
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

    //Returns die side up inside the hand
    public int getDieSideUpInHand(int index)
    {
       return hand.get(index).getSideUp();
    }

    //rolls a die inside the hand
    public void rollDieInHand(int index)
    {
        hand.get(index).roll();
    }
}