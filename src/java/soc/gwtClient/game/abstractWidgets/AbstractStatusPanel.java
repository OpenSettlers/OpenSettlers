package soc.gwtClient.game.abstractWidgets;

import soc.common.game.GamePhaseChangedEventHandler;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractStatusPanel implements StatusPanel,
        GamePhaseChangedEventHandler
{
    protected DockLayoutPanel rootPanel = new DockLayoutPanel(Unit.EM);
    protected Label lblAction = new Label("Action todo");
    protected Label lblPhase = new Label("Current Game phase goes here");
    protected Label lblStatus = new Label("Status of the game");
    protected IGamePanel gamePanel;

    protected abstract ComplexPanel createRootPanel();

    public AbstractStatusPanel(IGamePanel gamePanel)
    {
        super();
        this.gamePanel = gamePanel;

        rootPanel.add(lblStatus);

        gamePanel.getGame().getActionsQueue().addQueueChangedEventHandler(this);
        gamePanel.getGame().addGamePhaseChangedEventHandler(this);
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }
}