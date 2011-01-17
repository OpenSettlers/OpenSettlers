package soc.common.actions.gameAction.turnActions.standard;

import soc.common.actions.gameAction.turnActions.AbstractTurnAction;
import soc.common.board.resources.ResourceList;
import soc.common.game.Game;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.PlayTurnsGamePhase;
import soc.common.game.gamePhase.turnPhase.TradingTurnPhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.game.player.GamePlayer;
import soc.common.internationalization.I18n;

public class TradePlayer extends AbstractTurnAction
{
    private static final long serialVersionUID = 1492739389253213310L;
    private ResourceList offeredResources = new ResourceList();
    private ResourceList requestedResources = new ResourceList();
    private TradeOffer originatingOffer;
    private GamePlayer tradeOpponent;

    public TradeOffer getOriginatingOffer()
    {
        return originatingOffer;
    }

    public ResourceList getOfferedResources()
    {
        return offeredResources;
    }

    public ResourceList getRequestedResources()
    {
        return requestedResources;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.actions.gameAction.turnActions.AbstractTurnAction#perform(
     * soc.common.game.Game)
     */
    @Override
    public void perform(Game game)
    {

        tradeOpponent.getResources().moveTo(player.getResources(),
                requestedResources);
        player.getResources().moveTo(tradeOpponent.getResources(),
                offeredResources);

        // TODO: fix message

        super.perform(game);
    }

    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        return turnPhase instanceof TradingTurnPhase;
    }

    @Override
    public boolean isAllowed(GamePhase gamePhase)
    {
        return gamePhase instanceof PlayTurnsGamePhase;
    }

    @Override
    public String getToDoMessage()
    {
        return I18n.get().actions().noToDo();
    }

}
