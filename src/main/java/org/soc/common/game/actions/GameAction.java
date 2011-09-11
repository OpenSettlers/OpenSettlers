package org.soc.common.game.actions;

import java.util.Date;

import org.soc.common.core.Core;
import org.soc.common.game.Game;
import org.soc.common.game.GamePhase;
import org.soc.common.game.GamePlayer;
import org.soc.common.game.GamePlayer.GamePlayerImpl;
import org.soc.common.game.GameStatus;
import org.soc.common.game.Turn;
import org.soc.common.game.TurnPhase;
import org.soc.common.game.actions.ActionInGame.DefaultOpponentReceivedBehaviour;
import org.soc.common.game.actions.Criterium.Criteria;
import org.soc.common.server.actions.GameServerActionFactory;
import org.soc.common.server.actions.ServerAction;
import org.soc.common.utils.Util;
import org.soc.common.views.widgetsInterface.actions.ActionDetailWidget;
import org.soc.common.views.widgetsInterface.actions.ActionWidget;
import org.soc.common.views.widgetsInterface.actions.ActionDetailWidget.ActionDetailWidgetFactory;
import org.soc.common.views.widgetsInterface.actions.ActionWidget.ActionWidgetFactory;
import org.soc.common.views.widgetsInterface.main.GameWidget;

public interface GameAction extends Action {
  public boolean isAllowed(TurnPhase turnPhase);
  public boolean isAllowed(GamePhase gamePhase);
  public boolean isAllowed(GameStatus gameStatus);
  /** Subclasses should call this method after they have performed their specific implementation (at
   * the end of the method) */
  public void perform(Game game);
  /** Returns true when this action is allowed Since we're deserializing raw send data, we want to
   * check it */
  public boolean isValid(Game game);
  /** Message explaining why this action is in invalid state */
  public String getInvalidMessage();
  /** When an action is required to perform for a user, this message is displayed */
  public String toDoMessage();
  public String toDoMessageOpponent(GamePlayer opponent);
  public GamePlayer player();
  public GameAction setPlayer(GamePlayer player);
  public boolean isServer();
  /** Consecutive number of the game action */
  public int id();
  /** Sets the consecutive number of the game action */
  public GameAction setID(int ID);
  /** Returns true when the GameAction must be expected only, such as RobPlayer or PlaceRobber */
  public boolean mustExpected();
  public boolean isExpectedQueueType(GameAction actualAction);
  public ActionInGame received(GameWidget receiveGameBehaviourFactory);
  public ActionInGame opponentReceived(GameWidget receiveGameBehaviourFactory);;
  public GameBehaviour begin(GameWidget gameBehaviourFactory);
  public GameBehaviour next(GameWidget gameBehaviourFactory);
  public ActionWidget createActionWidget(ActionWidgetFactory actionWidgetFactory);
  public ActionDetailWidget createDetailWidget(ActionDetailWidgetFactory factory);
  public ServerAction createServerAction(GameServerActionFactory factory);

  public abstract class AbstractGameAction extends AbstractAction implements GameAction {
    protected transient GamePlayer player;
    protected transient String invalidMessage;
    protected transient String toDoMessage;
    protected Turn turnExecuted;
    protected int id;
    protected Criteria constraints = new Criteria();

    @Override public int id() {
      return id;
    }
    @Override public GameAction setID(int ID) {
      this.id = ID;
      return null;
    }
    public String getInvalidMessage() {
      return invalidMessage;
    }
    public String message() {
      return message;
    }
    @Override public String toDoMessageOpponent(GamePlayer opponent) {
      return "(Not implemented for GameAction " + name() + "): " + opponent.user().name();
    }
    public GamePlayer player() {
      if (sender == 0 && player == null)
        player = new GamePlayerImpl().setUser(Core.get().getServerUser());
      return player;
    }
    public GameAction setPlayer(GamePlayer player) {
      this.player = player;
      this.sender = player.user().id();
      return this;
    }
    /** Subclasses should call this method after they have performed their specific implementation
     * (at the end of the method) */
    public void perform(Game game) {
      dateTimeExecuted = new Date();
      game.log().addAction(this);
    }
    /** Returns true when this action is allowed Since we're deserializing raw send data, we want to
     * check it */
    @Override public boolean isValid(Game game) {
      if (!isAllowed(game.phase())) {
        invalidMessage = getName() + " is not allowed during " + game.phase().name();
        return false;
      }
      return true;
    }
    // TODO: implement in subclasses and return i18n constants
    public String getName() {
      return Util.shortName(this.getClass());
    }
    @Override public boolean isAllowed(GameStatus gameStatus) {
      if (gameStatus.isGameBlocking()) {
        return false;
      }
      return true;
    }
    @Override public boolean isServer() {
      return sender == 0;
    }
    @Override public boolean mustExpected() {
      return false;
    }
    @Override public boolean isExpectedQueueType(GameAction actualAction) {
      return this.getClass() == actualAction.getClass();
    }
    @Override public ActionWidget createActionWidget(ActionWidgetFactory actionWidgetFactory) {
      return null;
    }
    @Override public ActionDetailWidget createDetailWidget(ActionDetailWidgetFactory factory) {
      return null;
    }
    @Override public ServerAction createServerAction(GameServerActionFactory factory) {
      return factory.createDefaultServerAction(this);
    }
    @Override public ActionInGame opponentReceived(GameWidget gameWidget) {
      return new DefaultOpponentReceivedBehaviour(gameWidget, this);
    }
    @Override public ActionInGame received(GameWidget gameWidget) {
      return new DefaultOpponentReceivedBehaviour(gameWidget, this);
    }
    @Override public GameBehaviour begin(GameWidget gameBehaviourFactory) {
      return null;
    }
    @Override public GameBehaviour next(GameWidget gameBehaviourFactory) {
      return null;
    }
  }
}
