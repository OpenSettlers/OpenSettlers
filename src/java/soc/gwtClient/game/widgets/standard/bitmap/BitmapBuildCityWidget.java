package soc.gwtClient.game.widgets.standard.bitmap;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.actions.gameAction.turnActions.standard.BuildCity;
import soc.common.board.pieces.City;
import soc.common.board.pieces.PiecesChangedEvent;
import soc.common.board.pieces.PiecesChangedEventHandler;
import soc.common.board.resources.ResourcesChangedEvent;
import soc.common.board.resources.ResourcesChangedEventHandler;
import soc.common.game.Game;
import soc.common.game.GamePhaseChangedEvent;
import soc.common.game.GamePhaseChangedEventHandler;
import soc.common.game.Player;
import soc.gwtClient.game.abstractWidgets.AbstractActionWidget;
import soc.gwtClient.game.abstractWidgets.IGamePanel;

public class BitmapBuildCityWidget extends AbstractActionWidget
    implements ResourcesChangedEventHandler, PiecesChangedEventHandler, GamePhaseChangedEventHandler
{
    PushButton btnCity = new PushButton(new Image("icons/48/City48.png"));
    City city = new City();
    BuildCity buildCity = new BuildCity();
    
    public BitmapBuildCityWidget(IGamePanel gamePanel, Player player)
    {
        super(gamePanel, player);
        
        buildCity.setPlayer(player);
        
        player.getResources().addResourcesChangedEventHandler(this);
        player.getStock().addPiecesChangedEventHandler(this);
        gamePanel.getGame().addGamePhaseChangedEventHandler(this);
    }

    @Override
    public Widget asWidget()
    {
        return btnCity;
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
    
    @Override
    protected void updateEnabled()
    {
        btnCity.setEnabled(enabled);
    }

    private void checkEnabled()
    {
        if (onTurn)
        {
            Game game = gamePanel.getGame();
            
            if (game.getCurrentPhase().isAllowed(buildCity) &&   // current phase must be OK
                    city.canBuild(game.getBoard(), player)    &&   // we need space
                    city.canPay(player))                           // we need resources
            {
                setEnabled(true);
                return;
            }
        }
        setEnabled(false);
    }
}
