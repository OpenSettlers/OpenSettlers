package soc.gwtClient.game.abstractWidgets;

import java.util.HashMap;

import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.Point2D;
import soc.gwtClient.game.abstractWidgets.factories.ActionWidgetFactory;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractActionsWidget implements ActionsWidget
{
    protected ComplexPanel rootPanel;
    protected GamePlayer player;
    protected GamePanel gamePanel;
    protected HashMap<Integer, ActionWidget> widgetsIndices = new HashMap<Integer, ActionWidget>();

    public AbstractActionsWidget(GamePanel gamePanel, GamePlayer player)
    {
        this.player = player;
        this.gamePanel = gamePanel;

        rootPanel = createRootPanel();
        rootPanel.add(new Label(player.getUser().getName()));

        ActionWidgetFactory widgetFactory = getActionWidgetFactory();

        int index = 0;
        for (TurnAction turnAction : gamePanel.getGame().getRules()
                .getPossibleActions())
        {
            ActionWidget w = widgetFactory.createActionWidget(turnAction,
                    player, gamePanel);
            if (w != null)
            {
                rootPanel.add(w);
                widgetsIndices.put(index, w);
                index++;
            }
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

    public Point2D getTopLeftDiceWidgetPosition()
    {
        for (ActionWidget widget : widgetsIndices.values())
        {
            if (widget instanceof DiceWidget)
            {
                DiceWidget diceWidget = (DiceWidget) widget;
                return diceWidget.getPosition();
            }
        }
        throw new AssertionError("Assert we will find a DiceWidget");
    }
}
