package soc.gwtClient.game.widgetsAbstract;

import soc.common.game.StatusChangedEventHandler;
import soc.common.game.TurnChangedEventHandler;
import soc.gwtClient.game.widgetsBitmap.status.AllPhaseStatusBitmapWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsInterface.main.StatusWidget;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractStatusPanel implements StatusWidget,
        TurnChangedEventHandler, StatusChangedEventHandler
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
    protected GameWidget gamePanel;

    protected abstract ComplexPanel createRootPanel();

    public AbstractStatusPanel(GameWidget gamePanel)
    {
        super();
        this.gamePanel = gamePanel;

        this.allPhaseStatuses = new AllPhaseStatusBitmapWidget(gamePanel);

        turnPanel.add(lblTurnID);
        turnPanel.add(lblPlayerOnTurn);
        turnPanel.add(lblTurn);

        actionPanel.add(lblAction);

        actionTurnPanel.add(turnPanel);
        actionTurnPanel.add(actionPanel);

        playingStatusPanel.add(allPhaseStatuses);
        playingStatusPanel.add(actionTurnPanel);

        rootPanel.add(playingStatusPanel);

        gamePanel.getGame().getActionsQueue().addQueueChangedEventHandler(this);
        gamePanel.getGame().addTurnChangedEventHandler(this);
        gamePanel.getGame().addStatusChangedEventHandler(this);

        rootPanel.add(playingStatusPanel);
        rootPanel.setWidth("100%");
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }
}