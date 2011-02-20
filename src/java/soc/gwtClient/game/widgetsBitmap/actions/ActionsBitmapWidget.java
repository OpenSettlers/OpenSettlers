package soc.gwtClient.game.widgetsBitmap.actions;

import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.widgetsAbstract.actions.AbstractActionsWidget;
import soc.gwtClient.game.widgetsInterface.actions.ActionWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

public class ActionsBitmapWidget extends AbstractActionsWidget
{
    public ActionsBitmapWidget(GameWidget gamePanel, GamePlayer player)
    {
        super(gamePanel, player);
    }

    @Override
    public void setEnabled(boolean enabled)
    {
        for (ActionWidget actionWidget : widgetsIndices.values())
        {
            actionWidget.setEnabled(enabled);
        }
    }
}
