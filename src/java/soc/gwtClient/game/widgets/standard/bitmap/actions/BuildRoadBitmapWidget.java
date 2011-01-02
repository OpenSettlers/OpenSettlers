package soc.gwtClient.game.widgets.standard.bitmap.actions;

import soc.common.actions.gameAction.turnActions.AbstractTurnAction;
import soc.common.actions.gameAction.turnActions.standard.BuildRoad;
import soc.common.board.pieces.PiecesChangedEvent;
import soc.common.board.pieces.PiecesChangedEventHandler;
import soc.common.board.pieces.Road;
import soc.common.board.resources.ResourcesChangedEvent;
import soc.common.board.resources.ResourcesChangedEventHandler;
import soc.common.game.Game;
import soc.common.game.GamePhaseChangedEvent;
import soc.common.game.GamePhaseChangedEventHandler;
import soc.common.game.GamePlayer;
import soc.common.game.RoadTokensChangedEvent;
import soc.common.game.RoadTokensChangedEventHandler;
import soc.gwtClient.game.abstractWidgets.AbstractActionWidget;
import soc.gwtClient.game.abstractWidgets.IGamePanel;
import soc.gwtClient.images.Resources;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class BuildRoadBitmapWidget extends AbstractActionWidget implements
        ResourcesChangedEventHandler, PiecesChangedEventHandler,
        GamePhaseChangedEventHandler, RoadTokensChangedEventHandler
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

    public BuildRoadBitmapWidget(final IGamePanel gamePanel, final GamePlayer player)
    {
        super(gamePanel, player);

        absolutePanel.setSize("60px", "60px");

        buildRoad = new BuildRoad();
        buildRoad.setPlayer(player);

        player.getResources().addResourcesChangedEventHandler(this);
        player.getStock().addPiecesChangedEventHandler(this);
        gamePanel.getGame().addGamePhaseChangedEventHandler(this);

        btnBuildRoad.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                if (player.getResources().hasAtLeast(road.getCost()))
                {
                    gamePanel.startAction((AbstractTurnAction) new BuildRoad()
                            .setPlayer(player));
                }
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
        btnBuildRoad.setEnabled(enabled);
    }

    @Override
    public void onResourcesChanged(ResourcesChangedEvent resourcesChanged)
    {
        checkEnabled();
    }

    @Override
    public void onPiecesChanged(PiecesChangedEvent list)
    {
        checkEnabled();
    }

    @Override
    public void onGamePhaseChanged(GamePhaseChangedEvent event)
    {
        checkEnabled();
    }

    private void checkEnabled()
    {
        if (onTurn)
        {
            Game game = gamePanel.getGame();

            if (game.getCurrentPhase().isAllowed(buildRoad) && // current phase
                    // must be OK
                    road.canBuild(game.getBoard(), player) && // we need space
                    road.canPay(player)) // we need resources
            {
                setEnabled(true);
                if (player.getRoadBuildingTokens() > 0)
                {
                    setRoadTokens();
                }
                else
                {
                    setTradesNeededToBuild();
                }
                return;
            }
        }
        setEnabled(false);
    }

    private void setRoadTokens()
    {
        roadToken1.setVisible(player.getRoadBuildingTokens() > 0);
        roadToken2.setVisible(player.getRoadBuildingTokens() > 1);
    }

    private void setTradesNeededToBuild()
    {
        int amountTradesNeeded = player.getResources().getNeededResources(
                road.getCost()).size();
        trade1.setVisible(amountTradesNeeded >= 1);
        trade1.setVisible(amountTradesNeeded >= 2);
    }

    @Override
    public void onRoadTokensChanged(RoadTokensChangedEvent event)
    {
        checkEnabled();
    }
}