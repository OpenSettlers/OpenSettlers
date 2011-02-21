package soc.gwtClient.game.widgetsBitmap.status;

import soc.common.actions.gameAction.GameAction;
import soc.common.game.StatusChangedEvent;
import soc.common.game.Turn;
import soc.common.game.TurnChangedEvent;
import soc.common.game.logs.ActionQueueChangedEvent;
import soc.gwtClient.game.widgetsAbstract.AbstractStatusPanel;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class StatusBitmapPanel extends AbstractStatusPanel
{
    /**
     * @wbp.parser.entryPoint
     */
    public StatusBitmapPanel(GameWidget gameWidget)
    {
        super(gameWidget);

        rootPanel.setStyleName("statusPanel");
    }

    @Override
    public void onQueueChanged(ActionQueueChangedEvent event)
    {
        GameAction enqueuedAction = event.getEnqueuedAction();

        if (enqueuedAction != null)
            lblAction.setText(enqueuedAction.getToDoMessage());
    }

    @Override
    protected ComplexPanel createRootPanel()
    {
        return new HorizontalPanel();
    }

    @Override
    public void onTurnChanged(TurnChangedEvent event)
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
        sb.append(turn.getPlayer().getUser().getName());
        sb.append(" should ");
        sb.append(gameWidget.getGame().getCurrentPhase().getMessage());

        lblTurn.setText(sb.toString());
        lblPlayerOnTurn.setText(turn.getPlayer().getUser().getName());
    }

    @Override
    public void onStatusChanged(StatusChangedEvent event)
    {
        lblStatus.setText("Game status: "
                + gameWidget.getGame().getStatus().getDescription());
    }
}