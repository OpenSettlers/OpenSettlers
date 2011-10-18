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
import org.soc.common.game.RoadTokensChangedEvent.RoadTokensChangedHandler;
import org.soc.common.game.actions.Action.ActionPresenter.ActionWidgetFactory;
import org.soc.common.game.actions.ActionOnGameBoard.BuildSideOnBoard;
import org.soc.common.game.actions.GameBehaviour.DefaultReceivedActionInGame;
import org.soc.common.game.actions.GameBehaviour.GameBehaviourCallback;
import org.soc.common.game.actions.GameBehaviour.TradeFirst;
import org.soc.common.game.actions.TurnAction.AbstractTurnAction;
import org.soc.common.game.actions.WantsBuildRoadEvent.HasWantsBuildRoadHandlers;
import org.soc.common.game.actions.WantsBuildRoadEvent.WantsBuildRoadHandler;
import org.soc.common.game.board.*;
import org.soc.common.game.pieces.*;
import org.soc.common.internationalization.*;
import org.soc.common.views.meta.*;
import org.soc.common.views.widgetsInterface.actions.*;
import org.soc.common.views.widgetsInterface.actions.ActionDetailWidget.ActionDetailWidgetFactory;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.common.views.widgetsInterface.visuals.*;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual.SideVisual;
import org.soc.gwt.client.game.actions.*;
import org.soc.gwt.client.game.widgetsAbstract.actions.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.user.client.ui.*;
import com.gwtplatform.dispatch.annotation.*;

public class BuildRoad extends AbstractTurnAction {
  public interface BuildRoadView extends HasWantsBuildRoadHandlers {
    @GenEvent public class WantsBuildRoad {}

    public void setRoadToken1Visible(boolean visbile);
    public void setRoadToken2Visible(boolean visbile);
    public void setTrade1Visible(boolean visible);
    public void setTrade2Visible(boolean visible);
    public void enable();
    public void disable();
  }

  @Override public Icon icon() {
    return new IconImpl(R.icons().road16(), R
            .icons().road32(), R.icons().road48());
  }
  @Override public Name name() {
    return null;
  }
  @Override public Description description() {
    // TODO Auto-generated method stub
    return null;
  }

  private HexSide sideLocation;

  public HexSide getSideLocation() {
    return sideLocation;
  }
  public BuildRoad setSideLocation(HexSide sideLocation) {
    this.sideLocation = sideLocation;
    return this;
  }
  @Override public boolean isValid(Game game) {
    return requireThat(game)
            //
            .notNull(sideLocation)
            .existsOnBoard(sideLocation)
            .spot(sideLocation)
            .notTaken()
            //
            .areAllMet();
    //    if (!(game.phase().isInitialPlacement())) {
    //      // if (!(Road.ROAD.canBuild(game.getBoard(), player)))
    //      // {
    //      // invalidMessage = "Player cannot build the road";
    //      // return false;
    //      // }
    //      //
    //      // if (!(Road.ROAD.canPay(player)))
    //      // {
    //      // invalidMessage = "Player cannot pay for the road";
    //      // return false;
    //      // }
    //    }
    //    return true;
  }
  /** TODO: implement DiscoveryHexes */
  @Override public void perform(Game game) {
    boolean usedRoadbuildingToken = false;
    Road road = (Road) player.stock().roads().get(0);
    road.setSide(sideLocation);
    // when in InGame phase, player should pay for road somehow
    if (game.phase().isPlayTurns()) {
      int roadBuildingTokens = player.roadBuildingTokens();
      // When played a roadbuilding card, first use up roadbuilding tokens
      if (roadBuildingTokens > 0) {
        // the player has played a road building card this turn
        player.setRoadBuildingTokens(roadBuildingTokens--);
        usedRoadbuildingToken = true;
      } else {
        game.bank().moveListFrom(player.resources(), road.cost());
      }
    }
    game.addPiece(road, player);
    if (game.phase().isPlayTurns()) {
      // Check if the LR should be updated
      // TODO: do LR check
      // game.CalculateLongestRoad(pplayer);
    }
    // TODO: fix message
    // message = String.Format("{0} built a road"
    // ,game.GetPlayer(Sender).XmlPlayer.Name);
    // if (usedRoadbuildingToken)
    // _Message += ", using his roadbuilding development card.";
    message = player.user().name() + " has built a road";
    super.perform(game);
  }
  @Override public boolean isAllowed(TurnPhase turnPhase) {
    return turnPhase.isBuilding();
  }
  @Override public boolean isAllowed(GamePhase gamePhase) {
    return gamePhase.isPlayTurns() || gamePhase.isInitialPlacement();
  }
  @Override public String toDoMessage() {
    return I.get().actions().builtRoadToDo(player.user().name());
  }
  @Override public ActionPresenter createPresenter(
          ActionWidgetFactory actionWidgetFactory) {
    return actionWidgetFactory.createBuildRoadWidget();
  }
  @Override public GameBehaviour opponentReceived(GameWidget gameWidget) {
    return new DefaultReceivedActionInGame(gameWidget, this);
  }
  @Override public GameBehaviour received(GameWidget gameWidget) {
    return new DefaultReceivedActionInGame(gameWidget, this);
  }
  @Override public GameBehaviour begin(GameWidget gameWidget) {
    return new BuildRoadInGame(gameWidget, this);
  }
  @Override public ActionDetailWidget createDetailWidget(ActionDetailWidgetFactory factory) {
    return factory.getBuildRoadDetailWidget(this);
  }

  public static class BuildRoadOnBoard extends BuildSideOnBoard {
    private BuildRoad buildRoad;
    private Set<GraphSide> sides;
    private GameBehaviourCallback callback;

    /** Toggles displaying candidates off */
    @Override public void setNeutral(GameBoardVisual visual) {
      if (sides != null)
        for (GraphSide side : sides)
          visual.getSideVisuals().get(side).setVisible(false);
    }
    /** Shows road candidates based on the on current turn of the game */
    @Override public void start(GameBoardVisual gameVisual) {
      // Reset and see if there are candidates
      sides = null;
      // During initial setup phase
      if (gameVisual.game().phase().isInitialPlacement()) {
        // Grab player
        GamePlayer player = gameVisual.game().turn().player();
        // Grab road candidates for players' first or second town
        if (player.sidePieces().size() == 0)
          sides = gameVisual.board().graph().getRoadCandidatesFirstTown(player);
        else if (player.sidePieces().size() == 1)
          sides = gameVisual.board().graph().getRoadCandidatesSecondTown(player);
        else
          assert (false); //  "expected 0 or 1 sidepieces"
      }
      // During play turns GamePhase
      else if (gameVisual.game().phase().isPlayTurns()) {
        GamePlayer player = gameVisual.game().turn().player();
        sides = gameVisual.board().graph().roadCandidates(player);
      }
      // If any candidates found, show side visuals
      if (sides != null)
        for (GraphSide side : sides)
          gameVisual.getSideVisuals().get(side).setVisible(true);
    }
    public BuildRoad getBuildRoad() {
      return buildRoad;
    }
    public BuildRoadOnBoard(BuildRoad buildRoad, GameBehaviourCallback callback) {
      this.buildRoad = buildRoad;
      this.callback = callback;
    }
    /** Picks a candidate from the shown road candidates */
    @Override public void clicked(PieceVisual pieceVisual, GameBoardVisual board) {
      SideVisual sideVisual = pieceVisual.sideVisual();
      if (sideVisual != null) {
        buildRoad.setSideLocation(sideVisual.getHexSide());
        callback.done();
      }
    }
    @Override public GameAction gameAction() {
      return buildRoad;
    }
  }

  public static class BuildRoadInGame implements GameBehaviour,
          GameBehaviourCallback, TradeFirst {
    private BuildRoad buildRoad;
    private GameWidget gameWidget;
    private BuildRoadOnBoard buildRoadOnBoard;

    public BuildRoadInGame(GameWidget gameWidget, BuildRoad buildRoad) {
      this.buildRoad = buildRoad;
      this.buildRoadOnBoard = new BuildRoadOnBoard(buildRoad, this);
      this.gameWidget = gameWidget;
    }
    @Override public void finish() {
      buildRoadOnBoard.setNeutral(gameWidget
              .boardVisualWidget().boardVisual());
    }
    @Override public void start(GameWidget gameWidget) {
      GamePlayer player = gameWidget.playingPlayer();
      Road road = new Road();
      if (gameWidget.game().phase().isPlayTurns()) {
        if (road.canPay(player)) {
          gameWidget.boardVisualWidget().boardVisual()
                  .setBehaviour(buildRoadOnBoard);
        }
        else {
          gameWidget.bankTradeDialog().setPieceToTradeFor(road, this);
        }
      }
      else {
        gameWidget.boardVisualWidget().boardVisual().setBehaviour(
                buildRoadOnBoard);
      }
    }
    @Override public void cancel() {}
    @Override public void done() {
      gameWidget.doAction(buildRoad);
      buildRoadOnBoard.setNeutral(gameWidget.boardVisualWidget().boardVisual());
    }
    @Override public void onTraded() {
      gameWidget.boardVisualWidget().boardVisual().setBehaviour(buildRoadOnBoard);
    }
    @Override public void onCancelTrade() {
      finish();
    }
    @Override public boolean endsManually() {
      // TODO Auto-generated method stub
      return false;
    }
  }

  public static class BuildRoadPresenter
          extends
          AbstractActionPresenter
          implements
          GamePhaseChangedHandler,
          RoadTokensChangedHandler {
    Road road = new Road();
    BuildRoad buildRoad;
    BuildRoadView view = new BuildRoadGwt();

    public BuildRoadPresenter(final GameWidget gameWidget, final GamePlayer player) {
      super(gameWidget, player);
      buildRoad = new BuildRoad();
      buildRoad.setPlayer(player);
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
      player.stock().roads().addAddedHandler(new Added<Road>() {
        @Override public void added(Road item) {
          checkEnabled();
        }
      });
      gameWidget.game().addGamePhaseChangedHandler(this);
      player.addRoadTokensChangedHandler(this);
      view = new BuildRoadGwt();
      view.addWantsBuildRoadHandler(new WantsBuildRoadHandler() {
        @Override public void onWantsBuildRoad(WantsBuildRoadEvent event) {
          gameWidget.startAction(new BuildRoad().setPlayer(player));
        }
      });
    }
    @Override public Widget asWidget() {
      return (Widget) view;
    }
    @Override protected void updateEnabled() {
      checkEnabled();
    }
    @Override public void onGamePhaseChanged(GamePhaseChangedEvent event) {
      checkEnabled();
    }
    @Override public void onRoadTokensChanged(RoadTokensChangedEvent event) {
      checkEnabled();
    }
    private void checkEnabled() {
      setRoadTokens();
      setTradesNeededToBuild();
      if (enabled && player.isOnTurn()) {
        Game game = gameWidget.game();
        if (game.isAllowed(buildRoad)  // current phase
                && road.canBuild(game.board(), player)  // we need space
                && road.canPay(player)  // we need resources
                && game.board().graph().roadCandidates(player).size() > 0) {
          view.enable();
          return;
        }
      }
      view.disable();
    }
    private void setRoadTokens() {
      view.setRoadToken1Visible(player.roadBuildingTokens() > 0);
      view.setRoadToken1Visible(player.roadBuildingTokens() > 1);
    }
    private void setTradesNeededToBuild() {
      if (road.canPay(player) && player.roadBuildingTokens() == 0) {
        int amountTradesNeeded = player.resources().getNeededResources(road.cost()).size();
        view.setTrade1Visible(amountTradesNeeded >= 1);
        view.setTrade2Visible(amountTradesNeeded >= 2);
      } else {
        view.setTrade1Visible(false);
        view.setTrade2Visible(false);
      }
    }
  }
}
