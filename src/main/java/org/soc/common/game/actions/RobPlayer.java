package org.soc.common.game.actions;

import java.util.List;

import org.soc.common.game.Game;
import org.soc.common.game.GamePhase;
import org.soc.common.game.GamePlayer;
import org.soc.common.game.Resource;
import org.soc.common.game.TurnPhase;
import org.soc.common.game.actions.TurnAction.AbstractTurnAction;
import org.soc.common.game.board.HexPoint;
import org.soc.common.internationalization.I;
import org.soc.common.server.actions.GameServerActionFactory;
import org.soc.common.server.actions.ServerAction;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.widgetsInterface.actions.ActionWidget;
import org.soc.common.views.widgetsInterface.actions.ActionWidget.ActionWidgetFactory;
import org.soc.common.views.widgetsInterface.main.GameWidget;

/** An opponent is robbed of one resource caused by a 7 roll or a Soldier development card play */
public class RobPlayer extends AbstractTurnAction {
  @Override public Icon icon() {
    return new IconImpl(null, null, null, null);
  }
  @Override public String name() {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public String getLocalizedName() {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public String getDescription() {
    // TODO Auto-generated method stub
    return null;
  }

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
  /** @param robbedPlayer the robbedPlayer to set */
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
    return turnPhase.isBeforeDiceRoll() || turnPhase.isDiceRoll()
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
  @Override public ActionWidget createActionWidget(ActionWidgetFactory actionWidgetFactory) {
    return null;
  }
  @Override public ServerAction createServerAction(GameServerActionFactory factory) {
    return factory.createRobPlayerServerAction(this);
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
  }
}
