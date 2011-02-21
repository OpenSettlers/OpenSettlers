package soc.gwtClient.game.widgetsAbstract.playerInfo;

import soc.common.game.developmentCards.DevelopmentCardsChangedEvent;
import soc.common.game.developmentCards.DevelopmentCardsChangedEventHandler;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.widgetsInterface.playerInfo.DevelopmentCardsAmountWidget;

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
