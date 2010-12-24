package soc.gwtClient.game.widgets.standard.bitmap.actions;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

import soc.common.actions.gameAction.turnActions.EndTurn;
import soc.common.actions.gameAction.turnActions.AbstractTurnAction;
import soc.common.board.resources.ResourcesChangedEvent;
import soc.common.game.GamePhaseChangedEvent;
import soc.common.game.GamePhaseChangedEventHandler;
import soc.common.game.Player;
import soc.gwtClient.game.abstractWidgets.AbstractActionWidget;
import soc.gwtClient.game.abstractWidgets.IActionWidget;
import soc.gwtClient.game.abstractWidgets.IGamePanel;

public class EndTurnBitmapWidget extends AbstractActionWidget implements GamePhaseChangedEventHandler
{
    public PushButton btnEndTurn = new PushButton(new Image("iconz/32/EndTurn32.png"));
    private EndTurn endTurn = new EndTurn();

    public EndTurnBitmapWidget(final IGamePanel gamePanel, final Player player)
    {
        super(gamePanel, player);
        
        endTurn.setPlayer(player);
        
        // TODO: switch to game.getTurn
        //gamePanel.getGame().addPlayerOnTurnChangedEventHandler(this);
        gamePanel.getGame().addGamePhaseChangedEventHandler(this);
        
        btnEndTurn.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                gamePanel.startAction
                (
                    (AbstractTurnAction)new EndTurn()
                        .setPlayer(player)
                );
            }
        });
    }

    @Override
    public Widget asWidget()
    {
        return btnEndTurn;
    }

    @Override
    protected void updateEnabled()
    {
        btnEndTurn.setEnabled(enabled);
    }

    /*
    @Override
    public void onPlayerOnTurnChanged(PlayerOnTurnChangedEvent event)
    {
        checkEnabled();
    }
    */

    @Override
    public void onGamePhaseChanged(GamePhaseChangedEvent event)
    {
        checkEnabled();
    }
    
    private void checkEnabled()
    {
        if (onTurn)
        {
            if (gamePanel.getGame().getCurrentPhase().isAllowed(endTurn))
            {
                setEnabled(true);
                return;
            }
        }
        
        setEnabled(false);
    }
}
