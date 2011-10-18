package org.soc.gwt.client.game.widgetsBitmap.status;

import org.soc.common.game.ActionQueueChangedEvent;
import org.soc.common.game.StatusChangedEvent;
import org.soc.common.game.Turn;
import org.soc.common.game.TurnChangedEvent;
import org.soc.common.game.actions.GameAction;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.main.AbstractStatusPanel;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class StatusBitmapPanel extends AbstractStatusPanel
{
  /** @wbp.parser.entryPoint */
  public StatusBitmapPanel(GameWidget gameWidget)
  {
    super(gameWidget);
    rootPanel.setStyleName("statusPanel");
  }
  @Override public void onActionQueueChanged(ActionQueueChangedEvent event)
  {
    GameAction enqueuedAction = event.getEnqueuedAction();
    if (enqueuedAction != null)
      lblAction.setText(enqueuedAction.toDoMessage());
  }
  @Override protected ComplexPanel createRootPanel()
  {
    return new HorizontalPanel();
  }
  @Override public void onTurnChanged(TurnChangedEvent event)
  {
    Turn turn = event.getNewTurn();
    StringBuilder sb = new StringBuilder();
    if (turn.getID() != 0)
    {
      lblTurnID.setText("Turn #" + turn.getID());
      lblTurnID.setVisible(true);
    }
    else
    {
      lblTurnID.setVisible(false);
    }
    sb.append("'s turn. ");
    sb.append(turn.player().user().name());
    sb.append(" should ");
    sb.append(gameWidget.game().phase().message());
    lblTurn.setText(sb.toString());
    lblPlayerOnTurn.setText(turn.player().user().name());
  }
  @Override public void onStatusChanged(StatusChangedEvent event)
  {
    lblStatus.setText("Game status: "
            + gameWidget.game().status().getDescription());
  }
}