package soc.gwtClient.game.widgets.standard.bitmap;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import soc.common.board.resources.Resource;
import soc.common.board.resources.ResourceList;
import soc.gwtClient.game.abstractWidgets.AbstractBankStockResourceWidget;

public class BitmapBankStockResourceWidget extends
        AbstractBankStockResourceWidget
{
    Image reourceImage = new Image("icons/48/BlankCard48.png");
    Label lblResourceAmount = new Label();
    
    public BitmapBankStockResourceWidget(ResourceList bank, Resource resource)
    {
        super(bank, resource);
        
        int amount = bank.ofType(resource).size();
        lblResourceAmount.setText(Integer.toString(amount));
        
        rootPanel.add(reourceImage);
        rootPanel.add(lblResourceAmount);
    }

}
