package soc.common.game.dices;

public class StandardDice extends Dice
{
    private int dice1;
    private int dice2;
    
    public int getDice()
    {
        return dice1 + dice2;
    }
    /**
     * @return the dice1
     */
    public int getDice1()
    {
        return dice1;
    }
    /**
     * @param dice1 the dice1 to set
     */
    public StandardDice setDice1(int dice1)
    {
        this.dice1 = dice1;
    
        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    }
    /**
     * @return the dice2
     */
    public int getDice2()
    {
        return dice2;
    }
    /**
     * @param dice2 the dice2 to set
     */
    public StandardDice setDice2(int dice2)
    {
        this.dice2 = dice2;
    
        // Enables fluent interface usage
        // http://en.wikipedia.org/wiki/Fluent_interface
        return this;
    }
}
