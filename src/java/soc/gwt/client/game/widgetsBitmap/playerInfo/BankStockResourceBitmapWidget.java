package soc.gwt.client.game.widgetsBitmap.playerInfo;

import soc.common.board.resources.Resource;
import soc.common.board.resources.ResourceList;
import soc.common.board.resources.ResourcesChangedEvent;
import soc.common.board.resources.ResourcesChangedEventHandler;
import soc.gwt.client.game.widgetsAbstract.main.AbstractBankStockResourceWidget;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class BankStockResourceBitmapWidget extends
        AbstractBankStockResourceWidget implements ResourcesChangedEventHandler
{
    Image reourceImage;
    Label lblResourceAmount = new Label();

    public BankStockResourceBitmapWidget(ResourceList bank, Resource resource)
    {
        super(bank, resource);

        reourceImage = new Image(resource.getMeta().icon().iconDefault());

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
