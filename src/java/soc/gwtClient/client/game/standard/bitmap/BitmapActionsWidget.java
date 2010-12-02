package soc.gwtClient.client.game.standard.bitmap;

import soc.common.game.Player;
import soc.gwtClient.client.game.AbstractActionsWidget;
import soc.gwtClient.client.game.IActionWidgetFactory;
import soc.gwtClient.client.game.IGamePanel;

public class BitmapActionsWidget extends AbstractActionsWidget
{
    BitmapActionWidgetFactory widgetFactory = new BitmapActionWidgetFactory();

    public BitmapActionsWidget(IGamePanel gamePanel, Player player)
    {
        super(gamePanel, player);
    }
    
    @Override
    public IActionWidgetFactory getActionWidgetFactory()
    {
        return widgetFactory;
    }
}
