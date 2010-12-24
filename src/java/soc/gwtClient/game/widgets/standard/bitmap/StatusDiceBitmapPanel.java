package soc.gwtClient.game.widgets.standard.bitmap;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;

import soc.common.game.logs.ActionQueueChangedEvent;
import soc.common.game.logs.QueuedAction;
import soc.gwtClient.game.abstractWidgets.AbstractStatusDicePanel;
import soc.gwtClient.game.abstractWidgets.IDiceWidget;
import soc.gwtClient.game.abstractWidgets.IGamePanel;
import soc.gwtClient.game.widgets.standard.svg.SvgStandardDiceWidget;

public class StatusDiceBitmapPanel extends AbstractStatusDicePanel
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

    @Override
    protected IDiceWidget createDiceWidget()
    {
        return new SvgStandardDiceWidget(gamePanel);
    }

}
