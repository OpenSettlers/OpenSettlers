package soc.gwtClient.client.game;

import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.game.Player;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractActionsWidget implements IActionsWidget
{
    protected ComplexPanel rootPanel;
    protected Player player;
    protected IGamePanel gamePanel;
    
    public AbstractActionsWidget(IGamePanel gamePanel, Player player)
    {
        this.player=player;
        this.gamePanel=gamePanel;
        
        IActionWidgetFactory widgetFactory = getActionWidgetFactory();
        
        for (TurnAction turnAction : gamePanel.getGame().getPossibleActions())
        {
            rootPanel.add(widgetFactory.createActionPanel(turnAction, player, gamePanel));
        }
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }

}
