package org.soc.common.game;

import java.io.*;

import org.soc.common.core.GenericList.HasId;
import org.soc.common.core.GenericList.Model;
import org.soc.common.core.OpenZettlers.OsModel;
import org.soc.common.core.Props.PropertyList.PropertyTypeList;
import org.soc.common.core.property.Properties.Description;
import org.soc.common.core.property.Properties.Name;
import org.soc.common.core.property.*;
import org.soc.common.game.actions.*;
import org.soc.common.utils.*;
import org.soc.common.views.meta.*;
import org.soc.gwt.client.images.*;

public interface TurnPhase extends Serializable, OsModel<Integer> {
  public TurnPhase next();
  public TurnPhase processAction(GameAction action, Game game);
  public boolean isAllowed(GameAction action);
  public boolean isBeforeDiceRoll();
  public boolean isDiceRoll();
  public boolean isTrading();
  public boolean isBuilding();
  public String getMessage();

  /** Default impl for a TurnPhase to save some code */
  public abstract class AbstractTurnPhase implements TurnPhase {
    public boolean isAllowed(GameAction action) {
      return action.isAllowed(this);
    }
    @Override public Name name() {
      return new Name.Impl(Util.shortName(this.getClass()));
    }
    public boolean isBeforeDiceRoll() {
      return false;
    }
    public boolean isDiceRoll() {
      return false;
    }
    public boolean isTrading() {
      return false;
    }
    public boolean isBuilding() {
      return false;
    }
    @Override public Property getProp(Property type) {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public Integer id() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public HasId setId(Integer id) {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public org.soc.common.core.GenericList.HasId.IdScope scope() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public PropertyTypeList properties() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public Model copy() {
      // TODO Auto-generated method stub
      return null;
    }
  }

  public class BeforeDiceRollTurnPhase extends AbstractTurnPhase {
    @Override public Icon icon() {
      return new IconImpl(R.icons()
              .beforeDiceRollTurnPhase16(), R.icons()
              .beforeDiceRollTurnPhase32(), R.icons()
              .beforeDiceRollTurnPhase48());
    }
    @Override public Name name() {
      return null;
    }
    @Override public Description description() {
      return null;
    }
    @Override public TurnPhase next() {
      return new RollDiceTurnPhase();
    }
    @Override public TurnPhase processAction(GameAction action, Game game) {
      return this;
      // return super.processAction(action, game);
    }
    @Override public String getMessage() {
      // TODO fix message
      return "Start turn";
    }
    @Override public boolean isBeforeDiceRoll() {
      return true;
    }
  }

  public class BuildingTurnPhase extends AbstractTurnPhase {
    private TradingTurnPhase tradingTurnPhase;

    @Override public Icon icon() {
      return new IconImpl(R.icons()
              .buildingTurnPhase16(), R.icons()
              .buildingTurnPhase32(), R.icons()
              .buildingTurnPhase48());
    }
    @Override public Name name() {
      return null;
    }
    @Override public Description description() {
      return null;
    }
    public BuildingTurnPhase() {}
    public BuildingTurnPhase(TradingTurnPhase tradingTurnPhase) {
      this.tradingTurnPhase = tradingTurnPhase;
    }
    @Override public TurnPhase next() {
      return new BeforeDiceRollTurnPhase();
    }
    @Override public TurnPhase processAction(GameAction action, Game game) {
      if (action.isAllowed(this)) {
        action.perform(game);
        // EndTurn endTurn = action as EndTurnAction;
        if (action instanceof EndTurn) {
          return new BeforeDiceRollTurnPhase();
        }
        // Players may build anything they can pay for in a turnphase
        return this;
      } else {
        // Look if the action is allowed in the tradingPhase, and if we may
        // go back
        // to that phase, perform the action and return the phase
        if (action.isAllowed(tradingTurnPhase)) {
          tradingTurnPhase.processAction(action, game);
          return tradingTurnPhase;
        } else {
          throw new RuntimeException("We should not reach this code");
        }
      }
    }
    @Override public boolean isAllowed(GameAction action) {
      if (action.isAllowed(this)) {
        return true;
      } else {
        // TODO: add chck for game setting
        return action.isAllowed(tradingTurnPhase);
      }
    }
    @Override public String getMessage() {
      // TODO fix message
      return "Build or buy stuff";
    }
    @Override public boolean isBuilding() {
      return true;
    }
  }

  public class RollDiceTurnPhase extends AbstractTurnPhase {
    @Override public Icon icon() {
      return new IconImpl(R.icons()
              .rollDiceTurnPhase16(), R.icons()
              .rollDiceTurnPhase32(), R.icons()
              .rollDiceTurnPhase48());
    }
    @Override public Name name() {
      return null;
    }
    @Override public Description description() {
      return null;
    }
    @Override public TurnPhase next() {
      return new TradingTurnPhase();
    }
    @Override public boolean isAllowed(GameAction action) {
      return super.isAllowed(action);
    }
    @Override public TurnPhase processAction(GameAction action, Game game) {
      if (action.isAllowed(this)) {
        if (action instanceof RollDice) {
          RollDice rollDice = (RollDice) action;
          rollDice.perform(game);
          // When a 7 is rolled, enqueue every player required to loose
          // cards to do so
          if (rollDice.getDice().getDiceTotal() == 7) {
            // Add each player required to loose cards to the queue
            for (@SuppressWarnings("unused")
            int i : rollDice.getLooserPlayers()) {
              game.actionsQueue().enqueue(new BuildRoad(), true
                      // TODO: port to java
                      // new LooseCardsAction()
                      // .setPlayer(game.getPlayer(i));
                      );
            }
            // Expect player to place robber/pirate and rob a player
            /* TODO: port to java game.ActionsQueue.Enqueue(new PlaceRobberPirateAction() {
             * GamePlayer = action.GamePlayer }); game.ActionsQueue.Enqueue(new RobPlayerAction() {
             * GamePlayer = action.GamePlayer }); */
            // We have actions to be done, we should stay in this phase
            return this;
          }
          // Any other number has been rolled.
          // Proceed to trading phase
          if (game.actionsQueue().size() == 0) {
            return new TradingTurnPhase();
          } else {
            return this;
          }
        }
        /* TODO: port to java RobPlayerAction robPlayer = action as RobPlayerAction; if (robPlayer
         * != null) { robPlayer.PerformTurnAction(game);
         * 
         * // When finished robbing, advance phase to trading return this; } // perform the action
         * action.PerformTurnAction(game); EndTurnAction endTurn = action as EndTurnAction; if
         * (endTurn != null) { return new BuildTurnPhase(null); } */
        // Return current state
        return this;
      } else {
        // Action is not allowed in rollDice phase. Check if it is allowed in
        // subsequent phases,
        // then return that phase
        TradingTurnPhase trading = new TradingTurnPhase();
        BuildingTurnPhase building = new BuildingTurnPhase(trading);
        if (action.isAllowed(trading)) {
          return trading;
        }
        if (action.isAllowed(building)) {
          return building;
        }
        return null;
      }
    }
    @Override public String getMessage() {
      // TODO fix message
      return "Rolling dice...";
    }
    @Override public boolean isDiceRoll() {
      return true;
    }
  }

  public class TradingTurnPhase extends AbstractTurnPhase {
    private BuildingTurnPhase buildPhase;

    @Override public Icon icon() {
      return new IconImpl(
              R.icons().tradingTurnPhase16(), R
                      .icons().tradingTurnPhase32(),
              R.icons().tradingTurnPhase48());
    }
    @Override public Name name() {
      return null;
    }
    @Override public Description description() {
      return null;
    }
    public TradingTurnPhase() {
      buildPhase = new BuildingTurnPhase(this);
    }
    @Override public boolean isAllowed(GameAction action) {
      if (action.isAllowed(this))
        return true;
      else
        return action.isAllowed(buildPhase);
    }
    @Override public TurnPhase next() {
      return buildPhase;
    }
    @Override public TurnPhase processAction(GameAction action, Game game) {
      // If the action is allowed to be executed in this phase, do it
      if (action.isAllowed(this)) {
        action.perform(game);
        return this;
      } else {
        // If action is not allowed to execute, check if buildphase allows it.
        // If so, move to
        // buildphase
        if (buildPhase.isAllowed(action)) {
          buildPhase.processAction(action, game);
          return buildPhase;
        }
        throw new RuntimeException("Should not reach this code");
      }
    }
    @Override public String getMessage() {
      // TODO fix message
      return "Trade with opponents";
    }
    @Override public boolean isTrading() {
      return true;
    }
  }
}