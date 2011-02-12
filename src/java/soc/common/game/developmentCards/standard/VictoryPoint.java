package soc.common.game.developmentCards.standard;

import soc.common.game.Game;
import soc.common.game.VictoryPointItem;
import soc.common.game.developmentCards.DevelopmentCard;
import soc.common.game.player.GamePlayer;

/*
 * Standard ruleset VictoryPoint development card
 * Playable any time during a players' turn and not immediately played when
 * receiving the card
 */
public class VictoryPoint extends DevelopmentCard implements VictoryPointItem
{
    /**
     * 
     */
    private static final long serialVersionUID = -7322456488887068608L;

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

    /* VictoryPoint card can be played instantly
     * @see soc.common.game.developmentCards.DevelopmentCard#isHasSummoningSickness()
     */
    @Override
    public boolean isHasSummoningSickness()
    {
        return false;
    }

    /* VictoryPoint cards can be played without limits
     * @see soc.common.game.developmentCards.DevelopmentCard#isLimitOnePerTurn()
     */
    @Override
    public boolean isLimitOnePerTurn()
    {
        return false;
    }

    /* (non-Javadoc)
     * @see soc.common.game.developmentCards.DevelopmentCard#play(soc.common.game.Game, soc.common.game.Player)
     */
    @Override
    public void play(Game game, GamePlayer player)
    {
        player.getVictoryPoints().add(this);
        
        super.play(game, player);
    }

}
