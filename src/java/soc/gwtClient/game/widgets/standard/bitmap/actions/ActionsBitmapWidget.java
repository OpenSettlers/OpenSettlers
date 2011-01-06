package soc.gwtClient.game.widgets.standard.bitmap.actions;

import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.AbstractActionsWidget;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.game.abstractWidgets.factories.ActionWidgetFactory;

public class ActionsBitmapWidget extends AbstractActionsWidget
{
    ActionWidgetBitmapFactory widgetFactory;

    public ActionsBitmapWidget(GamePanel gamePanel, GamePlayer player)
    {
        super(gamePanel, player);
    }
    
    @Override
    public ActionWidgetFactory getActionWidgetFactory()
    {
        return new ActionWidgetBitmapFactory();
    }
}
