package soc.gwtClient.client.game;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import soc.common.game.Player;

public abstract class AbstractStockItemWidget implements IStockItemWidget
{
    protected Player player;
    protected ComplexPanel rootPanel;
    
    public AbstractStockItemWidget(Player player)
    {
        this.player=player;
        
        rootPanel = createRootPanel();
    }

    /* (non-Javadoc)
     * @see com.google.gwt.user.client.ui.IsWidget#asWidget()
     */
    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }
    
    /* (non-Javadoc)
     * @see soc.gwtClient.client.game.IStockItemWidget#createRootPanel()
     */
    @Override
    public ComplexPanel createRootPanel()
    {
        return new VerticalPanel();
    }
}
