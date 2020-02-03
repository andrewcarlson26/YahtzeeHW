import java.util.ArrayList;

public class DiceTest
{
    public static void main(String[] args)
    {
        YahtzeeDie die1 = new YahtzeeDie();
        YahtzeeDie die2 = new YahtzeeDie();
        YahtzeeDie die3 = new YahtzeeDie();
        YahtzeeDie die4 = new YahtzeeDie();
        YahtzeeDie die5 = new YahtzeeDie();

        Hand hand1 = new Hand();

        hand1.addHand(0, die1);
        hand1.addHand(1,die2);
        hand1.addHand(2,die3);
        hand1.addHand(3,die4);
        hand1.addHand(4,die5);

        die1.roll();
        die2.roll();
        die3.roll();
        die4.roll();
        die5.roll();

        hand1.showHand();
        System.out.println(hand1.totalHand());
        System.out.println(hand1.findStraight());
        System.out.println(hand1.fullHouseFound());
        System.out.println(hand1.maxOfAKind());
    }
}

