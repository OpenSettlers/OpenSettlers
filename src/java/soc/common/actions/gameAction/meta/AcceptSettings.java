package soc.common.actions.gameAction.meta;

import soc.common.actions.gameAction.AbstractGameAction;
import soc.common.game.Game;
import soc.common.game.phases.GamePhase;
import soc.common.game.phases.LobbyGamePhase;
import soc.common.game.phases.turnPhase.TurnPhase;
import soc.common.internationalization.I18n;
import soc.common.views.behaviour.gameWidget.GameBehaviour;
import soc.common.views.behaviour.gameWidget.factories.GameBehaviourFactory;
import soc.common.views.behaviour.gameWidget.factories.ReceiveGameBehaviourFactory;
import soc.common.views.behaviour.gameWidget.received.ReceiveGameBehaviour;
import soc.common.views.meta.Meta;
import soc.common.views.widgetsInterface.actions.ActionWidget;
import soc.common.views.widgetsInterface.actions.ActionWidgetFactory;

/*
 * Opponents on the game table in the lobby phase, optionally must say "Ok, let's play!!"
 */
public class AcceptSettings extends AbstractGameAction
{
    private static final long serialVersionUID = 8181882830703417988L;

    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        return false;
    }

    /*
     * Returns true when the game is in the lobby
     * 
     * @see soc.common.actions.gameAction.GameAction#isAllowed(soc.common.game.phases.GamePhase)
     */
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
        // Add the player to the list of players who accepted the settings
        LobbyGamePhase lobbyPhase = (LobbyGamePhase) game.getCurrentPhase();
        if (!lobbyPhase.getPlayersWhoAcceptedSettings().contains(getPlayer()))
        {
            lobbyPhase.getPlayersWhoAcceptedSettings().add(getPlayer());
        }

        super.perform(game);
    }

    @Override
    public String getToDoMessage()
    {
        return I18n.get().actions()
                        .acceptSettingsToDo(player.getUser().getName());
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
