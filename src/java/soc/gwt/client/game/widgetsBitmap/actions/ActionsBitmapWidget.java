package soc.gwt.client.game.widgetsBitmap.actions;

import soc.common.game.player.GamePlayer;
import soc.common.views.widgetsInterface.actions.ActionWidget;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.gwt.client.game.widgetsAbstract.actions.AbstractActionsWidget;

public class ActionsBitmapWidget extends AbstractActionsWidget
{
    public ActionsBitmapWidget(GameWidget gameWidget, GamePlayer player)
    {
        super(gameWidget, player);
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
