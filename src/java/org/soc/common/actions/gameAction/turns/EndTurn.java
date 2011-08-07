package org.soc.common.actions.gameAction.turns;

import org.soc.common.game.Game;
import org.soc.common.game.developmentCards.DevelopmentCard;
import org.soc.common.game.phases.GamePhase;
import org.soc.common.game.phases.turnPhase.TurnPhase;
import org.soc.common.internationalization.I18n;
import org.soc.common.server.gameActions.GameServerActionFactory;
import org.soc.common.server.gameActions.ServerAction;
import org.soc.common.views.behaviour.gameWidget.GameBehaviour;
import org.soc.common.views.behaviour.gameWidget.factories.GameBehaviourFactory;
import org.soc.common.views.behaviour.gameWidget.factories.ReceiveGameBehaviourFactory;
import org.soc.common.views.behaviour.gameWidget.received.ReceiveGameBehaviour;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.meta.Meta;
import org.soc.common.views.widgetsInterface.actions.ActionDetailWidgetFactory;
import org.soc.common.views.widgetsInterface.actions.ActionWidget;
import org.soc.common.views.widgetsInterface.actions.ActionWidgetFactory;
import org.soc.common.views.widgetsInterface.payerInfo.ActionDetailWidget;
import org.soc.gwt.client.images.Resources;

public class EndTurn extends AbstractTurnAction
{
    private static final long serialVersionUID = 3601236566991572901L;
    private static transient Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(null, null, Resources.icons()
                        .endTurn32());

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
        for (DevelopmentCard devCard : player.getDevelopmentCards())
            devCard.setPlayable(true);

        game.advanceTurn();
        game.advanceTurnPhase();

        super.perform(game);
    }

    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        return turnPhase.isBuilding();
    }

    @Override
    public boolean isAllowed(GamePhase gamePhase)
    {
        return gamePhase.isPlayTurns();
    }

    @Override
    public String getToDoMessage()
    {
        return I18n.get().actions().noToDo();
    }

    @Override
    public Meta getMeta()
    {
        return meta;
    }

    @Override
    public ActionWidget createActionWidget(
                    ActionWidgetFactory actionWidgetFactory)
    {
        return actionWidgetFactory.createEndTurnWidget();
    }

    @Override
    public GameBehaviour getNextActionBehaviour(
                    GameBehaviourFactory gameBehaviourFactory)
    {
        return gameBehaviourFactory.createEndTurnBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getOpponentReceiveBehaviour(
                    ReceiveGameBehaviourFactory receiveGameBehaviourFactory)
    {
        return receiveGameBehaviourFactory.createEndTurnBehaviour(this);
    }

    @Override
    public ReceiveGameBehaviour getReceiveBehaviour(
                    ReceiveGameBehaviourFactory receiveGameBehaviourFactory)
    {
        return receiveGameBehaviourFactory.createEndTurnBehaviour(this);
    }

    @Override
    public GameBehaviour getSendBehaviour(
                    GameBehaviourFactory gameBehaviourFactory)
    {
        return gameBehaviourFactory.createEndTurnBehaviour(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.common.actions.gameAction.AbstractGameAction#createActionDetailWidget
     * (org.soc.common.ui.ActionDetailWidgetFactory)
     */
    @Override
    public ActionDetailWidget createActionDetailWidget(
                    ActionDetailWidgetFactory factory)
    {
        return factory.getEndTurnDetailWidget(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.common.actions.gameAction.AbstractGameAction#createServerAction(org.soc
     * .common.server.actions.ServerActionFactory)
     */
    @Override
    public ServerAction createServerAction(GameServerActionFactory factory)
    {
        return factory.createEndTurnServerAction(this);
    }

}
