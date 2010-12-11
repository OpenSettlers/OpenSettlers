package soc.gwtClient.game.abstractWidgets;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;

import soc.common.game.Game;
import soc.common.game.Player;
import soc.common.game.StockItem;

public abstract class AbstractStockWidget implements IStockWidget
{
    protected Game game;
    protected Player player;
    protected ComplexPanel rootPanel;
    
    public AbstractStockWidget(Game game, Player player)
    {
        this.game=game;
        this.player=player;
        
        rootPanel = createRootPanel();

        createStockItemWidgetsList();
    }
    
    private void createStockItemWidgetsList()
    {
        for (StockItem stockItem : game.getGameRules().getStockPieces())
        {
            rootPanel.add(createStockItemWidget(stockItem.getPiece()));
        }
    }
    
    /* (non-Javadoc)
     * @see soc.gwtClient.client.game.IStockWidget#createRootPanel()
     */
    @Override
    public ComplexPanel createRootPanel()
    {
        return new HorizontalPanel();
    }
}
