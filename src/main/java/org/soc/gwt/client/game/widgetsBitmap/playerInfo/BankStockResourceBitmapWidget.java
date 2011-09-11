package org.soc.gwt.client.game.widgetsBitmap.playerInfo;

import org.soc.common.game.Resource;
import org.soc.common.game.ResourceList;
import org.soc.common.game.ResourcesChangedEvent;
import org.soc.common.game.ResourcesChangedEvent.ResourcesChangedHandler;
import org.soc.gwt.client.game.widgetsAbstract.main.AbstractBankStockResourceWidget;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class BankStockResourceBitmapWidget extends
        AbstractBankStockResourceWidget implements ResourcesChangedHandler
{
  Image reourceImage;
  Label lblResourceAmount = new Label();

  public BankStockResourceBitmapWidget(ResourceList bank, Resource resource)
  {
    super(bank, resource);
    reourceImage = new Image(resource.icon().iconDefault());
    int amount = bank.ofType(resource).size();
    lblResourceAmount.setText(Integer.toString(amount));
    rootPanel.add(reourceImage);
    rootPanel.add(lblResourceAmount);
    bank.addResourcesChangedHandler(this);
  }
  @Override public void onResourcesChanged(ResourcesChangedEvent resourcesChanged)
  {
    ResourceList changedResources = resourcesChanged.getRemovedResources() != null ? resourcesChanged
            .getRemovedResources() : resourcesChanged.getAddedResources();
    if (changedResources.ofType(resource).size() > 0)
    {
      int amount = bank.ofType(resource).size();
      lblResourceAmount.setText(Integer.toString(amount));
    }
  }
}
