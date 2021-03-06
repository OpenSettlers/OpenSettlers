package org.soc.gwt.client.game.widgetsAbstract.main;

import org.soc.common.game.Game;
import org.soc.common.game.Resource;
import org.soc.common.views.widgetsInterface.main.BankStockResourceWidget;
import org.soc.common.views.widgetsInterface.main.BankStockWidget;
import org.soc.common.views.widgetsInterface.playerInfo.DevelopmentCardsAmountWidget;
import org.soc.gwt.client.game.widgetsBitmap.playerInfo.BankStockResourceBitmapWidget;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class AbstractBankStockWidget implements BankStockWidget {
  protected ComplexPanel rootPanel;
  protected Game game;
  protected DevelopmentCardsAmountWidget devCards;

  public AbstractBankStockWidget(Game game) {
    this.game = game;
    rootPanel = createRootPanel();
    for (Resource playableResource : game.rules().supportedResources()) {
      BankStockResourceWidget bankResourceWidget = createBankStockResourceWidget(playableResource);
      rootPanel.add(bankResourceWidget);
    }
  }
  @Override public ComplexPanel createRootPanel() {
    return new HorizontalPanel();
  }
  @Override public Widget asWidget() {
    return rootPanel;
  }
  @Override public BankStockResourceWidget createBankStockResourceWidget(Resource resource) {
    return new BankStockResourceBitmapWidget(game.bank(), resource);
  }
  @Override public DevelopmentCardsAmountWidget createDevelopmentCardsWidget(Game game) {
    // TODO Auto-generated method stub
    return null;
  }
}
