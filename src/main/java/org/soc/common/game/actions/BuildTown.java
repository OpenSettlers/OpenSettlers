package org.soc.common.game.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.soc.common.game.Game;
import org.soc.common.game.GamePhase;
import org.soc.common.game.GamePlayer;
import org.soc.common.game.Port;
import org.soc.common.game.ResourceList;
import org.soc.common.game.TurnPhase;
import org.soc.common.game.actions.ActionOnGameBoard.BuildPointOnBoard;
import org.soc.common.game.actions.GameBehaviour.GameBehaviourCallback;
import org.soc.common.game.actions.GameBehaviour.TradeFirst;
import org.soc.common.game.actions.TurnAction.AbstractTurnAction;
import org.soc.common.game.board.GraphPoint;
import org.soc.common.game.board.HexLocation;
import org.soc.common.game.board.HexPoint;
import org.soc.common.game.board.HexSide;
import org.soc.common.game.hexes.Hex;
import org.soc.common.game.pieces.PointPieceList;
import org.soc.common.game.pieces.Town;
import org.soc.common.internationalization.I;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.widgetsInterface.actions.ActionDetailWidget;
import org.soc.common.views.widgetsInterface.actions.ActionWidget;
import org.soc.common.views.widgetsInterface.actions.ActionDetailWidget.ActionDetailWidgetFactory;
import org.soc.common.views.widgetsInterface.actions.ActionWidget.ActionWidgetFactory;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.common.views.widgetsInterface.visuals.GameBoardVisual;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual.PointVisual;
import org.soc.gwt.client.images.R;

public class BuildTown extends AbstractTurnAction {
  @Override public Icon icon() {
    return new IconImpl(R.icons().town16(), R
            .icons().town32(), R.icons().town48());
  }
  @Override public String getLocalizedName() {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public String getDescription() {
    // TODO Auto-generated method stub
    return null;
  }

  private HexPoint pointLocation;

  public HexPoint getPointLocation() {
    return pointLocation;
  }
  public BuildTown setPointLocation(HexPoint pointLocation) {
    this.pointLocation = pointLocation;
    return this;
  }
  @Override public boolean isValid(Game game) {
    if (!super.isValid(game))
      return false;
    // we need at least an instance of the new place
    if (pointLocation == null) {
      invalidMessage = "Location cannot be null";
      return false;
    }
    if (game.allPointPieces().contains(pointLocation)) {
      invalidMessage = "The spot and its neighbours is already used by anyone";
      return false;
    }
    if (!game.phase().isInitialPlacement()) {
      boolean hasNeighbour = false;
      PointPieceList allPointPieces = game.allPointPieces();
      for (HexPoint point : pointLocation.getNeighbours())
        if (allPointPieces.contains(point))
          hasNeighbour = true;
      if (hasNeighbour)
      {
        invalidMessage = "A neighbouring pointpiece was found. You can build here";
        return false;
      }
    }
    // player should have a ship or road at some neighbour
    if (!(game.phase().isInitialPlacement())) {
      boolean contains = true;
      for (HexSide neighbour : pointLocation.neighbourSides()) {
        if (player.sidePieces().contains(neighbour)) {
          contains = false;
          break;
        }
      }
      if (!contains) {
        invalidMessage = "No neighbouring ship or road found";
        return false;
      }
    }
    // check if location is suitable (hexpoint neighbours can't be
    // already built on)
    // check if location is a valid one to built on
    // (contains at least a landhex)
    // couldnt find a neighbour, assume invalid state
    return true;
  }
  @Override public void perform(Game game) {
    // update town management
    Town town = (Town) player.stock().towns().get(0);
    town.setPoint(pointLocation);
    game.addPiece(town, player);
    // remove players' resources and put them in the bank
    if (game.phase().isPlayTurns())
      player.resources().moveTo(game.bank(), town.cost());
    if (game.phase().isInitialPlacement() && player.pointPieces().size() == 2) {
      // player gets resources in neighbouring hexes
      ResourceList resourcesFromPlacement = new ResourceList();
      for (HexLocation loc : pointLocation.hexLocations()) {
        Hex hex = game.board().hexes().get(loc);
        if (hex.producesResource())
          resourcesFromPlacement.add(hex.resource());
      }
      player.resources().swapResourcesFrom(resourcesFromPlacement,
              game.bank());
    }
    // Check if the town is built on a port, if so, add port
    // to list of ports of the player
    // Get a list of ports
    List<Port> ports = new ArrayList<Port>();
    for (HexLocation locaction : pointLocation.hexLocations())
    {
      Hex hex = game.board().hexes().get(locaction);
      if (hex.canHavePort())
        ports.add(hex.port());
    }
    // Grab port if available on this spot and add it to player when found
    for (Port port : ports)
      if (port.hexSide().getHexPoint1().equals(pointLocation)
              || port.hexSide().getHexPoint2()
                      .equals(pointLocation))
        player.ports().add(port);
    message = I.get().actions().builtTown(player.user().name());
    super.perform(game);
  }
  @Override public boolean isAllowed(TurnPhase turnPhase) {
    return turnPhase.isBuilding();
  }
  @Override public boolean isAllowed(GamePhase gamePhase) {
    return gamePhase.isPlayTurns() || gamePhase.isInitialPlacement();
  }
  @Override public String toDoMessage() {
    return I.get().actions().builtTownToDo(player.user().name());
  }
  @Override public ActionWidget createActionWidget(ActionWidgetFactory actionWidgetFactory) {
    return actionWidgetFactory.createBuildTownWidget();
  }
  @Override public GameBehaviour begin(GameWidget gameWidget) {
    return new BuildTownInGame(gameWidget, this);
  }
  @Override public ActionDetailWidget createDetailWidget(ActionDetailWidgetFactory factory) {
    return factory.getBuildTownDetailWidget(this);
  }

  public static class BuildTownOnBoard extends BuildPointOnBoard {
    private BuildTown buildTown;
    private GameBehaviourCallback callback;
    private Set<GraphPoint> townCandidates;

    public BuildTownOnBoard(BuildTown buildTown, GameBehaviourCallback callback) {
      super();
      this.buildTown = buildTown;
      this.callback = callback;
    }
    @Override public void clicked(PieceVisual pieceVisual, GameBoardVisual board) {
      PointVisual pointVisual = pieceVisual.getPointVisual();
      if (pointVisual != null) {
        buildTown.setPointLocation(pointVisual.getHexPoint());
        callback.done();
      }
    }
    @Override public void setNeutral(GameBoardVisual gameVisual) {
      for (PointVisual pointVisual : gameVisual.pointVisuals().values()) {
        pointVisual.setVisible(false);
      }
    }
    @Override public void start(GameBoardVisual gameVisual) {
      if (gameVisual.game().phase().isInitialPlacement()) {
        townCandidates = gameVisual.board().graph().getTownCandidatesFirstTown(null);
      }
      if (gameVisual.game().phase().isPlayTurns()) {
        townCandidates = gameVisual.board().graph()
                .getTownCandidatesTurnPhase(gameVisual.game().turn().player());
      }
      for (GraphPoint point : townCandidates) {
        gameVisual.pointVisuals().get(point).setVisible(true);
      }
    }
    @Override public GameAction gameAction() {
      return buildTown;
    }
  }

  public static class BuildTownInGame implements GameBehaviour,
          GameBehaviourCallback, TradeFirst {
    private BuildTown buildTown;
    private BuildTownOnBoard buildTownOnBoard;
    private GameWidget gameWidget;
    private Town town = new Town();

    public BuildTownInGame(GameWidget gameWidget, BuildTown buildTown) {
      this.buildTown = buildTown;
      this.gameWidget = gameWidget;
      buildTownOnBoard = new BuildTownOnBoard(buildTown, this);
    }
    @Override public void finish() {}
    @Override public void start(GameWidget gameWidget) {
      GamePlayer player = gameWidget.playingPlayer();
      if (gameWidget.game().phase().isPlayTurns()) {
        if (player.resources().hasAtLeast(town.cost())) {
          gameWidget.boardVisualWidget().boardVisual().setBehaviour(buildTownOnBoard);
        } else {
          gameWidget.bankTradeDialog().setPieceToTradeFor(town, this);
        }
      }
      else {
        gameWidget.boardVisualWidget().boardVisual().setBehaviour(buildTownOnBoard);
      }
    }
    @Override public void cancel() {}
    @Override public void done() {
      gameWidget.doAction(buildTown);
      buildTownOnBoard.setNeutral(gameWidget.boardVisualWidget().boardVisual());
    }
    @Override public void onCancelTrade() {
      buildTownOnBoard.setNeutral(gameWidget.boardVisualWidget().boardVisual());
    }
    @Override public void onTraded() {
      gameWidget.boardVisualWidget().boardVisual().setBehaviour(buildTownOnBoard);
    }
  }
}
