package soc.gwtClient.game.widgets.standard.bitmap;

import soc.common.game.GamePhaseChangedEvent;
import soc.common.game.logs.ActionQueueChangedEvent;
import soc.common.game.logs.QueuedAction;
import soc.gwtClient.game.abstractWidgets.AbstractStatusPanel;
import soc.gwtClient.game.abstractWidgets.GamePanel;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class StatusBitmapPanel extends AbstractStatusPanel
{
    public StatusBitmapPanel(GamePanel gamePanel)
    {
        super(gamePanel);

        rootPanel.setStyleName("statusPanel");
    }

    @Override
    public void onQueueChanged(ActionQueueChangedEvent event)
    {
        QueuedAction enqueuedAction = event.getEnqueuedAction();
        if (enqueuedAction != null)
        {
            lblAction.setText(enqueuedAction.getAction().getToDoMessage());
        }
    }

    @Override
    protected ComplexPanel createRootPanel()
    {
        return new HorizontalPanel();
    }

    @Override
    public void onGamePhaseChanged(GamePhaseChangedEvent event)
    {
        lblPhase.setText(event.getNewPhase().getMessage());
    }
}
