package org.soc.gwt.client.game.widgetsAbstract.main;

import org.soc.common.game.StatusChangedEvent.StatusChangedHandler;
import org.soc.common.game.TurnChangedEvent.TurnChangedHandler;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.common.views.widgetsInterface.main.StatusWidget;
import org.soc.gwt.client.game.widgetsBitmap.status.AllPhaseStatusBitmapWidget;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractStatusPanel implements StatusWidget,
        TurnChangedHandler, StatusChangedHandler
{
  protected AbsolutePanel rootPanel = new AbsolutePanel();
  protected HorizontalPanel playingStatusPanel = new HorizontalPanel();
  protected VerticalPanel actionTurnPanel = new VerticalPanel();
  protected HorizontalPanel turnPanel = new HorizontalPanel();
  protected HorizontalPanel actionPanel = new HorizontalPanel();
  protected AllPhaseStatusBitmapWidget allPhaseStatuses;
  protected Label lblAction = new Label("Action todo");
  protected Label lblStatus = new Label("Status of the game");
  protected Label lblTurn = new Label("Status of the game");
  protected Label lblTurnID = new Label("Turn #");
  protected Label lblPlayerOnTurn = new Label("Player");
  protected GameWidget gameWidget;

  protected abstract ComplexPanel createRootPanel();
  public AbstractStatusPanel(GameWidget gameWidget)
  {
    super();
    this.gameWidget = gameWidget;
    this.allPhaseStatuses = new AllPhaseStatusBitmapWidget(gameWidget);
    turnPanel.add(lblTurnID);
    turnPanel.add(lblPlayerOnTurn);
    turnPanel.add(lblTurn);
    actionPanel.add(lblAction);
    actionTurnPanel.add(turnPanel);
    actionTurnPanel.add(actionPanel);
    playingStatusPanel.add(allPhaseStatuses);
    playingStatusPanel.add(actionTurnPanel);
    rootPanel.add(playingStatusPanel);
    gameWidget.game().actionsQueue().addActionQueueChangedHandler(this);
    gameWidget.game().addTurnChangedHandler(this);
    gameWidget.game().addStatusChangedHandler(this);
    rootPanel.add(playingStatusPanel);
    rootPanel.setWidth("100%");
  }
  @Override public Widget asWidget()
  {
    return rootPanel;
  }
}