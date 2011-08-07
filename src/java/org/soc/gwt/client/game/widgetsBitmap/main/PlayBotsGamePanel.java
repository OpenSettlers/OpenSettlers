package org.soc.gwt.client.game.widgetsBitmap.main;

import org.soc.common.views.widgetsInterface.actions.ActionsWidget;
import org.soc.common.views.widgetsInterface.generic.ToolTipManager;
import org.soc.common.views.widgetsInterface.main.GameWidgetFactory;
import org.soc.gwt.client.game.widgetsAbstract.main.AbstractGameWidget;

public class PlayBotsGamePanel extends AbstractGameWidget
{
    @Override
    protected void initialize()
    {
        super.initialize();

        GameWidgetFactory gameWidgetFactory = gameWidgetLayoutWidget
                .getGameWidgetFactory();

    }

    @Override
    public GameWidgetFactory createGameWidgetFactory()
    {
        return new GameBitmapWidgetFactory(this);
    }

    @Override
    public ActionsWidget getActionsWidget()
    {
        return null;
    }

    @Override
    public ToolTipManager getToolTipManager()
    {
        // TODO Auto-generated method stub
        return null;
    }
}
