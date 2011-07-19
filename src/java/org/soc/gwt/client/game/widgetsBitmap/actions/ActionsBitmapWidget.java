package org.soc.gwt.client.game.widgetsBitmap.actions;

import org.soc.common.game.player.GamePlayer;
import org.soc.common.views.widgetsInterface.actions.ActionWidget;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.gwt.client.game.widgetsAbstract.actions.AbstractActionsWidget;

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
