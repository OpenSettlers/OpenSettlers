package soc.gwtClient.game.abstractWidgets;

import soc.common.game.StatusChangedEventHandler;
import soc.common.game.TurnChangedEventHandler;
import soc.gwtClient.game.widgets.standard.bitmap.status.AllPhaseStatusBitmapWidget;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractStatusPanel implements StatusPanel,
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
    protected Label lblPlayerOnTurn = new Label("Player");
    protected GamePanel gamePanel;

    protected abstract ComplexPanel createRootPanel();

    public AbstractStatusPanel(GamePanel gamePanel)
    {
        super();
        this.gamePanel = gamePanel;

        this.allPhaseStatuses = new AllPhaseStatusBitmapWidget(gamePanel);

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