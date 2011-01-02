package soc.gwtClient.game.widgets.standard.bitmap.actions;

import com.google.gwt.user.client.ui.Widget;

import soc.common.game.GamePlayer;
import soc.common.game.developmentCards.DevelopmentCardsChangedEvent;
import soc.common.game.developmentCards.DevelopmentCardsChangedEventHandler;
import soc.gwtClient.game.abstractWidgets.IActionWidget;
import soc.gwtClient.game.abstractWidgets.IPlayDevelopmentCardWidget;

public class PlayDevelopmentCardWidget2 implements IActionWidget,
        IPlayDevelopmentCardWidget, DevelopmentCardsChangedEventHandler
{

    @Override
    public GamePlayer getPlayer()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IActionWidget setEnabled(boolean enabled)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Widget asWidget()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onDevelopmentCardsChanged(DevelopmentCardsChangedEvent event)
    {
        // TODO Auto-generated method stub

    }

}
