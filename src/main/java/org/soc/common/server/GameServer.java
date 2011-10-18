package org.soc.common.server;

import java.util.ArrayList;
import java.util.List;

import org.soc.common.game.ActionsQueue;
import org.soc.common.game.Game;
import org.soc.common.game.Random;
import org.soc.common.game.Random.ClientRandom;
import org.soc.common.game.actions.GameAction;
import org.soc.common.game.actions.MessageFromServer;
import org.soc.common.game.bots.BotPrincipal;
import org.soc.common.game.bots.BotPrincipal.BotPrincipalImpl;
import org.soc.common.server.actions.GameServerActionFactory;
import org.soc.common.server.actions.GameServerActionFactory.ServerActionFactory;
import org.soc.common.server.actions.ServerAction;

import com.google.gwt.user.client.Timer;

public interface GameServer extends Server
{
  public JoinResult join(UserCredentialsDto credentials);
  public void sendAction(GameAction action);
  public void leave();
  public Random random();
  public Game game();
  public ServerActionFactory createActionFactory();
  public void startGame(Game game);
  public void setBotTurnHandled(boolean handled);
  public boolean hasQueuedBotActions();

  public interface GameServerCallback {
    public void receive(GameAction gameAction);
  }

  /* Abstracted implementation of a gameserver. Should be able to be used locally as hotseat
   * implementation, as well as a thread synchronized remote server deployment. */
  public abstract class AbstractGameServer implements GameServer
  {
    protected GameServerCallback callback;
    protected Game game;
    protected GameServerActionFactory serverActionFactory;
    protected Random random;
    protected BotPrincipal botPrincipal;
    protected boolean botTurnHandled = false;
    protected List<GameAction> botActionQueue = new ArrayList<GameAction>();

    public AbstractGameServer()
    {
      serverActionFactory = createActionFactory();
    }
    public org.soc.common.game.Random random() {
      return random;
    }
    @Override public Game game()
    {
      return game;
    }
    /* Receives an action from a player */
    @Override public void sendAction(GameAction action)
    {
      if (action != null)
      {
        // When the action comes from a bot, schedule it to add a delay
        // If there are scheduled actions, ad this action to the schedule.
        if ((action.player().bot() != null)
                && !botActionQueue.contains(action))
        {
          botActionQueue.add(action);
          return;
        }
        if (botActionQueue.contains(action))
          botActionQueue.remove(action);
        if (!checkAllowedAndValid(action))
          return;
        GameAction expectedAction = null;
        if (game.actionsQueue().size() > 0)
        {
          ActionsQueue ac = game.actionsQueue();
          expectedAction = ac.findExpected(action);
          if (expectedAction == null)
          {
            notifyUnexpected(action);
            return;
          }
        }
        // Get associated server side action
        ServerAction serverAction = action
                .createServerAction(serverActionFactory);
        // Update state of our game instance by performing the action
        serverAction.execute();
        // send the action to all the players 
        callback.receive(serverAction.action());
        GameAction possibleNextServerAction = game.actionsQueue().peek();
        // Check if the action enqueued a server action, if so: execute it
        // right away
        if (possibleNextServerAction != null
                && possibleNextServerAction.isServer())
        {
          sendAction(possibleNextServerAction);
        }
        // If bot is on turn, pass control
        if (game.hasBots())
          handleBotActions();
      }
    }
    private void handleBotActions()
    {
      // If bots should perform actions, call the bot principal to let the
      // bots handle them
      if (game.actionsQueue().hasPendingBotActions())
        botPrincipal.handleActionsQueue();
      // Hand turn execution over to the bot currently on turn
      else if (game.turn().player().bot() != null
              && !hasQueuedBotActions())
        botPrincipal.handleActions();
    }
    private boolean checkAllowedAndValid(GameAction action)
    {
      if (!game.isAllowed(action))
      {
        notifyNotAllowed(action);
        return false;
      }
      if (!action.isValid(game))
      {
        notifyInvalid(action);
        return false;
      }
      return true;
    }
    private void notifyNotAllowed(GameAction action)
    {
      callback.receive((GameAction) new MessageFromServer()
              .setServerMessage("Action not allowed!"));
    }
    private void notifyInvalid(GameAction action)
    {
      callback.receive((GameAction) new MessageFromServer().setServerMessage(
              "Invalid action! \r\n Reason: " + action.getInvalidMessage())
              .setSender(0));
    }
    private void notifyUnexpected(GameAction action)
    {
      // Grab the expected action
      GameAction expected = game.actionsQueue().peek();
      // Notify we did not expect current action
      // TODO 200 fix message
      callback.receive((GameAction) new MessageFromServer().setServerMessage(
              "Invalid action! \r\n Reason: Expected "
                      + expected.getClass().toString()
                      + " from player "
                      + game.playerById(expected.senderId()).user()
                              .name()
                      + ", but instead got "
                      + action.getClass().toString()
                      + " from player "
                      + game.playerById(action.senderId()).user()
                              .name()).setSender(0));
    }
    @Override public boolean hasQueuedBotActions()
    {
      return botActionQueue.size() > 0;
    }
  }

  /** Server running n the browser to provide hotseat and playing versus bots possibility */
  public class HotSeatServer extends AbstractGameServer {
    int botActionDelay = 2000; // ms
    private Timer timer = new Timer() {
      @Override public void run() {
        processNextBotAction();
      }
    };

    public HotSeatServer(GameServerCallback callback) {
      super();
      this.callback = callback;
      game = new Game();
      random = new ClientRandom();
      timer.scheduleRepeating(botActionDelay);
    }
    private void processNextBotAction() {
      if (botActionQueue.size() > 0) {
        GameAction botAction = botActionQueue.get(0);
        super.sendAction(botAction);
      }
    }
    @Override public JoinResult join(UserCredentialsDto credentials) {
      return null;
    }
    @Override public void leave() {}
    @Override public org.soc.common.game.Random random() {
      return random;
    }
    @Override public ServerActionFactory createActionFactory() {
      return new ServerActionFactory(this);
    }
    @Override public void startGame(Game game) {
      this.game = game;
      botPrincipal = new BotPrincipalImpl(this);
    }
    @Override public void setBotTurnHandled(boolean handled) {
      botTurnHandled = handled;
    }
  }
}
