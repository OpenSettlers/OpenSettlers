package soc.common.actions.gameAction.meta;

import soc.common.actions.gameAction.AbstractGameAction;
import soc.common.game.Game;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.LobbyGamePhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.internationalization.I18n;
import soc.common.views.behaviour.gameWidget.GameBehaviour;
import soc.common.views.behaviour.gameWidget.factories.GameBehaviourFactory;
import soc.common.views.behaviour.gameWidget.factories.ReceiveGameBehaviourFactory;
import soc.common.views.behaviour.gameWidget.received.ReceiveGameBehaviour;
import soc.common.views.meta.Meta;
import soc.common.views.widgetsInterface.actions.ActionWidget;
import soc.common.views.widgetsInterface.actions.ActionWidgetFactory;

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
     * soc.common.actions.gameAction.AbstractGameAction#perform(soc.common.game
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
