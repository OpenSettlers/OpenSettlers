package soc.gwtClient.game.abstractWidgets;

import soc.common.game.GamePlayer;
import soc.common.game.developmentCards.DevelopmentCardsChangedEvent;
import soc.common.game.developmentCards.DevelopmentCardsChangedEventHandler;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractDevelopmentCardsAmountWidget implements
        IDevelopmentCardsAmountWidget, DevelopmentCardsChangedEventHandler
{
    protected GamePlayer player;
    protected ComplexPanel rootPanel;
    
    public AbstractDevelopmentCardsAmountWidget(GamePlayer player)
    {
        this.player=player;
        
        rootPanel = new VerticalPanel();
        
        player.getDevelopmentCards().addDevelopmentCardsChangedEventHandler(this);
    }
    
    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }
}
