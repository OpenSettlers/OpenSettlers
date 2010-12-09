package soc.gwtClient.game.widgets.standard.bitmap;

import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.Widget;

import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.game.Player;
import soc.common.game.developmentCards.DevelopmentCardsChangedEvent;
import soc.common.game.developmentCards.DevelopmentCardsChangedEventHandler;
import soc.gwtClient.game.abstractWidgets.AbstractActionWidget;
import soc.gwtClient.game.abstractWidgets.IGamePanel;

public class BitmapPlayDevelopmentCardWidget extends AbstractActionWidget 
    implements DevelopmentCardsChangedEventHandler
{
    MenuBar menu = new MenuBar(true);
    
    public BitmapPlayDevelopmentCardWidget(IGamePanel gamePanel, Player player)
    {
        super(gamePanel, player);
        
        player.getDevelopmentCards().addDevelopmentCardsChangedEventHandler(this);
    }

    @Override
    public Widget asWidget()
    {
        return menu;
    }

    @Override
    protected void updateEnabled()
    {
        //TODO: enable/disable all menu items
    }

    @Override
    public void onDevelopmentCardsChanged(DevelopmentCardsChangedEvent event)
    {
        //TODO:implement
    }
}
