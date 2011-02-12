package soc.gwtClient.game.widgets.standard.bitmap.actions;

import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.AbstractActionsWidget;
import soc.gwtClient.game.abstractWidgets.ActionWidget;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.game.abstractWidgets.factories.ActionWidgetFactory;

public class ActionsBitmapWidget extends AbstractActionsWidget
{
    public ActionsBitmapWidget(GamePanel gamePanel, GamePlayer player)
    {
        super(gamePanel, player);
    }

    @Override
    public ActionWidgetFactory getActionWidgetFactory()
    {
        return new ActionWidgetBitmapFactory(player);
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
