package soc.gwtClient.game.abstractWidgets;

import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.factories.ActionWidgetFactory;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractActionsWidget implements ActionsWidget
{
    protected ComplexPanel rootPanel;
    protected GamePlayer player;
    protected GamePanel gamePanel;

    public AbstractActionsWidget(GamePanel gamePanel, GamePlayer player)
    {
        this.player = player;
        this.gamePanel = gamePanel;

        rootPanel = createRootPanel();

        ActionWidgetFactory widgetFactory = getActionWidgetFactory();

        for (TurnAction turnAction : gamePanel.getGame().getGameRules()
                .getPossibleActions())
        {
            ActionWidget w = widgetFactory.createActionWidget(turnAction,
                    player, gamePanel);
            if (w != null)
                rootPanel.add(w);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.gwtClient.client.game.IActionsWidget#createRootPanel()
     */
    @Override
    public ComplexPanel createRootPanel()
    {
        VerticalPanel rootPanel = new VerticalPanel();
        rootPanel.setSpacing(5);
        return rootPanel;
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }
}
