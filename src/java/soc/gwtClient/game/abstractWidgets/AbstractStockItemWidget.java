package soc.gwtClient.game.abstractWidgets;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import soc.common.game.player.GamePlayer;

public abstract class AbstractStockItemWidget implements IStockItemWidget
{
    protected GamePlayer player;
    protected ComplexPanel rootPanel;
    
    public AbstractStockItemWidget(GamePlayer player)
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
