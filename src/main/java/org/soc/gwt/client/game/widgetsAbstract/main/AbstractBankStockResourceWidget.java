package org.soc.gwt.client.game.widgetsAbstract.main;

import org.soc.common.game.*;
import org.soc.common.game.Resources.ResourceList;
import org.soc.common.views.widgetsInterface.main.*;

import com.google.gwt.user.client.ui.*;

public abstract class AbstractBankStockResourceWidget implements BankStockResourceWidget {
  protected ComplexPanel rootPanel;
  protected ResourceList bank;
  protected Resource resource;

  public AbstractBankStockResourceWidget(ResourceList bank, Resource resource) {
    this.bank = bank;
    this.resource = resource;
    rootPanel = createRootPanel();
  }
  @Override public ComplexPanel createRootPanel() {
    return new VerticalPanel();
  }
  @Override public Widget asWidget() {
    return rootPanel;
  }
}
