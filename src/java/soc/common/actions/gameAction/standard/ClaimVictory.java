package soc.common.actions.gameAction.standard;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.GamePhaseHasEnded;
import soc.common.actions.gameAction.turns.AbstractTurnAction;
import soc.common.core.Core;
import soc.common.game.Game;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.PlayTurnsGamePhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.game.player.GamePlayer;
import soc.common.internationalization.I18n;
import soc.common.ui.Graphics;
import soc.common.ui.Icon;
import soc.common.ui.IconImpl;
import soc.common.ui.ToolTip;
import soc.common.ui.meta.Meta;
import soc.gwtClient.game.behaviour.gameBoard.received.ReceiveGameBehaviour;
import soc.gwtClient.game.behaviour.gameWidget.GameBehaviour;
import soc.gwtClient.game.widgetsInterface.actions.ActionWidget;
import soc.gwtClient.images.Resources;

public class ClaimVictory extends AbstractTurnAction
{
    private static final long serialVersionUID = 7906062762366374296L;
    private static Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().claimVictory(),
                null, null, null);

        @Override
        public Icon icon()
        {
            return icon;
        }

        @Override
        public Graphics graphics()
        {
            // TODO Auto-generated method stub
            return null;
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

        @Override
        public ToolTip createToolTip()
        {
            // TODO Auto-generated method stub
            return null;
        }
    };

    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        return true;
    }

    @Override
    public boolean isAllowed(GamePhase gamePhase)
    {
        return gamePhase instanceof PlayTurnsGamePhase;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.actions.gameAction.AbstractGameAction#isValid(soc.common.game
     * .Game)
     */
    @Override
    public boolean isValid(Game game)
    {
        if (!super.isValid(game))
        {
            return false;
        }

        if (player.getVictoryPoints().getTotalPoints() < game.getBoard()
                .getBoardSettings().getVpToWin())
        {
            invalidMessage = "Player does not have enough victory points to win";
            return false;
        }

        return true;
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
        game.getActionsQueue().enqueue(
                (GameAction) new GamePhaseHasEnded().setSender(0), true);

        super.perform(game);
    }

    @Override
    public String getToDoMessage()
    {
        return I18n.get().actions()
                .claimVictoryToDo(player.getUser().getName());
    }

    @Override
    public ActionWidget createActionWidget(GamePlayer player)
    {
        return Core.get().getClientFactory().getActionWidgetFactory(player)
                .createBuyDevelopmentCardWidget();
    }

    @Override
    public GameBehaviour getNextActionBehaviour()
    {
        return Core.get().getClientFactory().getBehaviourFactory()
                .getNextActionBehaviourFactory().createClaimVictoryBehaviour(
                        this);
    }

    @Override
    public ReceiveGameBehaviour getOpponentReceiveBehaviour()
    {
        return Core.get().getClientFactory().getBehaviourFactory()
                .getOpponentReceiveBehaviourFactory()
                .createClaimVictoryBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getReceiveBehaviour()
    {
        return Core.get().getClientFactory().getBehaviourFactory()
                .getReceiveBehaviourFactory().createClaimVictoryBehaviour(this);
    }

    @Override
    public GameBehaviour getSendBehaviour()
    {
        return Core.get().getClientFactory().getBehaviourFactory()
                .getSendBehaviourFactory().createClaimVictoryBehaviour(this);
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }
}
