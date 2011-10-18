package org.soc.gwt.client.game.widgetsBitmap.actions;

import org.soc.common.game.GamePlayer;
import org.soc.common.game.actions.Action.ActionPresenter;
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
        for (ActionPresenter actionWidget : widgetsIndices.values())
        {
            actionWidget.setEnabled(enabled);
        }
    }
}
