package soc.common.game.phases;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.trading.TradeAction;
import soc.common.actions.gameAction.turns.TurnPhaseEnded;
import soc.common.game.Game;
import soc.common.game.Turn;
import soc.common.game.TurnImpl;
import soc.common.game.phases.turnPhase.BeforeDiceRollTurnPhase;
import soc.common.game.phases.turnPhase.TurnPhase;
import soc.common.game.player.GamePlayer;
import soc.common.views.meta.Icon;
import soc.common.views.meta.IconImpl;
import soc.common.views.meta.Meta;
import soc.common.views.widgetsInterface.main.GamePhaseStatusWidget;
import soc.common.views.widgetsInterface.main.GamePhaseStatusWidgetFactory;
import soc.gwtClient.images.Resources;

public class PlayTurnsGamePhase extends AbstractGamePhase
{
    private static final long serialVersionUID = 7286330376523360257L;
    // Current phase of the player on turn
    private TurnPhase turnPhase = new BeforeDiceRollTurnPhase();
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons()
                        .playTurnsGamePhase16(), Resources.icons()
                        .playTurnsGamePhase32(), Resources.icons()
                        .playTurnsGamePhase48());

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public String getName()
        {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public String getLocalizedName()
        {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public String getDescription()
        {
            // TODO Auto-generated method stub
            return null;
        }

    };

    /** @return the turnPhase */
    public TurnPhase getTurnPhase()
    {
        return turnPhase;
    }

    public void setTurnPhase(TurnPhase newPhase)
    {
        turnPhase = newPhase;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.game.gamePhase.GamePhase#next(soc.common.game.Game)
     */
    @Override
    public GamePhase next(Game game)
    {
        return new EndedGamePhase();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.game.gamePhase.GamePhase#performAction(soc.common.actions.
     * gameAction.GameAction, soc.common.game.Game)
     */
    @Override
    public void performAction(GameAction action, Game game)
    {
        if (turnPhase.isTrading() && !(action instanceof TradeAction)
                        && !(action instanceof TurnPhaseEnded))
        {
            // Non-trading action detected in trading turn phase, advance phase
            TurnPhaseEnded endedTradePhase = (TurnPhaseEnded) new TurnPhaseEnded()
                            .setSender(0);
            game.performAction(endedTradePhase);
        }

        action.perform(game);
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.game.gamePhase.GamePhase#start(soc.common.game.Game)
     */
    @Override
    public void start(Game game)
    {
        game.advanceTurn();
        super.start(game);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.game.gamePhase.GamePhase#isAllowed(soc.common.actions.gameAction
     * .GameAction)
     */
    @Override
    public boolean isAllowed(GameAction action)
    {
        return turnPhase.isAllowed(action);
    }

    @Override
    public String getMessage()
    {
        // TODO fix message
        return turnPhase.getMessage();
    }

    @Override
    public Turn nextTurn(Game game)
    {
        Turn newTurn = null;

        if (game.getCurrentTurn().getID() == 0)
        {
            // First turn in PlayTurnsGamePhase has always ID=1
            newTurn = new TurnImpl(game.getPlayers().get(0), 1, turnPhase);
        } else
        {
            // Determine index of next player
            int nextPlayerIndex = game.getPlayers().indexOf(
                            game.getCurrentTurn().getPlayer()) + 1;
            if (nextPlayerIndex == game.getPlayers().size())
            {
                nextPlayerIndex = 0;
            }

            // Grab the player next on turn
            GamePlayer newPlayerOnTurn = game.getPlayers().get(nextPlayerIndex);

            // Create a new turn with increased ID numer
            newTurn = new TurnImpl(newPlayerOnTurn, game.getCurrentTurn()
                            .getID() + 1, turnPhase);
        }

        return newTurn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.game.gamePhase.AbstractGamePhase#isPlayTurns()
     */
    @Override
    public boolean isPlayTurns()
    {
        return true;
    }

    @Override
    public GamePhaseStatusWidget createGamePhaseStatusWidget(
                    GamePhaseStatusWidgetFactory factory)
    {
        return factory.createPlayTurnsStatusWidget(this);
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }
}