package soc.common.actions.gameAction;

import soc.common.actions.Action;
import soc.common.game.Game;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.game.player.GamePlayer;
import soc.common.game.statuses.GameStatus;
import soc.common.server.actions.GameServerActionFactory;
import soc.common.server.actions.ServerAction;
import soc.common.views.behaviour.gameWidget.GameBehaviour;
import soc.common.views.behaviour.gameWidget.factories.GameBehaviourFactory;
import soc.common.views.behaviour.gameWidget.factories.ReceiveGameBehaviourFactory;
import soc.common.views.behaviour.gameWidget.received.ReceiveGameBehaviour;
import soc.common.views.widgetsInterface.actions.ActionDetailWidgetFactory;
import soc.common.views.widgetsInterface.actions.ActionWidget;
import soc.common.views.widgetsInterface.actions.ActionWidgetFactory;
import soc.common.views.widgetsInterface.payerInfo.ActionDetailWidget;

public interface GameAction extends Action
{
    /*
     * Returns true if player is allowed to play this action in given TurnPhase
     */
    public boolean isAllowed(TurnPhase turnPhase);

    /*
     * Returns true if player is allowed to play this action in given GamePhase
     */
    public boolean isAllowed(GamePhase gamePhase);

    /*
     * Returns true if player is allowed to play this action in given GameStatus
     */
    public boolean isAllowed(GameStatus gameStatus);

    /*
     * Subclasses should call this method after they have performed their
     * specific implementation (at the end of the method)
     */
    public void perform(Game game);

    /*
     * Returns true when this action is allowed Since we're deserializing raw
     * send data, we want to check it
     */
    public boolean isValid(Game game);

    /**
     * @return Message explaining why this action is in invalid state
     */
    public String getInvalidMessage();

    /**
     * @return When an action is required to perform for a user, this message is
     *         displayed
     */
    public String getToDoMessage();

    public GamePlayer getPlayer();

    public GameAction setPlayer(GamePlayer player);

    public boolean isServer();

    /*
     * Returns consecutive number of the game action
     */
    public int getID();

    /*
     * Sets the consecutive number of the game action
     */
    public GameAction setID(int ID);

    /*
     * Returns true when the GameAction must be expected only, such as RobPlayer
     * or PlaceRobber
     */
    public boolean mustExpected();

    public boolean isExpectedQueueType(GameAction actualAction);

    public ReceiveGameBehaviour getReceiveBehaviour(
            ReceiveGameBehaviourFactory receiveGameBehaviourFactory);

    public ReceiveGameBehaviour getOpponentReceiveBehaviour(
            ReceiveGameBehaviourFactory receiveGameBehaviourFactory);;

    public GameBehaviour getSendBehaviour(
            GameBehaviourFactory gameBehaviourFactory);

    public GameBehaviour getNextActionBehaviour(
            GameBehaviourFactory gameBehaviourFactory);

    public ActionWidget createActionWidget(
            ActionWidgetFactory actionWidgetFactory);

    public ActionDetailWidget createActionDetailWidget(
            ActionDetailWidgetFactory factory);

    public ServerAction createServerAction(GameServerActionFactory factory);
}
