package org.soc.common.game.actions;

import static org.soc.common.internationalization.I.actions;
import static org.soc.common.internationalization.I.constants;
import static org.soc.gwt.client.images.R.icons;

import java.util.HashSet;
import java.util.Set;

import org.soc.common.game.Game;
import org.soc.common.game.GamePhase;
import org.soc.common.game.GamePlayer;
import org.soc.common.game.ResourceList;
import org.soc.common.game.TurnPhase;
import org.soc.common.game.actions.ActionOnGameBoard.BuildPointOnBoard;
import org.soc.common.game.actions.GameBehaviour.GameBehaviourCallback;
import org.soc.common.game.actions.GameBehaviour.TradeFirst;
import org.soc.common.game.actions.TurnAction.AbstractTurnAction;
import org.soc.common.game.board.GraphPoint;
import org.soc.common.game.board.HexLocation;
import org.soc.common.game.board.HexPoint;
import org.soc.common.game.hexes.Hex;
import org.soc.common.game.pieces.City;
import org.soc.common.game.pieces.Town;
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

/** Builds next city from stock on the playing board */
public class BuildCity extends AbstractTurnAction {
  @Override public Icon icon() {
    return new IconImpl(icons.city16(), icons.city32(), icons.city48());
  }
  @Override public String getLocalizedName() {
    return constants.city();
  }
  @Override public String getDescription() {
    return constants.cityDescription();
  }

  private HexPoint point;

  public HexPoint getPointLocation() {
    return point;
  }
  public BuildCity setLocation(HexPoint pointLocation) {
    this.point = pointLocation;
    return this;
  }
  @Override public boolean isValid(Game game) {
    if (!super.isValid(game))
      return false;
    // we need at least an instance of the new place
    if (point == null) {
      invalidMessage = "Location cant be null";
      return false;
    }
    // player should have a ship or road at some neighbour
    GamePlayer player = game.playerById(sender);
    if (!(player.towns().contains(point)))
    {
      invalidMessage = "No town found to replace with a city";
      return false;
    }
    // if (!City.CITY.canBuild(game.getBoard(), player))
    // {
    // invalidMessage = "Player cannot build the city";
    // return false;
    // }
    //
    // if (!City.CITY.canPay(player))
    // {
    // invalidMessage = "Player cannot pay for the city";
    // return false;
    // }
    return true;
  }
  @Override public void perform(Game game) {
    GamePlayer player = game.playerById(sender);
    // Get first city from stock
    City city = player.stock().cities().get(0);
    city.setPoint(point);
    if (game.phase().isPlayTurns()) {
      // Pay for the city
      player.resources().moveTo(game.bank(), city.cost());
      // Remove the town from the board
      Town town = player.towns().get(point);
      town.removeFrom(player);
    }
    if (game.phase().isInitialPlacement()) {
      // Add resources to player
      ResourceList resourcesFromCity = new ResourceList();
      for (HexLocation hexLocation : point.hexLocations()) {
        Hex hex = game.board().hexes().get(hexLocation);
        if (hex.producesResource()) {
          resourcesFromCity.add(hex.resource());
        }
      }
      player.resources().addList(resourcesFromCity);
    }
    // Add city to player
    game.addPiece(city, player);
    // TODO: fix message
    // message = String.Format("{0} build a city at {1}",
    // gamePlayer.XmlPlayer.Name, Location.ToString(xmlGame.Board));
    super.perform(game);
  }
  @Override public boolean isAllowed(TurnPhase turnPhase) {
    return turnPhase.isBuilding();
  }
  @Override public boolean isAllowed(GamePhase gamePhase) {
    return gamePhase.isPlayTurns() || gamePhase.isInitialPlacement();
  }
  @Override public String toDoMessage() {
    return actions.builtTownToDo(player.user().name());
  }
  @Override public ActionWidget createActionWidget(ActionWidgetFactory factory) {
    return factory.createBuildCityWidget();
  }
  @Override public GameBehaviour begin(GameWidget gameWidget) {
    return new BuildCityInGame(gameWidget, this);
  }
  @Override public ActionDetailWidget createDetailWidget(ActionDetailWidgetFactory factory) {
    return factory.buildCityDetailWidget(this);
  }

  public static class BuildCityInGame implements GameBehaviour, GameBehaviourCallback,
          TradeFirst {
    private BuildCityOnBoard buildCityBehaviour;
    private BuildCity buildCity;
    private GameWidget gameWidget;
    private City city = new City();

    public BuildCityInGame(GameWidget gameWidget, BuildCity buildCity) {
      super();
      this.buildCity = buildCity;
      this.gameWidget = gameWidget;
      buildCityBehaviour = new BuildCityOnBoard(buildCity, gameWidget.playingPlayer(), this);
    }
    @Override public void finish() {}
    @Override public void cancel() {}
    @Override public void onCancelTrade() {}
    @Override public void start(GameWidget gameWidget) {
      GamePlayer player = gameWidget.playingPlayer();
      if (player.resources().hasAtLeast(city.cost()))
        gameWidget.boardVisualWidget().boardVisual().setBehaviour(buildCityBehaviour);
      else
        gameWidget.bankTradeDialog().setPieceToTradeFor(city, this);
    }
    @Override public void done() {
      gameWidget.doAction(buildCity);
      buildCityBehaviour.setNeutral(gameWidget.boardVisualWidget().boardVisual());
    }
    @Override public void onTraded() {
      gameWidget.boardVisualWidget().boardVisual().setBehaviour(buildCityBehaviour);
    }
  }

  public static class BuildCityOnBoard extends BuildPointOnBoard {
    private BuildCity buildCity;
    private GamePlayer player;
    private GameBehaviourCallback callback;
    private Set<PointVisual> pointVisuals = new HashSet<PointVisual>();

    public BuildCityOnBoard(BuildCity buildCity, GamePlayer player,
            GameBehaviourCallback callback) {
      this.buildCity = buildCity;
      this.player = player;
      this.callback = callback;
    }
    public BuildCity getBuildCity() {
      return buildCity;
    }
    @Override public void clicked(PieceVisual pieceVisual, GameBoardVisual board) {
      PointVisual pointVisual = pieceVisual.getPointVisual();
      if (pointVisual != null) {
        buildCity.setLocation(pointVisual.getHexPoint());
        callback.done();
      }
    }
    @Override public void setNeutral(GameBoardVisual visual) {
      for (PointVisual pointVisual : pointVisuals) {
        pointVisual.setVisible(false);
      }
    }
    @Override public void start(GameBoardVisual gameVisual) {
      for (Town town : player.towns()) {
        GraphPoint graphPoint = gameVisual.board().graph().findGraphPoint(town.point());
        PointVisual pointVisual = gameVisual.pointVisuals().get(graphPoint);
        pointVisual.setVisible(true);
        pointVisuals.add(pointVisual);
      }
    }
    @Override public GameAction gameAction() {
      return buildCity;
    }
  }
}
