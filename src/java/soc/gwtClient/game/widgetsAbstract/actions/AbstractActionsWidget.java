package soc.gwtClient.game.widgetsAbstract.actions;

import java.util.HashMap;

import soc.common.actions.gameAction.turns.TurnAction;
import soc.common.core.Core;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.Point2D;
import soc.gwtClient.game.widgetsInterface.actions.ActionWidget;
import soc.gwtClient.game.widgetsInterface.actions.ActionWidgetFactory;
import soc.gwtClient.game.widgetsInterface.actions.ActionsWidget;
import soc.gwtClient.game.widgetsInterface.actions.DiceWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractActionsWidget implements ActionsWidget
{
    protected ComplexPanel rootPanel;
    protected GamePlayer player;
    protected GameWidget gameWidget;
    protected HashMap<Integer, ActionWidget> widgetsIndices = new HashMap<Integer, ActionWidget>();

    public AbstractActionsWidget(GameWidget gameWidget, GamePlayer player)
    {
        this.player = player;
        this.gameWidget = gameWidget;

        rootPanel = createRootPanel();
        rootPanel.add(new Label(player.getUser().getName()));

        ActionWidgetFactory widgetFactory = Core.get().getClientFactory()
                .getActionWidgetFactory(player);

        int index = 0;
        for (TurnAction turnAction : gameWidget.getGame().getRules()
                .getPossibleActions())
        {
            ActionWidget widget = turnAction.createActionWidget(player);
            if (widget != null)
            {
                rootPanel.add(widget);
                widgetsIndices.put(index, widget);
                index++;
            }
        }
        rootPanel.setWidth("100%");
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
