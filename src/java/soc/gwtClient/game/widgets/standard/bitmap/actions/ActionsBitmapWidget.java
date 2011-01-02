package soc.gwtClient.game.widgets.standard.bitmap.actions;

import soc.common.game.GamePlayer;
import soc.gwtClient.game.abstractWidgets.AbstractActionsWidget;
import soc.gwtClient.game.abstractWidgets.IGamePanel;
import soc.gwtClient.game.abstractWidgets.factories.IActionWidgetFactory;

public class ActionsBitmapWidget extends AbstractActionsWidget
{
    ActionWidgetBitmapFactory widgetFactory;

    public ActionsBitmapWidget(IGamePanel gamePanel, GamePlayer player)
    {
        super(gamePanel, player);
    }
    
    @Override
    public IActionWidgetFactory getActionWidgetFactory()
    {
        return new ActionWidgetBitmapFactory();
    }
}
