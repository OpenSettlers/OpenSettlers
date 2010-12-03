package soc.gwtClient.client.game;

import soc.common.game.Player;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class AbstractDevelopmentCardsAmountWidget implements
        IDevelopmentCardsAmountWidget
{
    protected Player player;
    protected ComplexPanel rootPanel;
    
    public AbstractDevelopmentCardsAmountWidget(Player player)
    {
        this.player=player;
        
        rootPanel = new VerticalPanel();
    }
    
    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }
}
