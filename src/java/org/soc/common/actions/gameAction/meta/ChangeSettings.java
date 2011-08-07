package org.soc.common.actions.gameAction.meta;

import org.soc.common.actions.gameAction.AbstractGameAction;
import org.soc.common.game.Game;
import org.soc.common.game.phases.GamePhase;
import org.soc.common.game.phases.LobbyGamePhase;
import org.soc.common.game.phases.turnPhase.TurnPhase;
import org.soc.common.internationalization.I18n;
import org.soc.common.views.behaviour.gameWidget.GameBehaviour;
import org.soc.common.views.behaviour.gameWidget.factories.GameBehaviourFactory;
import org.soc.common.views.behaviour.gameWidget.factories.ReceiveGameBehaviourFactory;
import org.soc.common.views.behaviour.gameWidget.received.ReceiveGameBehaviour;
import org.soc.common.views.meta.Meta;
import org.soc.common.views.widgetsInterface.actions.ActionWidget;
import org.soc.common.views.widgetsInterface.actions.ActionWidgetFactory;

public class ChangeSettings extends AbstractGameAction
{
    private static final long serialVersionUID = -3965573991881671035L;

    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        return false;
    }

    @Override
    public boolean isAllowed(GamePhase gamePhase)
    {
        return gamePhase.isLobby();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.common.actions.gameAction.AbstractGameAction#perform(org.soc.common.game
     * .Game)
     */
    @Override
    public void perform(Game game)
    {
        // Invalidate all other players
        LobbyGamePhase lobbyPhase = (LobbyGamePhase) game.getCurrentPhase();
        lobbyPhase.resetPlayersWhoAcceptedSettings(getPlayer());

        super.perform(game);
    }

    @Override
    public String getToDoMessage()
    {
        return I18n.get().actions().noToDo();
    }

    @Override
    public ActionWidget createActionWidget(
            ActionWidgetFactory actionWidgetFactory)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameBehaviour getNextActionBehaviour(
            GameBehaviourFactory gameBehaviourFactory)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ReceiveGameBehaviour getOpponentReceiveBehaviour(
            ReceiveGameBehaviourFactory receiveGameBehaviourFactory)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ReceiveGameBehaviour getReceiveBehaviour(
            ReceiveGameBehaviourFactory receiveGameBehaviourFactory)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameBehaviour getSendBehaviour(
            GameBehaviourFactory gameBehaviourFactory)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Meta getMeta()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
