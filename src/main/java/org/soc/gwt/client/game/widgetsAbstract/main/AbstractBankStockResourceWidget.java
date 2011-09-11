package org.soc.gwt.client.game.widgetsAbstract.main;

import org.soc.common.game.Resource;
import org.soc.common.game.ResourceList;
import org.soc.common.views.widgetsInterface.main.BankStockResourceWidget;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

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
