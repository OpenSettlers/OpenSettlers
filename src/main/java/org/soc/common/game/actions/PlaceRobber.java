package org.soc.common.game.actions;

import java.util.*;

import org.soc.common.core.GenericList.Models;
import org.soc.common.game.*;
import org.soc.common.game.actions.Action.ActionPresenter.ActionWidgetFactory;
import org.soc.common.game.actions.TurnAction.AbstractTurnAction;
import org.soc.common.game.board.*;
import org.soc.common.game.hexes.*;
import org.soc.common.internationalization.*;
import org.soc.common.views.widgetsInterface.actions.*;
import org.soc.common.views.widgetsInterface.actions.ActionDetailWidget.ActionDetailWidgetFactory;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.common.views.widgetsInterface.visuals.*;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual.HexVisual;

/*
 * Robber is placed by current player due to 7 roll or Soldier
 * development card play
 */
public class PlaceRobber extends AbstractTurnAction
{
  private HexLocation newLocation;

  public HexLocation getNewLocation() {
    return newLocation;
  }
  public PlaceRobber setNewLocation(HexLocation newLocation) {
    this.newLocation = newLocation;
    return this;
  }
  @Override public boolean isValid(Game game)
  {
    if (!super.isValid(game))
      return false;
    // we need a good location
    if (newLocation == null)
    {
      invalidMessage = "Location can't be null";
      return false;
    }
    // Make sure the player does not put robber or pirate on the edge of the
    // map,
    // which is forbidden
    if (game.board().hexes().isAtEdge(newLocation))
    {
      invalidMessage = "putting robber on the edge is not allowed";
      return false;
    }
    // TODO: check if a previous action included rolling a 7,
    // or playing a soldier development card
    // Player may not leave the robber on the same location
    if (game.robber().location().equals(newLocation))
    {
      invalidMessage = "putting robber back onto same location is not allowed";
      return false;
    }
    Hex hex = game.board().hexes().get(newLocation);
    if (!hex.isRobberPlaceable())
    {
      invalidMessage = "Can't place robber or pirate on a " + Models.name(hex);
      return false;
    }
    return super.isValid(game);
  }
  @Override public void perform(Game game) {
    game.robber().setLocation(newLocation);
    // TODO: fix message
    // _Message = String.Format("{0} put the robber on the {1}",
    // xmlGame.GetPlayer(Sender).XmlPlayer.Name,
    // Location.ToString(xmlGame.Board));
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
    return I.get().actions().placeRobberToDo(player.user().name());
  }
  @Override public boolean mustExpected() {
    return true;
  }
  @Override public ActionPresenter createPresenter(ActionWidgetFactory actionWidgetFactory) {
    return null;
  }
  @Override public ActionDetailWidget createDetailWidget(ActionDetailWidgetFactory factory) {
    return factory.getMoveRobberDetailWidget(this);
  }

  public static class PlaceRobberBehaviour implements ActionOnGameBoard {
    private PlaceRobber placeRobber;
    private List<HexVisual> possibleNewLocations = new ArrayList<HexVisual>();
    private MoveRobberGameBehaviour moveRobberGameBehaviour;

    public PlaceRobberBehaviour(MoveRobberGameBehaviour moveRobberGameBehaviour) {
      super();
      this.moveRobberGameBehaviour = moveRobberGameBehaviour;
      this.placeRobber = moveRobberGameBehaviour.getPlaceRobber();
    }
    @Override public void setNeutral(GameBoardVisual visual)
    {
      for (HexVisual hv : visual.hexVisuals().values()) {
        hv.setEnabled(true);
        hv.setDarkened(false);
      }
    }
    @Override public void start(GameBoardVisual gameVisual) {
      possibleNewLocations.clear();
      for (Hex hex : gameVisual.hexVisuals().keySet()) {
        if (hex.isRobberPlaceable()) {
          if (hex.location().equals(gameVisual.game().robber().location())) {
            // Disable the current location of the robber
            gameVisual.hexVisuals().get(hex).setEnabled(false);
          }
          else { // A possible location to place the robber.
            possibleNewLocations.add(gameVisual.hexVisuals().get(hex));
          }
        } else {
          // Can't place a robber here, darken location
          gameVisual.hexVisuals().get(hex).setDarkened(true);
        }
      }
    }
    @Override public void clicked(PieceVisual pieceVisual, GameBoardVisual board) {
      if (possiblePosition(pieceVisual)) {
        placeRobber.setNewLocation(((HexVisual) pieceVisual).hex().location());
        moveRobberGameBehaviour.pickedRobberSpot(this);
      }
    }
    @Override public void mouseEnter(PieceVisual pieceVisual, GameBoardVisual board) {
      if (possiblePosition(pieceVisual)) {
        pieceVisual.setSelected(true);
      }
    }
    @Override public void mouseOut(PieceVisual pieceVisual, GameBoardVisual board) {
      if (possiblePosition(pieceVisual)) {
        pieceVisual.setSelected(false);
      }
    }
    /** Returns true if given piece visual is a hexvisual showing a landhex, and the robber is not on
     * given hexvisual position */
    private boolean possiblePosition(PieceVisual pieceVisual) {
      HexVisual hexVisual = pieceVisual.hexVisual();
      if (hexVisual != null && possibleNewLocations.contains(hexVisual))
        return true;
      else
        return false;
    }
    @Override public GameAction gameAction() {
      return placeRobber;
    }
  }

  public static class MoveRobberGameBehaviour implements GameBehaviour {
    private PlaceRobber placeRobber;
    private PlaceRobberBehaviour placeRobberBehaviour;
    private GameWidget gameWidget;

    public MoveRobberGameBehaviour(GameWidget gameWidget, PlaceRobber placeRobber) {
      super();
      this.placeRobber = placeRobber;
      this.gameWidget = gameWidget;
      placeRobberBehaviour = new PlaceRobberBehaviour(this);
    }
    @Override public void finish() {}
    @Override public void start(GameWidget gameWidget) {
      gameWidget.blockUI();
      gameWidget.boardVisualWidget().boardVisual().setBehaviour(
              placeRobberBehaviour);
    }
    public void pickedRobberSpot(PlaceRobberBehaviour placeRobberBehaviour) {
      gameWidget.unBlockUI();
      placeRobberBehaviour.setNeutral(gameWidget.boardVisualWidget()
              .boardVisual());
      gameWidget.boardVisualWidget().boardVisual().stopBehaviour();
      gameWidget.doAction(placeRobber);
    }
    public PlaceRobber getPlaceRobber() {
      return placeRobber;
    }
    @Override public boolean endsManually() {
      // TODO Auto-generated method stub
      return false;
    }
  }
}
