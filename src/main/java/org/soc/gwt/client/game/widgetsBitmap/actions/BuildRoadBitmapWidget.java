package org.soc.gwt.client.game.widgetsBitmap.actions;

import org.soc.common.game.Game;
import org.soc.common.game.GamePhaseChangedEvent;
import org.soc.common.game.GamePhaseChangedEvent.GamePhaseChangedHandler;
import org.soc.common.game.GamePlayer;
import org.soc.common.game.ResourcesChangedEvent;
import org.soc.common.game.ResourcesChangedEvent.ResourcesChangedHandler;
import org.soc.common.game.RoadTokensChangedEvent;
import org.soc.common.game.RoadTokensChangedEvent.RoadTokensChangedHandler;
import org.soc.common.game.actions.BuildRoad;
import org.soc.common.game.pieces.PlayerPieceList.PlayerPieceListChangedEvent;
import org.soc.common.game.pieces.PlayerPieceList.PlayerPieceListChangedEventHandler;
import org.soc.common.game.pieces.Road;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.actions.AbstractActionWidget;
import org.soc.gwt.client.images.R;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class BuildRoadBitmapWidget extends AbstractActionWidget implements
        ResourcesChangedHandler, GamePhaseChangedHandler,
        RoadTokensChangedHandler,
        PlayerPieceListChangedEventHandler<Road>
{
  AbsolutePanel absolutePanel = new AbsolutePanel();
  VerticalPanel tradesPanel = new VerticalPanel();
  VerticalPanel roadTokensPanel = new VerticalPanel();
  Road road = new Road();
  PushButton btnBuildRoad = new PushButton(new Image(R.icons()
          .road48()));
  Image trade1 = new Image(R.icons().trade16());
  Image trade2 = new Image(R.icons().trade16());
  Image roadToken1 = new Image(R.icons().roadToken16());
  Image roadToken2 = new Image(R.icons().roadToken16());
  BuildRoad buildRoad;

  public BuildRoadBitmapWidget(final GameWidget gameWidget,
          final GamePlayer player)
  {
    super(gameWidget, player);
    absolutePanel.setSize("4em", "4em");
    buildRoad = new BuildRoad();
    buildRoad.setPlayer(player);
    player.resources().addResourcesChangedHandler(this);
    player.stock().roads().addRoadsChangedEventHandler(this);
    gameWidget.game().addGamePhaseChangedHandler(this);
    player.addRoadTokensChangedHandler(this);
    btnBuildRoad.addClickHandler(new ClickHandler()
    {
      @Override public void onClick(ClickEvent event)
      {
        gameWidget.startAction(new BuildRoad().setPlayer(player));
      }
    });
    tradesPanel.add(trade1);
    tradesPanel.add(trade2);
    roadTokensPanel.add(roadToken1);
    roadTokensPanel.add(roadToken2);
    absolutePanel.add(btnBuildRoad, 0, 0);
    absolutePanel.add(tradesPanel, 3, 3);
    absolutePanel.add(roadTokensPanel, 3, 3);
  }
  @Override public Widget asWidget()
  {
    return absolutePanel;
  }
  @Override protected void updateEnabled()
  {
    checkEnabled();
  }
  private void enableUI()
  {
    btnBuildRoad.setEnabled(true);
  }
  private void disableUI()
  {
    btnBuildRoad.setEnabled(false);
  }
  @Override public void onResourcesChanged(ResourcesChangedEvent resourcesChanged)
  {
    checkEnabled();
  }
  @Override public void onGamePhaseChanged(GamePhaseChangedEvent event)
  {
    checkEnabled();
  }
  @Override public void onRoadTokensChanged(RoadTokensChangedEvent event)
  {
    checkEnabled();
  }
  @Override public void onPlayerPieceListChanged(PlayerPieceListChangedEvent<Road> event)
  {
    checkEnabled();
  }
  private void checkEnabled()
  {
    setRoadTokens();
    setTradesNeededToBuild();
    if (enabled && player.isOnTurn())
    {
      Game game = gameWidget.game();
      if (game.isAllowed(buildRoad) && // current phase
      // must be OK
      road.canBuild(game.board(), player) && // we need space
      road.canPay(player) && // we need resources
      game.board().graph()
              .roadCandidates(player).size() > 0)
      {
        enableUI();
        return;
      }
    }
    disableUI();
  }
  private void setRoadTokens()
  {
    roadToken1.setVisible(player.roadBuildingTokens() > 0);
    roadToken2.setVisible(player.roadBuildingTokens() > 1);
  }
  private void setTradesNeededToBuild()
  {
    if (road.canPay(player) && player.roadBuildingTokens() == 0)
    {
      int amountTradesNeeded = player.resources()
              .getNeededResources(road.cost()).size();
      trade1.setVisible(amountTradesNeeded >= 1);
      trade2.setVisible(amountTradesNeeded >= 2);
    } else
    {
      trade1.setVisible(false);
      trade2.setVisible(false);
    }
  }
}