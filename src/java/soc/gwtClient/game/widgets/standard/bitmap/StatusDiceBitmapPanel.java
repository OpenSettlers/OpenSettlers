package soc.gwtClient.game.widgets.standard.bitmap;

import soc.common.game.logs.ActionQueueChangedEvent;
import soc.common.game.logs.QueuedAction;
import soc.gwtClient.game.abstractWidgets.AbstractStatusPanel;
import soc.gwtClient.game.abstractWidgets.IGamePanel;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class StatusDiceBitmapPanel extends AbstractStatusPanel
{
    public StatusDiceBitmapPanel(IGamePanel gamePanel)
    {
        super(gamePanel);

    }

    @Override
    public void onQueueChanged(ActionQueueChangedEvent event)
    {
        QueuedAction enqueuedAction = event.getEnqueuedAction();
        if (enqueuedAction != null)
        {
            lblStatus.setText(enqueuedAction.getAction().getToDoMessage());
        }
    }

    @Override
    protected ComplexPanel createRootPanel()
    {
        return new HorizontalPanel();
    }

}
