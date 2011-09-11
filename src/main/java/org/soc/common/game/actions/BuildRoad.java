package org.soc.common.game.actions;

import java.util.Set;

import org.soc.common.game.Game;
import org.soc.common.game.GamePhase;
import org.soc.common.game.GamePlayer;
import org.soc.common.game.TurnPhase;
import org.soc.common.game.actions.ActionInGame.DefaultOpponentReceivedBehaviour;
import org.soc.common.game.actions.ActionOnGameBoard.BuildSideOnBoard;
import org.soc.common.game.actions.GameBehaviour.GameBehaviourCallback;
import org.soc.common.game.actions.GameBehaviour.TradeFirst;
import org.soc.common.game.actions.TurnAction.AbstractTurnAction;
import org.soc.common.game.board.GraphSide;
import org.soc.common.game.board.HexSide;
import org.soc.common.game.pieces.Road;
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
import org.soc.common.views.widgetsInterface.visuals.PieceVisual.SideVisual;
import org.soc.gwt.client.images.R;

public class BuildRoad extends AbstractTurnAction {
  @Override public Icon icon() {
    return new IconImpl(R.icons().road16(), R
            .icons().road32(), R.icons().road48());
  }
  @Override public String name() {
    return "BuildRoad";
  }
  @Override public String getLocalizedName() {
    return null;
  }
  @Override public String getDescription() {
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
    if (!super.isValid(game))
      return false;
    if (sideLocation == null) {
      invalidMessage = "Intersection cannot be null";
      return false;
    }
    // the spot must be free still
    if (game.allSidePieces().contains(sideLocation)) {
      invalidMessage = "Already built on the given location";
      return false;
    }
    if (!(game.phase().isInitialPlacement())) {
      // if (!(Road.ROAD.canBuild(game.getBoard(), player)))
      // {
      // invalidMessage = "Player cannot build the road";
      // return false;
      // }
      //
      // if (!(Road.ROAD.canPay(player)))
      // {
      // invalidMessage = "Player cannot pay for the road";
      // return false;
      // }
    }
    return true;
  }
  /* (non-Javadoc) TODO: implement DiscoveryHexes
   * 
   * @see org.soc.common.actions.gameAction.GameAction#perform(org.soc.common.game.Game) */
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
        player.resources().moveTo(game.bank(), road.cost());
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
  @Override public ActionWidget createActionWidget(
          ActionWidgetFactory actionWidgetFactory) {
    return actionWidgetFactory.createBuildRoadWidget();
  }
  @Override public ActionInGame opponentReceived(GameWidget gameWidget) {
    return new DefaultOpponentReceivedBehaviour(gameWidget, this);
  }
  @Override public ActionInGame received(GameWidget gameWidget) {
    return new DefaultOpponentReceivedBehaviour(gameWidget, this);
  }
  @Override public GameBehaviour begin(GameWidget gameWidget) {
    return new BuildRoadInGame(gameWidget, this);
  }
  @Override public ActionDetailWidget createDetailWidget(
          ActionDetailWidgetFactory factory)
  {
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
      if (gameVisual.game().phase().isInitialPlacement())
      {
        // Grab player
        GamePlayer player = gameVisual.game().turn()
                .player();
        // Grab road candidates for players' first or second town
        if (player.sidePieces().size() == 0)
          sides = gameVisual.board().graph()
                  .getRoadCandidatesFirstTown(player);
        else
          sides = gameVisual.board().graph()
                  .getRoadCandidatesSecondTown(player);
      }
      // During play turns GamePhase
      else if (gameVisual.game().phase().isPlayTurns())
      {
        GamePlayer player = gameVisual.game().turn().player();
        sides = gameVisual.board().graph().getRoadCandidates(player);
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
    /* Picks a candidate from the shown road candidates
     * 
     * @see org.soc.common.client.behaviour.game.BuildSideBehaviour#clicked(org.soc.common
     * .client.visuals.IPieceVisual, org.soc.common.client.visuals.board.IBoardVisual) */
    @Override public void clicked(PieceVisual pieceVisual, GameBoardVisual board) {
      SideVisual sideVisual = pieceVisual.sideVisual();
      if (sideVisual != null)
      {
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
      super();
      this.buildRoad = buildRoad;
      buildRoadOnBoard = new BuildRoadOnBoard(buildRoad, this);
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
  }
}
