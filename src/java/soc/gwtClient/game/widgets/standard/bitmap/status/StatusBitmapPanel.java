package soc.gwtClient.game.widgets.standard.bitmap.status;

import soc.common.actions.gameAction.GameAction;
import soc.common.game.StatusChangedEvent;
import soc.common.game.Turn;
import soc.common.game.TurnChangedEvent;
import soc.common.game.logs.ActionQueueChangedEvent;
import soc.gwtClient.game.abstractWidgets.AbstractStatusPanel;
import soc.gwtClient.game.abstractWidgets.GamePanel;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class StatusBitmapPanel extends AbstractStatusPanel
{
    /**
     * @wbp.parser.entryPoint
     */
    public StatusBitmapPanel(GamePanel gamePanel)
    {
        super(gamePanel);

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
            sb.append("Turn #");
            sb.append(turn.getID());
        }

        sb.append("'s turn. ");
        sb.append(turn.getPlayer().getUser().getName());
        sb.append(" should ");
        sb.append(gamePanel.getGame().getCurrentPhase().getMessage());

        lblTurn.setText(sb.toString());
        lblPlayerOnTurn.setText(turn.getPlayer().getUser().getName());
    }

    @Override
    public void onStatusChanged(StatusChangedEvent event)
    {
        lblStatus.setText("Game status: "
                + gamePanel.getGame().getStatus().getDescription());
    }
}