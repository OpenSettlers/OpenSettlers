package org.soc.common.game.actions;

import java.util.*;

import org.soc.common.core.GenericList.Adds.Added;
import org.soc.common.core.GenericList.AddsList.ListAdded;
import org.soc.common.core.GenericList.ImmutableList;
import org.soc.common.core.GenericList.ModelPresenter;
import org.soc.common.core.GenericList.ModelView.InfoView;
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
import org.soc.common.game.actions.GameAction.GameActionPresenter.ButtonPresenter.ButtonView;
import org.soc.common.game.actions.GameBehaviour.GameBehaviourCallback;
import org.soc.common.game.actions.GameBehaviour.TradeFirst;
import org.soc.common.game.actions.TurnAction.AbstractTurnAction;
import org.soc.common.game.actions.WantsBuildCityEvent.HasWantsBuildCityHandlers;
import org.soc.common.game.actions.WantsBuildCityEvent.WantsBuildCityHandler;
import org.soc.common.game.board.*;
import org.soc.common.game.hexes.*;
import org.soc.common.game.pieces.*;
import org.soc.common.views.meta.*;
import org.soc.common.views.widgetsInterface.actions.*;
import org.soc.common.views.widgetsInterface.actions.ActionDetailWidget.ActionDetailWidgetFactory;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.common.views.widgetsInterface.visuals.*;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual.PointVisual;
import org.soc.gwt.client.game.widgetsAbstract.actions.*;

import com.google.gwt.user.client.ui.*;
import com.google.inject.*;
import com.gwtplatform.dispatch.annotation.*;

import static org.soc.common.internationalization.I.*;
import static org.soc.gwt.client.images.R.*;

/** Builds next city from stock on the playing board */
public class BuildCity extends AbstractTurnAction {
  public interface BuildCityButton extends GameActionPresenter<BuildCity, ButtonView> {
    public interface View extends ButtonView<BuildCity> {}
  }

  public interface BuildCityInfo extends ModelPresenter<BuildCity, InfoView> {
    public interface View extends InfoView<BuildCity> {}
  }

  private HexPoint point;
  private Town town;
  private City city;

  @Override public Icon icon() {
    return new IconImpl(icons.city16(), icons.city32(), icons.city48());
  }
  @Override public Name name() {
    return new Name.Impl(constants.city());
  }
  @Override public Description description() {
    return new Description.Impl(constants.cityDescription());
  }
  public HexPoint getPointLocation() {
    return point;
  }
  public BuildCity setLocation(HexPoint pointLocation) {
    this.point = pointLocation;
    return this;
  }
  @Override public boolean isValid(Game game) {
    return requireThat(game)
            .notNull(point)
            .player(player).hasTownAt(point).canPayFor(city.cost())
            .existsOnBoard(point)
            // TODO: add criterium --> has city available in stock
            .areAllMet();
  }
  @Override public void perform(Game game) {
    GamePlayer player = game.playerById(sender);
    // Get first city from stock
    City city = player.stock().cities().get(0);
    city.setPoint(point);
    if (game.phase().isPlayTurns()) {
      game.bank().moveListFrom(player.resources(), city.cost());
      Town town = player.towns().get(point);
      town.removeFrom(player);
    }
    if (game.phase().isInitialPlacement()) {
      // Add resources to player
      MutableResourceList resourcesFromCity = new MutableResourceListImpl();
      for (HexLocation hexLocation : point.hexLocations()) {
        Hex hex = game.board().hexes().get(hexLocation);
        if (hex.producesResource())
          resourcesFromCity.add(hex.resource());
      }
      player.resources().addList(resourcesFromCity);
    }
    // Add city to player
    game.addPiece(city, player);
    // TODO: fix message
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
  @Override public ActionPresenter createPresenter(ActionWidgetFactory factory) {
    return factory.createBuildCityWidget();
  }
  @Override public GameBehaviour begin(GameWidget gameWidget) {
    return new BuildCityInGame(gameWidget, this);
  }
  @Override public ActionDetailWidget createDetailWidget(ActionDetailWidgetFactory factory) {
    return factory.buildCityDetailWidget(this);
  }

  public static class BuildCityInGame
          implements
          GameBehaviour,
          GameBehaviourCallback,
          TradeFirst {
    private BuildCityOnBoard buildCityBehaviour;
    private BuildCity buildCity;
    private GameWidget gameWidget;
    private City city = new City();

    public BuildCityInGame(GameWidget gameWidget, BuildCity buildCity) {
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
    @Override public boolean endsManually() {
      // TODO Auto-generated method stub
      return false;
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
      for (PointVisual pointVisual : pointVisuals)
        pointVisual.setVisible(false);
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

  public interface BuidCityView extends HasWantsBuildCityHandlers {
    @GenEvent public class WantsBuildCity {}

    public void setTrade1Visible(boolean visible);
    public void setTrade2Visible(boolean visible);
    public void setTrade3Visible(boolean visible);
    public void setTrade4Visible(boolean visible);
    public void setTrade5Visible(boolean visible);
    public void setTradesVisible(boolean visible);
    public void enable();
    public void disable();
  }

  public static class BuildCityPresenter
          extends
          AbstractActionPresenter
          implements
          GamePhaseChangedHandler,
          TurnPhaseChangedHandler {
    private City city = new City();
    private BuildCity buildCity = new BuildCity();
    private BuidCityView view;

    @Inject public BuildCityPresenter(final GameWidget gameWidget, final GamePlayer player,
            BuidCityView view) {
      super(gameWidget, player);
      view = view;
      buildCity.setPlayer(player);
      player.resources().addListAddedHandler(new ListAdded<Resource>() {
        @Override public void listAdded(ImmutableList<Resource> items) {
          checkEnabled();
        }
      });
      player.resources().addListRemovedHandler(new ListRemoved<Resource>() {
        @Override public void listRemoved(ImmutableList<Resource> items) {
          checkEnabled();
        }
      });
      player.stock().cities().addAddedHandler(new Added<City>() {
        @Override public void added(City item) {
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
      view.addWantsBuildCityHandler(new WantsBuildCityHandler() {
        @Override public void onWantsBuildCity(WantsBuildCityEvent event) {
          gameWidget.startAction(new BuildCity().setPlayer(player));
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
    @Override protected void updateEnabled() {
      checkEnabled();
    }
    private void checkEnabled() {
      if (enabled && player.isOnTurn()) {
        Game game = gameWidget.game();
        if (game.phase().isAllowed(buildCity)  // current phase
                && city.canBuild(game.board(), player) // we need space
                && city.canPay(player)) { // we need resources
          view.enable();
          setTradesNeededToBuild();
          return;
        }
      }
      view.disable();
    }
    private void setTradesNeededToBuild() {
      int amountTradesNeeded = player.resources().getNeededResources(city.cost()).size();
      view.setTrade1Visible(amountTradesNeeded >= 1);
      view.setTrade2Visible(amountTradesNeeded >= 2);
      view.setTrade3Visible(amountTradesNeeded >= 3);
      view.setTrade4Visible(amountTradesNeeded >= 4);
      view.setTrade5Visible(amountTradesNeeded >= 5);
    }
  }
}
