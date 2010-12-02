package soc.gwtClient.client.game;

import soc.common.board.resources.Resource;
import soc.common.board.resources.ResourceList;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractBankStockResourceWidget implements IBankStockResourceWidget
{
    protected ComplexPanel rootPanel;
    protected ResourceList bank;
    protected Resource resource;
    
    public AbstractBankStockResourceWidget(ResourceList bank, Resource resource)
    {
        this.bank=bank;
        this.resource=resource;
        
        rootPanel = createRootPanel();
    }

    @Override
    public ComplexPanel createRootPanel()
    {
        return new VerticalPanel();
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }
}
