package soc.gwtClient.game.widgets.standard.bitmap.actions;

import soc.common.game.Player;
import soc.common.game.developmentCards.DevelopmentCard;
import soc.common.game.developmentCards.DevelopmentCardsChangedEvent;
import soc.common.game.developmentCards.DevelopmentCardsChangedEventHandler;
import soc.gwtClient.game.abstractWidgets.IActionWidget;
import soc.gwtClient.game.abstractWidgets.IGamePanel;
import soc.gwtClient.game.abstractWidgets.IPlayDevelopmentCardWidget;

import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.Widget;

public class PlayDevelopmentCardBitmapWidget implements
        IPlayDevelopmentCardWidget, DevelopmentCardsChangedEventHandler, IActionWidget
{
    protected MenuBar menuBar = new MenuBar();
    protected MenuItem rootMenuItem;
    protected MenuBar secondMenu = new MenuBar(true);
    protected Player player;
    protected IGamePanel gamePanel;
    
    public PlayDevelopmentCardBitmapWidget(Player player, IGamePanel gamePanel)
    {
        this.player=player;
        this.gamePanel=gamePanel;
        rootMenuItem = new MenuItem("menu", false, secondMenu);
        rootMenuItem.setHTML("<img src=\"iconz/48/BlankCard48.png\" />");
        menuBar.addItem(rootMenuItem);
        
        for (DevelopmentCard devCard : player.getDevelopmentCards())
        {
            secondMenu.addItem(new DevelopmentCardBitmapMenuItem(devCard, gamePanel));
        }
        player.getDevelopmentCards().addDevelopmentCardsChangedEventHandler(this);
        menuBar.addItem(rootMenuItem);
    }

    @Override
    public Widget asWidget()
    {
        return menuBar;
    }

    @Override
    public void onDevelopmentCardsChanged(DevelopmentCardsChangedEvent event)
    {
        if (event.getAddedCard() !=null)
        {
            secondMenu.addItem(new DevelopmentCardBitmapMenuItem(event.getAddedCard(), gamePanel));
        }
        //TODO:implement remove
    }

    @Override
    public Player getPlayer()
    {
        return player;
    }

    @Override
    public IActionWidget setEnabled(boolean enabled)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
