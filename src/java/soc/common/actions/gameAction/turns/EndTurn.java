package soc.common.actions.gameAction.turns;

import soc.common.game.Game;
import soc.common.game.developmentCards.DevelopmentCard;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.internationalization.I18n;
import soc.common.server.actions.GameServerActionFactory;
import soc.common.server.actions.ServerAction;
import soc.common.ui.Graphics;
import soc.common.ui.Icon;
import soc.common.ui.IconImpl;
import soc.common.ui.meta.Meta;
import soc.gwtClient.game.behaviour.gameWidget.GameBehaviour;
import soc.gwtClient.game.behaviour.gameWidget.factories.GameBehaviourFactory;
import soc.gwtClient.game.behaviour.gameWidget.factories.ReceiveGameBehaviourFactory;
import soc.gwtClient.game.behaviour.gameWidget.received.ReceiveGameBehaviour;
import soc.gwtClient.game.widgetsInterface.actions.ActionDetailWidgetFactory;
import soc.gwtClient.game.widgetsInterface.actions.ActionWidget;
import soc.gwtClient.game.widgetsInterface.actions.ActionWidgetFactory;
import soc.gwtClient.game.widgetsInterface.generic.ToolTip;
import soc.gwtClient.game.widgetsInterface.playerInfo.ActionDetailWidget;
import soc.gwtClient.images.Resources;

public class EndTurn extends AbstractTurnAction
{
    private static final long serialVersionUID = 3601236566991572901L;
    private static Meta meta = new Meta()
    {
        private Icon icon = new IconImpl(Resources.icons().endTurn(), null,
                null, null);

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
     * soc.common.actions.gameAction.AbstractGameAction#createActionDetailWidget
     * (soc.common.ui.ActionDetailWidgetFactory)
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
     * soc.common.actions.gameAction.AbstractGameAction#createServerAction(soc
     * .common.server.actions.ServerActionFactory)
     */
    @Override
    public ServerAction createServerAction(GameServerActionFactory factory)
    {
        return factory.createEndTurnServerAction(this);
    }

}
