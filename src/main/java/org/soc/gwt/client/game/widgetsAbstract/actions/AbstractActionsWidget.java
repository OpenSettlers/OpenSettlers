package org.soc.gwt.client.game.widgetsAbstract.actions;

import java.util.HashMap;

import org.soc.common.game.GamePlayer;
import org.soc.common.game.actions.TurnAction;
import org.soc.common.views.widgetsInterface.actions.ActionWidget;
import org.soc.common.views.widgetsInterface.actions.ActionWidget.ActionWidgetFactory;
import org.soc.common.views.widgetsInterface.actions.ActionsWidget;
import org.soc.common.views.widgetsInterface.actions.DiceWidget;
import org.soc.common.views.widgetsInterface.generic.Point2D;
import org.soc.common.views.widgetsInterface.main.GameWidget;

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

  public AbstractActionsWidget(GameWidget gameWidget, GamePlayer player) {
    this.player = player;
    this.gameWidget = gameWidget;
    rootPanel = createRootPanel();
    rootPanel.add(new Label(player.user().name()));
    ActionWidgetFactory widgetFactory = gameWidget.clientFactory()
            .getActionWidgetFactory(player);
    int index = 0;
    for (TurnAction turnAction : gameWidget.game().rules()
            .possibleActions())
    {
      ActionWidget widget = turnAction.createActionWidget(widgetFactory);
      if (widget != null) {
        rootPanel.add(widget);
        widgetsIndices.put(index, widget);
        index++;
      }
    }
    rootPanel.setWidth("100%");
  }
  @Override public ComplexPanel createRootPanel() {
    VerticalPanel rootPanel = new VerticalPanel();
    rootPanel.setSpacing(5);
    return rootPanel;
  }
  @Override public Widget asWidget() {
    return rootPanel;
  }
  public Point2D getTopLeftDiceWidgetPosition() {
    for (ActionWidget widget : widgetsIndices.values()) {
      if (widget instanceof DiceWidget) {
        DiceWidget diceWidget = (DiceWidget) widget;
        return diceWidget.getPosition();
      }
    }
    throw new AssertionError("Assert we will find a DiceWidget");
  }
}
