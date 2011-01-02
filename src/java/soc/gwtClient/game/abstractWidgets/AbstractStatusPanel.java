package soc.gwtClient.game.abstractWidgets;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractStatusPanel implements StatusPanel
{
    protected DockLayoutPanel rootPanel = new DockLayoutPanel(Unit.EM);
    protected Label lblStatus = new Label(
            "Some text here to tell the player what's up with the status of the game");
    protected IGamePanel gamePanel;

    protected abstract ComplexPanel createRootPanel();

    public AbstractStatusPanel(IGamePanel gamePanel)
    {
        super();
        this.gamePanel = gamePanel;

        rootPanel.add(lblStatus);
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }
}