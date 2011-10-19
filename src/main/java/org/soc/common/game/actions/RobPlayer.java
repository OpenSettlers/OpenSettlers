package org.soc.common.game.actions;

import java.util.*;

import org.soc.common.game.*;
import org.soc.common.game.actions.Action.ActionPresenter.ActionWidgetFactory;
import org.soc.common.game.actions.TurnAction.AbstractTurnAction;
import org.soc.common.game.board.*;
import org.soc.common.internationalization.*;
import org.soc.common.server.actions.*;
import org.soc.common.views.widgetsInterface.main.*;

/** An opponent is robbed of one resource caused by a 7 roll or a Soldier development card play */
public class RobPlayer extends AbstractTurnAction {
  private int victimID = 0;
  private GamePlayer robbedPlayer;
  private Resource stolenResource;

  public Resource getStolenResource() {
    return stolenResource;
  }
  public RobPlayer setStolenResource(Resource stolenResource) {
    this.stolenResource = stolenResource;
    return this;
  }
  public GamePlayer getRobbedPlayer() {
    return robbedPlayer;
  }
  public RobPlayer setRobbedPlayer(GamePlayer robbedPlayer) {
    this.robbedPlayer = robbedPlayer;
    if (robbedPlayer == null) {
      victimID = 0;
    } else {
      victimID = robbedPlayer.user().id();
    }
    return this;
  }
  public int getVictimID() {
    return victimID;
  }
  @Override public boolean isValid(Game game) {
    if (!super.isValid(game))
      return false;
    if (victimID != 0) {
      robbedPlayer = game.playerById(victimID);
    }
    // Checks to do if a player is robbed
    if (robbedPlayer != null) {
      // Check if the robbed player has a town or city on one of the 6
      // points
      List<HexPoint> possiblePoints = game.robber().location()
              .neighbourPoints();
      boolean containsTownOrCity = false;
      for (HexPoint point : possiblePoints) {
        if (robbedPlayer.pointPieces().contains(point))
        {
          containsTownOrCity = true;
          break;
        }
      }
      if (!containsTownOrCity) {
        invalidMessage = "Robbed opponent does not have a town or city at Hexlocation"
                + game.robber().toString();
        return false;
      }
      if (!robbedPlayer.resources().hasTradeableResources()) {
        invalidMessage = "The player should have a resource to rob";
        return false;
      }
    }
    return true;
  }
  /** Removes the stolen resource from the robbed player and adds it to this player */
  @Override public void perform(Game game) {
    player = game.playerById(sender);
    if (victimID != 0) {
      robbedPlayer = game.playerById(victimID);
    }
    if (robbedPlayer != null) {
      player.resources().add(stolenResource);
      robbedPlayer.resources().remove(stolenResource);
      message = player.user().name() + " stole one "
              + stolenResource.toString() + " from "
              + robbedPlayer.user().name();
    } else {
      message = player.user().name()
              + " stole nothing! How refreshing";
    }
    super.perform(game);
  }
  @Override public boolean isAllowed(TurnPhase turnPhase) {
    return turnPhase.isBeforeDiceRoll()
            || turnPhase.isDiceRoll()
            || turnPhase.isBuilding();
  }
  @Override public boolean isAllowed(GamePhase gamePhase) {
    return gamePhase.isPlayTurns();
  }
  @Override public String toDoMessage() {
    return I.get().actions().robPlayerToDo(player.user().name());
  }
  @Override public boolean mustExpected() {
    return true;
  }
  @Override public ActionPresenter createPresenter(ActionWidgetFactory actionWidgetFactory) {
    return null;
  }
  @Override public ServerAction createServerAction(GameServerActionFactory factory) {
    return factory.createRobPlayer(this);
  }

  public static class RobPlayerGameBehaviour implements GameBehaviour {
    private GameWidget gameWidget;

    public RobPlayerGameBehaviour(GameWidget gameWidget) {
      super();
      this.gameWidget = gameWidget;
    }
    @Override public void finish() {}
    @Override public void start(GameWidget gameWidget) {
      gameWidget.blockUI();
      gameWidget.stealCardWidget().update(this);
    }
    public void robbedPlayer(RobPlayer robplayer) {
      gameWidget.unBlockUI();
      gameWidget.doAction(robplayer);
    }
    @Override public boolean endsManually() {
      // TODO Auto-generated method stub
      return false;
    }
  }
}
