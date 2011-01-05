package soc.gwtClient.game.abstractWidgets;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import soc.common.game.developmentCards.DevelopmentCardsChangedEventHandler;
import soc.common.game.player.GamePlayer;

public abstract class AbstractLargestArmyWidget 
    implements ILargestArmyWidget, DevelopmentCardsChangedEventHandler
{
    protected ComplexPanel rootPanel;
    protected GamePlayer player; 
    
    public AbstractLargestArmyWidget(GamePlayer player)
    {
        this.player=player;
        
        rootPanel = createRootPanel();
        
        player.getPlayedDevelopmentCards().addDevelopmentCardsChangedEventHandler(this);
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }
    
    /* (non-Javadoc)
     * @see soc.gwtClient.client.game.ILargestArmyWidget#createRootPanel()
     */
    @Override
    public ComplexPanel createRootPanel()
    {
        return new VerticalPanel();
    }
}
