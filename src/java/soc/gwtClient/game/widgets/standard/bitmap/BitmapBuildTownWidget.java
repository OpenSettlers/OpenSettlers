package soc.gwtClient.game.widgets.standard.bitmap;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.actions.gameAction.turnActions.standard.BuildTown;
import soc.common.board.pieces.PiecesChangedEvent;
import soc.common.board.pieces.PiecesChangedEventHandler;
import soc.common.board.pieces.Town;
import soc.common.board.resources.ResourcesChangedEvent;
import soc.common.board.resources.ResourcesChangedEventHandler;
import soc.common.game.Game;
import soc.common.game.GamePhaseChangedEvent;
import soc.common.game.GamePhaseChangedEventHandler;
import soc.common.game.Player;
import soc.gwtClient.game.abstractWidgets.AbstractActionWidget;
import soc.gwtClient.game.abstractWidgets.IActionWidget;
import soc.gwtClient.game.abstractWidgets.IGamePanel;

public class BitmapBuildTownWidget extends AbstractActionWidget
    implements ResourcesChangedEventHandler, PiecesChangedEventHandler, GamePhaseChangedEventHandler
{
    PushButton btnBuildTown = new PushButton(new Image("icons/48/Town48.png"));
    Town town = new Town();
    BuildTown buildTown = new BuildTown();
    
    public BitmapBuildTownWidget(IGamePanel gamePanel, Player player)
    {
        super(gamePanel, player);
        
        buildTown.setPlayer(player);
        
        player.getResources().addResourcesChangedEventHandler(this);
        player.getStock().addPiecesChangedEventHandler(this);
        gamePanel.getGame().addGamePhaseChangedEventHandler(this);
    }

    @Override
    public Widget asWidget()
    {
        return btnBuildTown;
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
            
            if (game.getCurrentPhase().isAllowed(buildTown) &&   // current phase must be OK
                town.canBuild(game.getBoard(), player)      &&    // we need space
                town.canPay(player))                               // we need resources
            {
                setEnabled(true);
                return;
            }
        }
        setEnabled(false);
    }

    @Override
    protected void updateEnabled()
    {
        btnBuildTown.setEnabled(enabled);
    }
}
