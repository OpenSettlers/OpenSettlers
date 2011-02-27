package soc.common.board.settings;

/*
 *  Maximum allowed cards in hand when a 7 rolls
 */
public class MaximumCardsInHandWhen7 implements BoardSetting
{
    private int maxCardsInHand = 7;

    @Override
    public void set(BoardSettings boardSettings)
    {
        boardSettings.getMaximumCardsInHandWhenSeven().setMaxCardsInHand(
                maxCardsInHand);
    }

    /**
     * @return the maxCardsInHand
     */
    public int getMaxCardsInHand()
    {
        return maxCardsInHand;
    }

    /**
     * @param maxCardsInHand
     *            the maxCardsInHand to set
     */
    public MaximumCardsInHandWhen7 setMaxCardsInHand(int maxCardsInHand)
    {
        this.maxCardsInHand = maxCardsInHand;
        return this;
    }

}
