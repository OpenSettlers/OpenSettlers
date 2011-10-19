package org.soc.common.game.actions;

import java.util.*;

import org.soc.common.core.*;
import org.soc.common.core.GenericList.Model;
import org.soc.common.core.GenericList.ModelPresenter;
import org.soc.common.core.GenericList.ModelView;
import org.soc.common.core.GenericList.Models;
import org.soc.common.game.*;
import org.soc.common.game.GamePlayer.GamePlayerImpl;
import org.soc.common.game.actions.Action.ActionPresenter.ActionWidgetFactory;
import org.soc.common.game.actions.Criteria.CriteriaBuilder;
import org.soc.common.game.actions.GameAction.GameActionPresenter.ButtonPresenter.ButtonView;
import org.soc.common.game.actions.GameBehaviour.DefaultReceivedActionInGame;
import org.soc.common.server.actions.*;
import org.soc.common.utils.*;
import org.soc.common.views.widgetsInterface.actions.*;
import org.soc.common.views.widgetsInterface.actions.ActionDetailWidget.ActionDetailWidgetFactory;
import org.soc.common.views.widgetsInterface.main.*;

import com.gwtplatform.dispatch.annotation.*;

import static org.soc.common.game.actions.Criteria.CriteriaBuilder.*;

public interface GameAction extends Action {
  public interface GameActionPresenter<A extends GameAction, V extends ModelView>
          extends
          ModelPresenter<A, V> {
    public interface GameActionView<A extends GameAction> extends ModelView<A> {}

    /** i.e. a button enabling the user to perform the action */
    public interface ButtonPresenter extends GameActionPresenter<GameAction, ButtonView> {
      public interface ButtonView<A extends GameAction> extends GameActionView<A> {
        @GenEvent public class DoAction {}
      }
    }
  }

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
  /** Sets the consecutive number of the game action */
  /** Returns true when the GameAction must be expected only, such as RobPlayer or PlaceRobber */
  public boolean mustExpected();
  public boolean isExpectedQueueType(GameAction actualAction);
  public GameBehaviour received(GameWidget receiveGameBehaviourFactory);
  public GameBehaviour opponentReceived(GameWidget receiveGameBehaviourFactory);;
  public GameBehaviour begin(GameWidget gameBehaviourFactory);
  public GameBehaviour next(GameWidget gameBehaviourFactory);
  public ActionPresenter createPresenter(ActionWidgetFactory actionWidgetFactory);
  public ActionDetailWidget createDetailWidget(ActionDetailWidgetFactory factory);
  public ServerAction createServerAction(GameServerActionFactory factory);

  //public interface GameActionPresenter extends Presenter<>
  public abstract class AbstractGameAction extends AbstractAction implements GameAction {
    protected transient GamePlayer player;
    protected transient String invalidMessage;
    protected transient String toDoMessage;
    protected Turn turnExecuted;
    protected int id;
    private Criteria constraints;

    protected CriteriaBuilder requireThat(Game game) {
      player = game.playerById(sender);
      return require(game)
              .notNull(game, player)
              .action(this).allowedIn(game.phase()).allowedWith(game.status());
    }
    @Override public boolean isValid(Game game) {
      return requireThat(game).areAllMet();
    }
    @Override public Integer id() {
      return id;
    }
    @Override public GameAction setId(Integer ID) {
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
      return "(Not implemented for GameAction " + Models.name(this) + "): " + opponent.user()
              .name();
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
    // TODO: implement in subclasses and return i18n constants
    public String getName() {
      return Util.shortName(this.getClass());
    }
    @Override public boolean isAllowed(GameStatus gameStatus) {
      return gameStatus.blocksGame();
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
    @Override public ActionPresenter createPresenter(ActionWidgetFactory actionWidgetFactory) {
      return null;
    }
    @Override public ActionDetailWidget createDetailWidget(ActionDetailWidgetFactory factory) {
      return null;
    }
    @Override public ServerAction createServerAction(GameServerActionFactory factory) {
      return factory.createDefault(this);
    }
    @Override public GameBehaviour opponentReceived(GameWidget gameWidget) {
      return new DefaultReceivedActionInGame(gameWidget, this);
    }
    @Override public GameBehaviour received(GameWidget gameWidget) {
      return new DefaultReceivedActionInGame(gameWidget, this);
    }
    @Override public GameBehaviour begin(GameWidget gameBehaviourFactory) {
      return null;
    }
    @Override public GameBehaviour next(GameWidget gameBehaviourFactory) {
      return null;
    }
    @Override public org.soc.common.core.GenericList.HasId.IdScope scope() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public Model copy() {
      // TODO Auto-generated method stub
      return null;
    }
  }
}
