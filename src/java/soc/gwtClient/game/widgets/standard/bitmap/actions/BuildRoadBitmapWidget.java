package soc.gwtClient.game.widgets.standard.bitmap.actions;

import soc.common.actions.gameAction.turnActions.standard.BuildRoad;
import soc.common.board.pieces.PlistChangedEvent;
import soc.common.board.pieces.PlistChangedEventHandler;
import soc.common.board.pieces.Road;
import soc.common.board.resources.ResourcesChangedEvent;
import soc.common.board.resources.ResourcesChangedEventHandler;
import soc.common.game.Game;
import soc.common.game.GamePhaseChangedEvent;
import soc.common.game.GamePhaseChangedEventHandler;
import soc.common.game.RoadTokensChangedEvent;
import soc.common.game.RoadTokensChangedEventHandler;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.AbstractActionWidget;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.images.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class BuildRoadBitmapWidget extends AbstractActionWidget implements
        ResourcesChangedEventHandler, GamePhaseChangedEventHandler,
        RoadTokensChangedEventHandler, PlistChangedEventHandler<Road>
{
    AbsolutePanel absolutePanel = new AbsolutePanel();
    VerticalPanel tradesPanel = new VerticalPanel();
    VerticalPanel roadTokensPanel = new VerticalPanel();
    Road road = new Road();
    PushButton btnBuildRoad = new PushButton(
            new Image(Resources.icons().road()));
    Image trade1 = new Image(Resources.icons().trade());
    Image trade2 = new Image(Resources.icons().trade());
    Image roadToken1 = new Image(Resources.icons().roadToken());
    Image roadToken2 = new Image(Resources.icons().roadToken());
    BuildRoad buildRoad;

    public BuildRoadBitmapWidget(final GamePanel gamePanel,
            final GamePlayer player)
    {
        super(gamePanel, player);

        absolutePanel.setSize("4em", "4em");

        buildRoad = new BuildRoad();
        buildRoad.setPlayer(player);

        player.getResources().addResourcesChangedEventHandler(this);
        player.getStock().getRoads().addRoadsChangedEventHandler(this);
        gamePanel.getGame().addGamePhaseChangedEventHandler(this);
        player.addRoadTokenChangedEventHandler(this);

        btnBuildRoad.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                gamePanel.startAction(new BuildRoad().setPlayer(player));
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

    @Override
    public Widget asWidget()
    {
        return absolutePanel;
    }

    @Override
    protected void updateEnabled()
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

    @Override
    public void onResourcesChanged(ResourcesChangedEvent resourcesChanged)
    {
        checkEnabled();
    }

    @Override
    public void onGamePhaseChanged(GamePhaseChangedEvent event)
    {
        checkEnabled();
    }

    @Override
    public void onRoadTokensChanged(RoadTokensChangedEvent event)
    {
        checkEnabled();
    }

    @Override
    public void onPlistChanged(PlistChangedEvent<Road> event)
    {
        checkEnabled();
    }

    private void checkEnabled()
    {
        setRoadTokens();
        setTradesNeededToBuild();
        if (enabled && player.isOnTurn())
        {
            Game game = gamePanel.getGame();

            if (game.isAllowed(buildRoad) && // current phase
                    // must be OK
                    road.canBuild(game.getBoard(), player) && // we need space
                    road.canPay(player) && // we need resources
                    game.getBoard().getGraph().getRoadCandidates(player).size() > 0)
            {
                enableUI();
                return;
            }
        }
        disableUI();
    }

    private void setRoadTokens()
    {
        roadToken1.setVisible(player.getRoadBuildingTokens() > 0);
        roadToken2.setVisible(player.getRoadBuildingTokens() > 1);
    }

    private void setTradesNeededToBuild()
    {
        if (road.canPay(player) && player.getRoadBuildingTokens() == 0)
        {
            int amountTradesNeeded = player.getResources().getNeededResources(
                    road.getCost()).size();
            trade1.setVisible(amountTradesNeeded >= 1);
            trade2.setVisible(amountTradesNeeded >= 2);
        }
        else
        {
            trade1.setVisible(false);
            trade2.setVisible(false);
        }

    }
}