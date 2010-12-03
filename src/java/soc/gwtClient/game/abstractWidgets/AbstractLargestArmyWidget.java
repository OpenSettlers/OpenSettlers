package soc.gwtClient.game.abstractWidgets;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import soc.common.game.Player;

public abstract class AbstractLargestArmyWidget implements ILargestArmyWidget
{
    protected ComplexPanel rootPanel;
    protected Player player; 
    
    public AbstractLargestArmyWidget(Player player)
    {
        this.player=player;
        
        rootPanel = createRootPanel();
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
