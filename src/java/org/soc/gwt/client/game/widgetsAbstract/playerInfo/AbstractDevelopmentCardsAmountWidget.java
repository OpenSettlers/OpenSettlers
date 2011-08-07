package org.soc.gwt.client.game.widgetsAbstract.playerInfo;

import org.soc.common.game.developmentCards.DevelopmentCardsChangedEvent;
import org.soc.common.game.developmentCards.DevelopmentCardsChangedEventHandler;
import org.soc.common.game.player.GamePlayer;
import org.soc.common.views.widgetsInterface.payerInfo.DevelopmentCardsAmountWidget;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractDevelopmentCardsAmountWidget implements
        DevelopmentCardsAmountWidget, DevelopmentCardsChangedEventHandler
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
