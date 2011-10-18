package org.soc.common.game.actions;

import java.util.*;

import org.soc.common.core.GenericList.Adds.Added;
import org.soc.common.core.GenericList.AddsList.ListAdded;
import org.soc.common.core.GenericList.ImmutableList;
import org.soc.common.core.GenericList.RemovesList.ListRemoved;
import org.soc.common.core.property.Properties.Description;
import org.soc.common.core.property.Properties.Name;
import org.soc.common.game.*;
import org.soc.common.game.GamePhaseChangedEvent.GamePhaseChangedHandler;
import org.soc.common.game.Resources.MutableResourceList;
import org.soc.common.game.Resources.MutableResourceListImpl;
import org.soc.common.game.TurnPhaseChangedEvent.TurnPhaseChangedHandler;
import org.soc.common.game.actions.Action.ActionPresenter.ActionWidgetFactory;
import org.soc.common.game.actions.ActionOnGameBoard.BuildPointOnBoard;
import org.soc.common.game.actions.GameBehaviour.GameBehaviourCallback;
import org.soc.common.game.actions.GameBehaviour.TradeFirst;
import org.soc.common.game.actions.TurnAction.AbstractTurnAction;
import org.soc.common.game.actions.WantsBuildTownEvent.HasWantsBuildTownHandlers;
import org.soc.common.game.actions.WantsBuildTownEvent.WantsBuildTownHandler;
import org.soc.common.game.board.*;
import org.soc.common.game.hexes.*;
import org.soc.common.game.pieces.*;
import org.soc.common.internationalization.*;
import org.soc.common.views.meta.*;
import org.soc.common.views.widgetsInterface.actions.*;
import org.soc.common.views.widgetsInterface.actions.ActionDetailWidget.ActionDetailWidgetFactory;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.common.views.widgetsInterface.visuals.*;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual.PointVisual;
import org.soc.gwt.client.game.actions.*;
import org.soc.gwt.client.game.widgetsAbstract.actions.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.user.client.ui.*;
import com.gwtplatform.dispatch.annotation.*;

public class BuildTown extends AbstractTurnAction {
  private HexPoint pointLocation;
  private Town town;

  @Override public Icon icon() {
    return new IconImpl(R.icons().town16(), R
            .icons().town32(), R.icons().town48());
  }
  @Override public Name name() {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public Description description() {
    // TODO Auto-generated method stub
    return null;
  }
  public HexPoint getPointLocation() {
    return pointLocation;
  }
  public BuildTown setPointLocation(HexPoint pointLocation) {
    this.pointLocation = pointLocation;
    return this;
  }
  @Override public boolean isValid(Game game) {// @formatter:off
    return requireThat(game)
            .notNull(pointLocation)
            .player(player)
              .canPayFor(town.cost())
              .hasSideAt(pointLocation)
            .existsOnBoard(pointLocation)
            .spotAndNeighbours(pointLocation).notTaken()
            .isLandHexAt(pointLocation)
            .areAllMet();
  }                                             // @formatter:on
  @Override public void perform(Game game) {
    // update town management
    Town town = (Town) player.stock().towns().get(0);
    town.setPoint(pointLocation);
    game.addPiece(town, player);
    // remove players' resources and put them in the bank
    if (game.phase().isPlayTurns())
      game.bank().moveListFrom(player.resources(), town.cost());
    if (game.phase().isInitialPlacement() && player.pointPieces().size() == 2) {
      // player gets resources in neighbouring hexes
      MutableResourceList resourcesFromPlacement = new MutableResourceListImpl();
      for (HexLocation loc : pointLocation.hexLocations()) {
        Hex hex = game.board().hexes().get(loc);
        if (hex.producesResource())
          resourcesFromPlacement.add(hex.resource());
      }
      player.resources().moveListFrom(game.bank(), resourcesFromPlacement);
    }
    // Check if the town is built on a port, if so, add port
    // to list of ports of the player
    // Get a list of ports
    List<Port> ports = new ArrayList<Port>();
    for (HexLocation locaction : pointLocation.hexLocations()) {
      Hex hex = game.board().hexes().get(locaction);
      if (hex.canHavePort())
        ports.add(hex.port());
    }
    // Grab port if available on this spot and add it to player when found
    for (Port port : ports)
      if (port.hexSide().getHexPoint1().equals(pointLocation)
              || port.hexSide().getHexPoint2().equals(pointLocation))
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
  @Override public ActionPresenter createPresenter(ActionWidgetFactory actionWidgetFactory) {
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
      for (PointVisual pointVisual : gameVisual.pointVisuals().values())
        pointVisual.setVisible(false);
    }
    @Override public void start(GameBoardVisual gameVisual) {
      if (gameVisual.game().phase().isInitialPlacement())
        townCandidates = gameVisual.board().graph().getTownCandidatesFirstTown(null);
      if (gameVisual.game().phase().isPlayTurns())
        townCandidates = gameVisual.board().graph()
                .getTownCandidatesTurnPhase(gameVisual.game().turn().player());
      for (GraphPoint point : townCandidates)
        gameVisual.pointVisuals().get(point).setVisible(true);
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
    @Override public boolean endsManually() {
      // TODO Auto-generated method stub
      return false;
    }
  }

  //TODO: more refined UI invalidation
  public interface BuildTownView extends HasWantsBuildTownHandlers {
    @GenEvent public class WantsBuildTown {}

    public void setTrade1Visible(boolean visible);
    public void setTrade2Visible(boolean visible);
    public void setTrade3Visible(boolean visible);
    public void setTrade4Visible(boolean visible);
    public void enable();
    public void disable();
    public void setTradesVisible(boolean visible);
  }

  public static class BuildTownPresenter extends AbstractActionPresenter implements
          GamePhaseChangedHandler,
          TurnPhaseChangedHandler {
    private Town town = new Town();
    private BuildTown buildTown = new BuildTown();
    private BuildTownView view = new BuildTownGwt();

    public BuildTownPresenter(final GameWidget gameWidget,
            final GamePlayer player)
    {
      super(gameWidget, player);
      buildTown.setPlayer(player);
      player.resources().addListRemovedHandler(new ListRemoved<Resource>() {
        @Override public void listRemoved(ImmutableList<Resource> items) {
          checkEnabled();
        }
      });
      player.resources().addListAddedHandler(new ListAdded<Resource>() {
        @Override public void listAdded(ImmutableList<Resource> items) {
          checkEnabled();
        }
      });
      player.stock().towns().addAddedHandler(new Added<Town>() {
        @Override public void added(Town item) {
          checkEnabled();
        }
      });
      gameWidget.game().addGamePhaseChangedHandler(this);
      gameWidget.game().addTurnPhaseChangedHandler(this);
      player.towns().addAddedHandler(new Added<Town>() {
        @Override public void added(Town item) {
          checkEnabled();
        }
      });
      view.addWantsBuildTownHandler(new WantsBuildTownHandler() {
        @Override public void onWantsBuildTown(WantsBuildTownEvent event) {
          gameWidget.startAction(new BuildTown().setPlayer(player));
        }
      });
    }
    @Override public Widget asWidget() {
      return (Widget) view;
    }
    @Override public void onGamePhaseChanged(GamePhaseChangedEvent event) {
      checkEnabled();
    }
    @Override public void onTurnPhaseChanged(TurnPhaseChangedEvent event) {
      checkEnabled();
    }
    private void checkEnabled() {
      setTradesNeededToBuild();
      if (enabled && player.isOnTurn()) {
        Game game = gameWidget.game();
        if (game.isAllowed(buildTown)  // current phase
                // must be OK
                && town.canBuild(game.board(), player)  // we need space
                && town.canPay(player)  // we need resources
                && game.board().graph()
                        .getTownCandidatesTurnPhase(player)
                        .size() > 0) {
          view.enable();
          return;
        }
      }
      view.disable();
    }
    @Override protected void updateEnabled() {
      checkEnabled();
    }
    private void setTradesNeededToBuild() {
      if (town.canPay(player)) {
        int amountTradesNeeded = player.resources()
                .getNeededResources(town.cost()).size();
        view.setTrade1Visible(amountTradesNeeded >= 1);
        view.setTrade2Visible(amountTradesNeeded >= 2);
        view.setTrade3Visible(amountTradesNeeded >= 3);
        view.setTrade4Visible(amountTradesNeeded >= 4);
      } else {
        view.setTrade1Visible(false);
        view.setTrade2Visible(false);
        view.setTrade3Visible(false);
        view.setTrade4Visible(false);
      }
    }
  }
}
