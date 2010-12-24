package soc.gwtClient.game.widgets.standard.bitmap;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import soc.common.board.resources.AbstractResource;
import soc.common.board.resources.Resource;
import soc.common.board.resources.ResourceList;
import soc.common.board.resources.ResourcesChangedEvent;
import soc.common.board.resources.ResourcesChangedEventHandler;
import soc.gwtClient.game.abstractWidgets.AbstractBankStockResourceWidget;

public class BankStockResourceBitmapWidget extends
        AbstractBankStockResourceWidget implements ResourcesChangedEventHandler
{
    Image reourceImage = new Image("iconz/48/BlankCard48.png");
    Label lblResourceAmount = new Label();
    
    public BankStockResourceBitmapWidget(ResourceList bank, Resource resource)
    {
        super(bank, resource);
        
        int amount = bank.ofType(resource).size();
        lblResourceAmount.setText(Integer.toString(amount));
        
        rootPanel.add(reourceImage);
        rootPanel.add(lblResourceAmount);
        
        bank.addResourcesChangedEventHandler(this);
    }

    @Override
    public void onResourcesChanged(ResourcesChangedEvent resourcesChanged)
    {
        if (resourcesChanged.getChangedResources().ofType(resource).size() > 0)
        {
            int amount = bank.ofType(resource).size();
            lblResourceAmount.setText(Integer.toString(amount));
        }
    }

}
