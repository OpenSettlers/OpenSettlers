package org.soc.gwt.client.game.widgetsBitmap.playerInfo;

import org.soc.common.core.GenericList.AddsList.ListAdded;
import org.soc.common.core.GenericList.ImmutableList;
import org.soc.common.core.GenericList.Models;
import org.soc.common.core.GenericList.RemovesList.ListRemoved;
import org.soc.common.game.*;
import org.soc.common.game.Resources.MutableResourceList;
import org.soc.gwt.client.game.widgetsAbstract.main.*;

import com.google.gwt.user.client.ui.*;

public class BankStockResourceBitmapWidget extends
        AbstractBankStockResourceWidget
{
  private Image reourceImage;
  private Label lblResourceAmount = new Label();

  public BankStockResourceBitmapWidget(MutableResourceList bank, Resource resource)
  {
    super(bank, resource);
    reourceImage = new Image(Models.icon(resource).iconDefault());
    int amount = bank.ofType(resource.getClass()).size();
    lblResourceAmount.setText(Integer.toString(amount));
    rootPanel.add(reourceImage);
    rootPanel.add(lblResourceAmount);
    bank.addListAddedHandler(new ListAdded<Resource>() {
      @Override public void listAdded(ImmutableList<Resource> items) {
        update();
      }
    });
    bank.addListRemovedHandler(new ListRemoved<Resource>() {
      @Override public void listRemoved(ImmutableList<Resource> items) {
        update();
      }
    });
  }
  public void update() {
    //    ResourceList changedResources = resourcesChanged.getRemovedResources() != null ? resourcesChanged
    //            .getRemovedResources() : resourcesChanged.getAddedResources();
    //    if (changedResources.ofType(resource.getClass()).size() > 0)
    //    {
    //      int amount = bank.ofType(resource.getClass()).size();
    //      lblResourceAmount.setText(Integer.toString(amount));
    //    }
  }
}
