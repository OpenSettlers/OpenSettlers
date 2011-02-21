package soc.gwtClient.game.widgetsBitmap.main;

import soc.gwtClient.game.widgetsAbstract.main.AbstractGameWidget;
import soc.gwtClient.game.widgetsInterface.actions.ActionsWidget;
import soc.gwtClient.game.widgetsInterface.generic.ToolTipManager;
import soc.gwtClient.game.widgetsInterface.main.GameWidgetFactory;

public class PlayBotsGamePanel extends AbstractGameWidget
{
    @Override
    protected void initialize()
    {
        super.initialize();

        GameWidgetFactory gameWidgetFactory = gamePanelLayoutWidget
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
