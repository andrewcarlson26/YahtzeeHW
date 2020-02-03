import java.util.Random;

public class Dice
{
    private int sideUp;

   //Constructor
    public Dice()
    {
        sideUp = -1;
    }

    public void roll() //the equivalent to setSideUp(), (re)rolls the dice
    {
        Random randRef = new Random();
        sideUp = randRef.nextInt(6) + 1;
    }

    //Returns the integer side up on the die
    public int getSideUp()
    {
        return sideUp;
    }

}
