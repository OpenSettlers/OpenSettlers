package soc.common.game.developmentCards;

import soc.common.game.Game;
import soc.common.game.IVictoryPointItem;

/*
 * Standard ruleset VictoryPoint development card
 * Playable any time during a players' turn and not immediately played when
 * receiving the card
 */
public class VictoryPoint extends DevelopmentCard implements IVictoryPointItem
{
    /* A victoryPoint development card returns into stock after playing
     * @see soc.common.game.developmentCards.DevelopmentCard#keepInStock()
     */
    @Override
    public boolean keepInStock()
    {
        return true;
    }

    /* A VictoryPoint development card is always valid
     * @see soc.common.game.developmentCards.DevelopmentCard#isValid(soc.common.game.Game)
     */
    @Override
    public boolean isValid(Game game)
    {
        return true;
    }

    @Override
    public int getVictoryPoints()
    {
        return 1;
    }

    /* (non-Javadoc)
     * @see soc.common.game.developmentCards.DevelopmentCard#isHasSummoningSickness()
     */
    @Override
    public boolean isHasSummoningSickness()
    {
        return false;
    }

}
