package org.soc.common.actions.gameAction.standard;

import org.soc.common.actions.gameAction.GameAction;
import org.soc.common.actions.gameAction.turns.AbstractTurnAction;
import org.soc.common.actions.gameAction.turns.GamePhaseHasEnded;
import org.soc.common.game.Game;
import org.soc.common.game.phases.GamePhase;
import org.soc.common.game.phases.turnPhase.TurnPhase;
import org.soc.common.internationalization.I18n;
import org.soc.common.views.behaviour.gameWidget.GameBehaviour;
import org.soc.common.views.behaviour.gameWidget.factories.GameBehaviourFactory;
import org.soc.common.views.behaviour.gameWidget.factories.ReceiveGameBehaviourFactory;
import org.soc.common.views.behaviour.gameWidget.received.ReceiveGameBehaviour;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.meta.Meta;
import org.soc.common.views.widgetsInterface.actions.ActionWidget;
import org.soc.common.views.widgetsInterface.actions.ActionWidgetFactory;
import org.soc.gwt.client.images.Resources;

public class ClaimVictory extends AbstractTurnAction
{
    private static final long serialVersionUID = 7906062762366374296L;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().claimVictory16(),
                        Resources.icons().claimVictory32(), Resources.icons()
                                        .claimVictory48());

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

    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        return true;
    }

    @Override
    public boolean isAllowed(GamePhase gamePhase)
    {
        return gamePhase.isPlayTurns();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.common.actions.gameAction.AbstractGameAction#isValid(org.soc.common.game
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
                        .getBoardSettings().getVpToWin().getVpToWin())
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
     * org.soc.common.actions.gameAction.turnActions.AbstractTurnAction#perform(
     * org.soc.common.game.Game)
     */
    @Override
    public void perform(Game game)
    {
        game.getActionsQueue()
                        .enqueue((GameAction) new GamePhaseHasEnded()
                                        .setSender(0),
                                        true);

        super.perform(game);
    }

    @Override
    public String getToDoMessage()
    {
        return I18n.get().actions()
                        .claimVictoryToDo(player.getUser().getName());
    }

    @Override
    public ActionWidget createActionWidget(
                    ActionWidgetFactory actionWidgetFactory)
    {
        return actionWidgetFactory.createClaimVictoryWidget();
    }

    @Override
    public GameBehaviour getNextActionBehaviour(
                    GameBehaviourFactory gameBehaviourFactory)
    {
        return gameBehaviourFactory.createClaimVictoryBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getOpponentReceiveBehaviour(
                    ReceiveGameBehaviourFactory receiveGameBehaviourFactory)
    {
        return receiveGameBehaviourFactory.createClaimVictoryBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getReceiveBehaviour(
                    ReceiveGameBehaviourFactory receiveGameBehaviourFactory)
    {
        return receiveGameBehaviourFactory.createClaimVictoryBehaviour(this);
    }

    @Override
    public GameBehaviour getSendBehaviour(
                    GameBehaviourFactory gameBehaviourFactory)
    {
        return gameBehaviourFactory.createClaimVictoryBehaviour(this);
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }
}
