package org.soc.common.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.soc.common.game.ActionPerformedEvent.ActionPerformedHandler;
import org.soc.common.game.ActionPerformedEvent.HasActionPerformedHandlers;
import org.soc.common.game.actions.GameAction;
import org.soc.common.game.actions.RollDice;
import org.soc.common.game.actions.RolledSame;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.gwtplatform.dispatch.annotation.GenEvent;
import com.gwtplatform.dispatch.annotation.Order;

/*
 * List of GameActions which occurred during a game
 */
public interface GameLog extends Iterable<GameAction>, Serializable, HasActionPerformedHandlers
{
  /* Adds an action to this gamelog */
  public void addAction(GameAction inGameAction);
  /* Returns a list of RollDice actions performed during current dicerolling round */
  public List<RollDice> currentRoundRolls(Game game);
  /* Returns the beginning player which won the dice rolling round if this player exists, otherwise
   * returns null */
  public GamePlayer firstPlayerIsDetermined(Game game, int highRoll);

  /* Adds an actionPerformed event listener to this gamelog */
  @GenEvent public class ActionPerformed {
    @Order(0) GameAction performedAction;
  }

  public class GameLogImpl implements GameLog {
    private List<GameAction> actions = new ArrayList<GameAction>();
    private transient SimpleEventBus eventBus = new SimpleEventBus();

    @Override public void addAction(GameAction inGameAction)
    {
      actions.add(inGameAction);
      //      safelyFireEvent(new ActionPerformedEvent(inGameAction));
    }
    public List<RollDice> currentRoundRolls(Game game)
    {
      List<RollDice> result = new ArrayList<RollDice>();
      // We run the like a stack, first to examine is last in the list
      for (int i = actions.size() - 1; i > 0; i--)
      {
        GameAction action = actions.get(i);
        // we break the loop when we encountered a rolledsame action
        if (action instanceof RolledSame)
          break;
        // When we encounter a RollDiceAction, add it to the list
        if (action instanceof RollDice)
          result.add((RollDice) action);
        // We always have maximum of PlayerCount RollDiceAction
        if (result.size() == game.players().size())
          break;
      }
      return result;
    }
    public GamePlayer firstPlayerIsDetermined(Game game, int highRoll)
    {
      List<RollDice> rolledDices = currentRoundRolls(game);
      // Get a list of all highrolls
      List<RollDice> highRolls = new ArrayList<RollDice>();
      for (RollDice rollDice : rolledDices)
      {
        if (rollDice.getDice().getDiceTotal() == highRoll)
          highRolls.add(rollDice);
      }
      // the player with highest dice is determined when we
      // have only one result
      if (highRolls.size() == 1)
      {
        return highRolls.get(0).player();
      }
      else
      {
        // return false
        return null;
      }
    }
    @Override public Iterator<GameAction> iterator()
    {
      return actions.iterator();
    }
    @Override public HandlerRegistration addActionPerformedHandler(
            ActionPerformedHandler handler)
    {
      return eventBus.addHandler(ActionPerformedEvent.getType(), handler);
    }
    @Override public void fireEvent(GwtEvent<?> event) {
      eventBus.fireEvent(event);
    }
  }
}
