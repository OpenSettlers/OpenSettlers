package soc.gwtClient.game.widgets.standard.bitmap;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.actions.gameAction.turnActions.standard.*;
import soc.common.board.pieces.PiecesChangedEvent;
import soc.common.board.pieces.PiecesChangedEventHandler;
import soc.common.board.pieces.Road;
import soc.common.board.resources.ResourcesChangedEvent;
import soc.common.board.resources.ResourcesChangedEventHandler;
import soc.common.game.Game;
import soc.common.game.GamePhaseChangedEvent;
import soc.common.game.GamePhaseChangedEventHandler;
import soc.common.game.Player;
import soc.gwtClient.game.abstractWidgets.AbstractActionWidget;
import soc.gwtClient.game.abstractWidgets.IActionWidget;
import soc.gwtClient.game.abstractWidgets.IGamePanel;
import soc.gwtClient.game.widgets.bitmap.ImageLibrary;


public class BitmapBuildRoadWidget extends AbstractActionWidget 
    implements ResourcesChangedEventHandler, PiecesChangedEventHandler, GamePhaseChangedEventHandler
{
    AbsolutePanel absolutePanel = new AbsolutePanel();
    VerticalPanel tradesPanel = new VerticalPanel();
    Road road = new Road();
    PushButton btnBuildRoad = new PushButton(new Image(ImageLibrary.getIcon(road, 48)));
    Image trade1 = new Image(ImageLibrary.getTradeIcon(16));
    Image trade2 = new Image(ImageLibrary.getTradeIcon(16));
    BuildRoad buildRoad;
    
    public BitmapBuildRoadWidget(final IGamePanel gamePanel, final Player player)
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
                    gamePanel.startAction
                    (
                        (TurnAction)new BuildRoad()
                            .setPlayer(player)
                    );
                }
            }
        });
        
        tradesPanel.add(trade1);
        tradesPanel.add(trade2);
        
        absolutePanel.add(btnBuildRoad, 0,0);
        absolutePanel.add(tradesPanel, 3,3);
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
            
            if (game.getCurrentPhase().isAllowed(buildRoad) &&   // current phase must be OK
                    road.canBuild(game.getBoard(), player)    &&   // we need space
                    road.canPay(player))                           // we need resources
            {
                setEnabled(true);
                setTradesNeededToBuild();
                return;
            }
        }
        setEnabled(false);
    }

    private void setTradesNeededToBuild()
    {
        int amountTradesNeeded = player.getResources().getNeededResources(road.getCost()).size();
        trade1.setVisible(amountTradesNeeded >= 1);
        trade1.setVisible(amountTradesNeeded >= 2);
    }
}