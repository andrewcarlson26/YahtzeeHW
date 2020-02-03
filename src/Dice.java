import java.util.Random;

public class Dice
{
    private int sideUp;

   //Constructor and sets the private variable to a initialized state
    public Dice()
    {
        sideUp = -1;
    }

    /*
     *Randomly sets the sideUp private variable as a number [1-6]
     * no parameters needed
     * returns an integer
     */
    public void roll() //the equivalent to setSideUp(), (re)rolls the dice
    {
        Random randRef = new Random();
        sideUp = randRef.nextInt(6) + 1;
    }

    /*
     * Fetches the sideUp variable
     * no parameters, because it fetches a private variable
     * @return the sideUp private variable which is an integer
     */
    public int getSideUp()
    {
        return sideUp;
    }

}
