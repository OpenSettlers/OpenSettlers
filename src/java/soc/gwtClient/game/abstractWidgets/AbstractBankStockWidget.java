package soc.gwtClient.game.abstractWidgets;

import soc.common.board.resources.AbstractResource;
import soc.common.board.resources.Resource;
import soc.common.game.Game;
import soc.gwtClient.game.widgets.standard.bitmap.BankStockResourceBitmapWidget;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class AbstractBankStockWidget implements IBankStockPanel
{
    protected ComplexPanel rootPanel;
    protected Game game;
    
    public AbstractBankStockWidget(Game game)
    {
        this.game=game;
        rootPanel = createRootPanel();
        
        for (Resource playableResource : game.getGameRules().getSupportedResources())
        {
            IBankStockResourceWidget bankResourceWidget = 
                createBankStockResourceWidget(playableResource);
            rootPanel.add(bankResourceWidget);
        }
    }
    
    @Override
    public ComplexPanel createRootPanel()
    {
        return new HorizontalPanel();
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }

    @Override
    public IBankStockResourceWidget createBankStockResourceWidget(
            Resource resource)
    {
        return new BankStockResourceBitmapWidget(game.getBank(), resource);
    }

}
